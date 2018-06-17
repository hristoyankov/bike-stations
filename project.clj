(defproject bike-stations "0.1.0-SNAPSHOT"
  :description "Personal project (interview take home task)"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [ring-basic-authentication "1.0.5"]
                 [metosin/reitit-core "0.1.2"]
                 [metosin/reitit-ring "0.1.2"]
                 [org.clojure/data.json "0.2.6"]
                 [haversine "0.1.1"]
                 [hiccup "1.0.5"]]
  :main ^:skip-aot bike-stations.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
