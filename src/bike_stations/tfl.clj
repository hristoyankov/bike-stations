(ns bike-stations.tfl
  (:require [clojure.data.json :as json]
            [haversine.core :as haversine]))

(def tfl-bike-stations-endpoint
  (str "https://api.tfl.gov.uk/BikePoint?"
       "app_id=" (System/getenv "TFL_APP_ID")
       "&app_key=" (System/getenv "TFL_APP_KEY")))

(def tfl-bike-stations
  (-> tfl-bike-stations-endpoint
      slurp
      (json/read-str :key-fn keyword)))

(defn- get-int-prop [tfl-bike-station prop]
  (->> tfl-bike-station
      :additionalProperties
      (filter #(= (:key %) prop))
      first
      :value
      Integer/parseInt))

(defn- get-docks [tfl-bike-station]
  (get-int-prop tfl-bike-station "NbDocks"))

(defn- get-bikes [tfl-bike-station]
  (get-int-prop tfl-bike-station "NbBikes"))

(defn- get-spaces [tfl-bike-station]
  (get-int-prop tfl-bike-station "NbEmptyDocks"))

(defn- tfl->bike-station [tfl]
  {:id (:id tfl)
   :name (:commonName tfl)
   :lat (:lat tfl)
   :lon (:lon tfl)
   :docks (get-docks tfl)
   :bikes (get-bikes tfl)
   :spaces (get-spaces tfl)})

(defn- valid? [{:keys [docks bikes spaces]}]
  (= 0 (- docks bikes spaces)))

(def bike-stations
  (->> tfl-bike-stations
       (map tfl->bike-station)
       (filter valid?)))

(defn nearby-stations [{:keys [lat lon n]}]
  (->> bike-stations
       (map #(assoc % :distance (haversine/haversine {:latitude lat :longitude lon}
                                                     {:latitude (:lat %) :longitude (:lon %)})))
       (sort-by :distance <)
       (take n)))
