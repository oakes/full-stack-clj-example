(ns full-stack-clj-example.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.file :refer [wrap-file]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.util.response :refer [not-found]]
            [clojure.java.io :as io])
  (:gen-class))

(defmulti handler :uri)

(defmethod handler "/"
  [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (-> "public/index.html" io/resource slurp)})

(defmethod handler :default
  [request]
  (not-found "Page not found"))

(defn dev-main []
  (.mkdirs (io/file "target" "public"))
  (-> handler
      (wrap-file "target/public")
      (run-jetty {:port 3000 :join? false})))

(defn -main [& args]
  (-> handler
      (wrap-resource "public")
      (run-jetty {:port 3000 :join? false})))

