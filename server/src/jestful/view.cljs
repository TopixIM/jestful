
(ns jestful.view
  (:require [jestful.schema :as schema] [clojure.string :as string]))

(defn render-view [state-id db]
  {:state (get-in db [:states state-id]),
   :statistics
   {:count (count (:states db)),
    :names
    (map
      (fn [entry]
        (let [state (last entry) nickname (:nickname state)]
          (if (string/blank? nickname) "访客" nickname)))
      (:states db))},
   :messages (:messages db),
   :drafts
   (->>
     (:states db)
     (map val)
     (filter (fn [state] (not (string/blank? (:draft state)))))
     (map
       (fn [state]
         (let [draft (:draft state) nickname (:nickname state)]
           [state-id nickname draft]))))})

(defn render-scene [db] db)
