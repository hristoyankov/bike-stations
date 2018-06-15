(ns bike-stations.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.basic-authentication :refer [wrap-basic-authentication]]))

(defn handler [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World!"})

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

(print "Hello world")
