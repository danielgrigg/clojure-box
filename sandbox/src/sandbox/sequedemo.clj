(ns sandbox.sequedemo)

(def ^:dynamic *work-queue* (java.util.concurrent.ArrayBlockingQueue. 10))
(def ^:dynamic *consumer-active* (atom false))
(def ^:dynamic *consumer* nil)

(defn start-consumer []  
(when-not @*consumer-active*
  (do
    (swap! *consumer-active* (fn [_] true))
    (.start (Thread. 
           (fn []
             (when @*consumer-active*
               (println "Getting ready to work again")
               (println "Working hard on " (.take *work-queue*))
               (recur))))))))

  
  

(defn stop-consumer []
  (swap! *consumer-active* (fn [_] false)))