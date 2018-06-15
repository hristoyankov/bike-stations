(ns bike-stations.auth)

(defn authenticated? [name pass]
  (let [users-db (read-string (slurp "users_db.clj"))
        user (first (filter #(= (:name %) name) users-db))]
    (when user
      (= (:password user) pass))))
