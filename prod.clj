(require
  '[cljs.build.api :as api]
  '[leiningen.core.project :as p :refer [defproject]]
  '[leiningen.clean :refer [clean]]
  '[leiningen.uberjar :refer [uberjar]])

(defn read-project-clj []
  (p/ensure-dynamic-classloader)
  (-> "project.clj" load-file var-get))

(def project (p/init-project (read-project-clj)))

(clean project)

(println "Building main.js")
(api/build "src" {:main          'full-stack-clj-example.core
                  :optimizations :advanced
                  :output-to     "target-cljs/public/main.js"
                  :output-dir    "target/main.out"})

(println "Building uberjar")
(uberjar project)
