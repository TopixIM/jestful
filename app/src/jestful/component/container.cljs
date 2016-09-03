
(ns jestful.component.container
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer [create-comp div span]]
            [jestful.style.widget :as widget]
            [jestful.style.layout :as layout]
            [respo.comp.debug :refer [comp-debug]]))

(defn render [store]
  (fn [state mutate!]
    (div
      {:style (merge layout/fullscreen layout/horizontal)}
      (div {:style widget/row-divider})
      (comp-debug store {:bottom 0, :max-width "100%", :left 0}))))

(def comp-container (create-comp :container render))
