(ns full-stack-clj-example.start-dev
  (:require [full-stack-clj-example.start :as start]
            [clojure.spec.test.alpha :as st]
            [ring.middleware.file :refer [wrap-file]]
            [clojure.java.io :as io]))

(defn -main []
  (st/instrument)
  (.mkdirs (io/file "target" "public"))
  (-> start/handler
      (wrap-file "target/public")
      start/run-server))
