
(ns jestful.comp.naming
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer [create-comp div span textarea input]]
            [respo-ui.style :as ui]
            [jestful.style.widget :as widget]
            [jestful.style.layout :as layout]
            [respo.comp.debug :refer [comp-debug]]))

(defn on-input [e dispatch!] (dispatch! :naming (:value e)))

(def style-input {:background-color (hsl 0 0 100), :width "80px"})

(defn render [nickname]
  (fn [state mutate!]
    (input
      {:style (merge ui/input style-input),
       :event {:input on-input},
       :attrs {:placeholder "访客...", :value nickname}})))

(def comp-naming (create-comp :naming render))
