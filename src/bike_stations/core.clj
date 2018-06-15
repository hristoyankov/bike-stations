(ns bike-stations.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.basic-authentication :refer [wrap-basic-authentication]]
            [reitit.ring :as ring]
            [ring.util.response :refer [response redirect]]))

(defn hello [ppreq]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World"})

(defn bike-stations-table [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Show html table"})

(defn bike-stations-edn [req]
  {:status 200
   :headers {"Content-Type" "application/edn"}
   :body (str [])})

(defn bike-stations-json [req]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body "[]"})

(def handler
  (ring/ring-handler
   (ring/router
    [["/" {:get {:handler hello}}]
     ["/bike-stations" {:get {:handler bike-stations-table}}]
     ["/api/bike-stations"
      [".edn" {:get {:handler bike-stations-edn}}]
      [".json" {:get {:handler bike-stations-json}}]]])
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


