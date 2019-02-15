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

(defmethod task "run"
  [_]
  (task ["run-clj"])
  (task ["run-cljs"]))

(defmethod task "run-clj"
  [_]
  (dev-main))

(defmethod task "run-cljs"
  [_]
  (figwheel/-main "--build" "dev"))

(defmethod task "build-cljs"
  [_]
  (api/build "src" {:main          'full-stack-clj-example.core
                    :optimizations :advanced
                    :output-to     "resources/public/main.js"}))

;; tasks that use leiningen

(require
  '[leiningen.core.project :as p :refer [defproject]]
  '[leiningen.uberjar :refer [uberjar]])

(load-file "project.clj")
(p/ensure-dynamic-classloader)
(def project (p/init-project project))

(defmethod task "uberjar"
  [_]
  (task ["build-cljs"])
  (uberjar project))

;; entry point

(task *command-line-args*)

