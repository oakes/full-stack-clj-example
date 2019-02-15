(require
  '[cljs.build.api :as api]
  '[figwheel-sidecar.repl-api :as repl-api]
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
  (future (task ["run-cljs"]))
  (task ["run-clj"]))

(defmethod task "run-clj"
  [_]
  (dev-main))

(def cljs-config {:main          'full-stack-clj-example.core
                  :optimizations :none
                  :output-to     "target/public/main.js"
                  :output-dir    "target/public/main"
                  :asset-path    "/main"})

(defmethod task "run-cljs"
  [_]
  (repl-api/start-figwheel! {:all-builds [{:id "dev"
                                           :figwheel true
                                           :source-paths ["src"]
                                           :compiler cljs-config}]}))

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

