(ns sandbox.core)

(defmacro bench [n & exprs]
  `(time
     (dotimes [~'_ ~n]
       (do ~@exprs))))

(defn java-methods [java-class re-string-or-pattern]
  (let [re (re-pattern re-string-or-pattern)]
    (for [m (seq (.getMethods java-class))
          :let [m-name (.getName m)]
          :when (re-find re m-name)]
      m-name)))

(defn fetch-url[address]
  (with-open [stream (.openStream (java.net.URL. address))]
    (let  [buf (java.io.BufferedReader. 
                 (java.io.InputStreamReader. stream))]
      (apply str (line-seq buf)))))

(defmacro jna-call "Call func from lib" [lib func ret & args] 
    `(let [library#  (name ~lib)
                      function# (com.sun.jna.Function/getFunction library# ~func)] 
                  (.invoke function# ~ret (to-array [~@args]))))


(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
