(ns bike-stations.handlers
  (:require [ring.util.response :as ring]
            [bike-stations.tfl :as api]
            [clojure.data.json :as json]
            [bike-stations.renderers :as renderers]))

(def marble-arch-coords {:lat 51.513110 :lon -0.158915})

(defn hello [req]
  (-> (ring/response (renderers/welcome-page [marble-arch-coords]))
      (ring/content-type "text/html")))

(defn bike-stations-table [req]
  (-> (ring/response "Show html table")
      (ring/content-type "text/html")))

(defn bike-stations-edn [req]
  (-> (ring/response (str (seq (api/nearby-stations (assoc marble-arch-coords :n 5)))))
      (ring/content-type "application/edn")))

(defn bike-stations-json [req]
  (-> (ring/response (str (json/write-str (api/nearby-stations (assoc marble-arch-coords :n 5)))))
      (ring/content-type "application/json")))
