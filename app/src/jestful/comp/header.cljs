
(ns jestful.comp.header
  (:require [hsl.core :refer [hsl]]
            [clojure.string :as string]
            [respo.alias :refer [create-comp div span]]
            [jestful.style.widget :as widget]
            [jestful.style.layout :as layout]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.debug :refer [comp-debug]]
            [jestful.comp.naming :refer [comp-naming]]))

(def style-header
 {:color (hsl 0 0 50),
  :background-color (hsl 0 0 96),
  :padding 8,
  :height 40})

(defn render [session statistics]
  (fn [state mutate!]
    (div
      {:style style-header}
      (comp-text "参与聊天" nil)
      (comp-space "8px" nil)
      (comp-text (str (:count statistics) "人"))
      (comp-space "8px" nil)
      (comp-text (string/join ", " (:names statistics)) nil)
      (comment comp-debug session nil))))

(def comp-header (create-comp :header render))
