
(ns jestful.util.format)

(defn display-time [time]
  (let [the-time (js/Date. time)
        hours (.getHours the-time)
        mins (.getMinutes the-time)]
    (str hours ":" mins)))
