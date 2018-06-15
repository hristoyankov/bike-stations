(ns bike-stations.auth)

(def passwd-file "users_db.edn")

(defn authenticated? [name pass]
  (let [users-db (read-string (slurp passwd-file))
        user (first (filter #(= (:name %) name) users-db))]
    (when user
      (= (:password user) pass))))
