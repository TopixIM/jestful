
(ns jestful.comp.empty
  (:require [hsl.core :refer [hsl]]
            [clojure.string :as string]
            [respo.alias :refer [create-comp div span textarea]]
            [respo-ui.style :as ui]
            [jestful.style.widget :as widget]
            [jestful.style.layout :as layout]
            [respo.comp.debug :refer [comp-debug]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.text :refer [comp-text]]))

(def style-empty
 {:line-height 2,
  :color (hsl 0 0 100),
  :background-color (hsl 200 80 40),
  :padding "40px"})

(defn render []
  (fn [state mutate!]
    (div
      {:style (merge ui/fullscreen style-empty)}
      (comp-text "我在测试... \b如果服务器断开, 等待 8s 自动刷新~" nil)
      (comp-space nil "40px")
      (comp-text "不过要是等了一分钟, 还是手动刷吧..." nil))))

(def comp-empty (create-comp :empty render))
