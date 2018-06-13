(ns bike-stations.core
  (:require [ring.adapter.jetty :refer [run-jetty]]))

(defn handler [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World!"})

(defn -main
  "Start the jetty server."
  [& args]
  (run-jetty handler {:port 3000}))
