(ns bike-stations.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.basic-authentication :refer [wrap-basic-authentication]]
            [reitit.ring :as ring]
            [bike-stations.handlers :as handlers]))

(def handler
  (ring/ring-handler
   (ring/router
    [["/" {:get {:handler handlers/hello}}]
     ["/bike-stations" {:get {:handler handlers/bike-stations-table}}]
     ["/api/bike-stations"
      [".edn" {:get {:handler handlers/bike-stations-edn}}]
      [".json" {:get {:handler handlers/bike-stations-json}}]]])
   (ring/create-default-handler)))

(defn authenticated? [name pass]
  (let [users-db (read-string (slurp "users_db.clj"))
        user (first (filter #(= (:name %) name) users-db))]
    (when user
      (= (:password user) pass))))

(def app (-> handler
             (wrap-basic-authentication authenticated?)))

(defn -main
  "Start the jetty server."
  [& args]
  (run-jetty app {:port 3000}))


