
(ns jestful.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer [create-comp div span textarea]]
            [respo-ui.style :as ui]
            [jestful.style.widget :as widget]
            [jestful.style.layout :as layout]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.debug :refer [comp-debug]]
            [jestful.comp.header :refer [comp-header]]
            [jestful.comp.reply :refer [comp-reply]]
            [jestful.comp.naming :refer [comp-naming]]
            [jestful.comp.message :refer [comp-message]]
            [jestful.comp.empty :refer [comp-empty]]
            [jestful.comp.draft :refer [comp-draft]]))

(def style-container {:font-size "13px"})

(defn render [store]
  (fn [state mutate!]
    (if (some? store)
      (div
        {:style (merge ui/column style-container)}
        (comp-header (:state store) (:statistics store))
        (comp-reply (:state store))
        (div
          {:style {}}
          (->>
            (:drafts store)
            (map
              (fn [draft]
                (let [[state-id nickname text] draft]
                  [state-id (comp-draft nickname text)])))))
        (div
          {:style {}}
          (->>
            (:messages store)
            (reverse)
            (map
              (fn [message] [(:id message) (comp-message message)]))))
        (comment
          comp-debug
          (:drafts store)
          {:bottom 0, :max-width "100%", :left 0}))
      (comp-empty))))

(def comp-container (create-comp :container render))
