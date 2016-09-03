
(ns jestful.comp.reply
  (:require [hsl.core :refer [hsl]]
            [clojure.string :as string]
            [respo.alias :refer [create-comp div span textarea]]
            [respo-ui.style :as ui]
            [jestful.style.widget :as widget]
            [jestful.style.layout :as layout]
            [respo.comp.debug :refer [comp-debug]]
            [jestful.comp.naming :refer [comp-naming]]))

(defn on-input [mutate!]
  (fn [e dispatch!] (mutate! (:value e)) (dispatch! :draft (:value e))))

(defn update-state [state text] text)

(def style-container {:min-height 40})

(defn init-state [& args] "")

(def style-reply
 {:line-height 1.6,
  :background-color (hsl 0 0 99),
  :width "auto",
  :flex 1,
  :resize "none",
  :flex-shrink 0,
  :padding "6px 8px"})

(defn on-keydown [state mutate!]
  (fn [e dispatch!]
    (if (and (= 13 (:key-code e)) (not (string/blank? state)))
      (do
        (.preventDefault (:original-event e))
        (mutate! "")
        (dispatch! :reply state)))))

(defn render [session]
  (fn [state mutate!]
    (div
      {:style (merge ui/row ui/flex style-container)}
      (textarea
        {:style (merge ui/input style-reply),
         :event
         {:keydown (on-keydown state mutate!),
          :input (on-input mutate!)},
         :attrs {:placeholder "回复...", :value state}})
      (comp-naming (:nickname session))
      (comment comp-debug session nil))))

(def comp-reply (create-comp :reply init-state update-state render))
