(ns bike-stations.core
  (:require [ring.adapter.jetty :as jetty]
            [reitit.ring :as ring]
            [bike-stations.handlers :as handlers]
            [bike-stations.auth :as auth]
            [ring.middleware.basic-authentication :as http-auth]))

(def routes
  (ring/ring-handler
   (ring/router
    [["/" {:get {:handler handlers/hello}}]
     ["/bike-stations" {:get {:handler handlers/bike-stations-table}}]
     ["/api/bike-stations"
      [".edn" {:get {:handler handlers/bike-stations-edn}}]
      [".json" {:get {:handler handlers/bike-stations-json}}]]])
   (ring/create-default-handler)))

(defn -main
  "Start the jetty server."
  [& args]
  (jetty/run-jetty
   (http-auth/wrap-basic-authentication
    routes
    auth/authenticated?)
   {:port (Integer/parseInt (System/getenv "BIKE_STATIONS_SERVER_PORT"))}))


