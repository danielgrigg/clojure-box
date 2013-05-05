(ns seesaw-button.core
  (:gen-class)
  (:use seesaw.core))

(defn handler
  [event]
  (alert event
    (str "<html>Hello from <b>Clojure</b>. Button "
      (.getActionCommand event) " clicked.")))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
    (invoke-later
     (-> (frame :title "Hello Swing" :on-close :exit
           :content (button :text "Click Me" :listen [:action handler]))
         pack!
         show!)))
