(ns bike-stations.renderers
  (:require [hiccup.core :as h]))

(defn welcome-page [{:keys [lat lon]}]
  (h/html [:div
           [:h3 "Welcome to bike stations"]
           [:p "This is a sample project that consumes tfl open data and shows nearby bike stations based."]
           [:a {:href "/bike-stations"} "Click here to see the 5 five closest stations to Marble Arch, London and their availability"]]))

(defn- bs->tr [{:keys [name bikes]}]
  [:tr (list [:td name] [:td bikes])])

(defn stations-table [bike-stations]
  (h/html [:table
           [:thead
            [:tr (list [:th "Name"] [:th "Available bikes"])]]
           [:tbody
            (map bs->tr bike-stations)]]))
