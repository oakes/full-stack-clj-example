(ns full-stack-clj-example.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.file :refer [wrap-file]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.content-type :refer [wrap-content-type]]
            [ring.util.response :refer [not-found]]
            [clojure.java.io :as io])
  (:gen-class))

(def port 3000)

(defmulti handler :uri)

(defmethod handler "/"
  [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (-> "public/index.html" io/resource slurp)})

(defmethod handler :default
  [request]
  (not-found "Page not found"))

(defn run-server [handler-fn]
  (run-jetty (wrap-resource handler-fn "public") {:port port :join? false})
  (println (str "Started server on http://localhost:" port)))

(defn dev-main []
  (.mkdirs (io/file "target" "public"))
  (-> handler
      (wrap-file "target/public")
      run-server))

(defn -main [& args]
  (run-server handler))

