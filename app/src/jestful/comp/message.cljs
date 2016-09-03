
(ns jestful.comp.message
  (:require [hsl.core :refer [hsl]]
            [clojure.string :as string]
            [respo.alias :refer [create-comp div span textarea]]
            [respo-ui.style :as ui]
            [jestful.style.widget :as widget]
            [jestful.style.layout :as layout]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.debug :refer [comp-debug]]
            [jestful.comp.header :refer [comp-header]]
            [jestful.util.format :refer [display-time]]))

(def style-name
 {:color (hsl 0 0 80), :overflow "hidden", :flex-shrink 0})

(def style-content {:min-width "40px"})

(def style-message
 {:line-height 1.6,
  :font-size "13px",
  :padding "8px 8px",
  :border-bottom (str "1px solid " (hsl 0 0 100))})

(defn render [message]
  (fn [state mutate!]
    (let [nickname (:author-name message)]
      (div
        {:style (merge ui/row style-message)}
        (div {:style style-content} (comp-text (:text message) nil))
        (comp-space "16px" nil)
        (div
          {:style style-name}
          (comp-text (if (string/blank? nickname) "访客" nickname) nil)
          (comp-space "8px" nil)
          (comp-text (display-time (:time message)) nil))))))

(def comp-message (create-comp :message render))
