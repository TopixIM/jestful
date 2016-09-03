
(ns jestful.comp.draft
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer [create-comp div span textarea]]
            [respo-ui.style :as ui]
            [jestful.style.widget :as widget]
            [jestful.style.layout :as layout]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.debug :refer [comp-debug]]))

(def style-draft
 {:line-height 1.6,
  :color (hsl 0 0 60),
  :min-height "37px",
  :padding 8})

(defn render [nickname text]
  (fn [state mutate!]
    (div
      {:style style-draft}
      (comp-text text nil)
      (comp-space "8px" nil)
      (comp-text nickname nil))))

(def comp-draft (create-comp :draft render))
