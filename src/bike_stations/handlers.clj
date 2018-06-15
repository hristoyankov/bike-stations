(ns bike-stations.handlers)

(defn hello [req]
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
