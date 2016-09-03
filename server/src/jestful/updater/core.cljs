
(ns jestful.updater.core
  (:require [jestful.updater.state :as state]))

(defn handle-draft [db op-data state-id op-id op-time]
  (assoc-in db [:states state-id :draft] op-data))

(defn handle-naming [db op-data state-id op-id op-time]
  (assoc-in db [:states state-id :nickname] op-data))

(defn handle-reply [db op-data state-id op-id op-time]
  (let [state (get-in db [:states state-id])
        nickname (:nickname state)]
    (-> db
     (update
       :messages
       (fn [messages]
         (let [left-messages (if (> (count messages) 40)
                               (subvec messages 10)
                               messages)]
           (conj
             messages
             {:author-name nickname,
              :time op-time,
              :id op-id,
              :text op-data}))))
     (assoc-in [:states state-id :draft] ""))))

(defn updater [db op op-data state-id op-id op-time]
  (case
    op
    :state/connect
    (state/connect db op-data state-id op-id op-time)
    :state/disconnect
    (state/disconnect db op-data state-id op-id op-time)
    :reply
    (handle-reply db op-data state-id op-id op-time)
    :naming
    (handle-naming db op-data state-id op-id op-time)
    :draft
    (handle-draft db op-data state-id op-id op-time)
    db))
