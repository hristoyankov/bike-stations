(ns bike-stations.renderers
  (:require [hiccup.core :as h]))

(defn welcome-page [{:keys [lat lon]}]
  (h/html [:div
           [:h3 "Welcome to bike stations"]
           [:p "This is a sample project that consumes tfl open data and shows nearby bike stations based."]
           [:a {:href "/bike-stations"} "Click here to see the 5 five closest stations to Marble Arch, London and their availability"]]))
