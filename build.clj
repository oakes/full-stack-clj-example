(require
  '[cljs.build.api :as api]
  '[figwheel.main :as figwheel]
  '[full-stack-clj-example.core :refer [dev-main]]
  '[clojure.java.io :as io]
  '[clojure.edn :as edn]
  '[clojure.string :as str])

(defmulti task first)

(defmethod task :default
  [_]
  (let [all-tasks  (-> task methods (dissoc :default) keys sort)
        interposed (->> all-tasks (interpose ", ") (apply str))]
    (println "Unknown or missing task. Choose one of:" interposed)
    (System/exit 1)))

(defmethod task "dev"
  [_]
  (task ["dev-clj"])
  (task ["dev-cljs"]))

(defmethod task "dev-clj"
  [_]
  (dev-main))

(defmethod task "dev-cljs"
  [_]
  (figwheel/-main "--build" "dev"))

(defmethod task "prod-cljs"
  [_]
  (api/build "src" {:main          'full-stack-clj-example.core
                    :optimizations :advanced
                    :output-to     "resources/public/main.js"}))

;; tasks that use leiningen

(require
  '[leiningen.core.project :as p :refer [defproject]]
  '[leiningen.uberjar :refer [uberjar]])

(defn read-project-clj []
  (p/ensure-dynamic-classloader)
  (-> "project.clj" load-file var-get))

(defmethod task "prod"
  [_]
  (task ["prod-cljs"])
  (-> (read-project-clj) p/init-project uberjar))

;; entry point

(task *command-line-args*)

