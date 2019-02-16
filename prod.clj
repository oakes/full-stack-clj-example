(require
  '[cljs.build.api :as api]
  '[leiningen.core.project :as p :refer [defproject]]
  '[leiningen.uberjar :refer [uberjar]])

(defn read-project-clj []
  (p/ensure-dynamic-classloader)
  (-> "project.clj" load-file var-get))

(println "Building main.js")
(api/build "src" {:main          'full-stack-clj-example.core
                  :optimizations :advanced
                  :output-to     "resources/public/main.js"
                  :output-dir    "target/public/main.out"})
(println "Building uberjar")
(-> (read-project-clj) p/init-project uberjar)
