(require
  '[cljs.build.api :as api]
  '[leiningen.core.project :as p :refer [defproject]]
  '[leiningen.clean :refer [clean]]
  '[leiningen.uberjar :refer [uberjar]])

(defn read-project-clj []
  (p/ensure-dynamic-classloader)
  (-> "project.clj" load-file var-get))

(defn read-deps-edn [aliases-to-include]
  (let [{:keys [paths deps aliases]} (-> "deps.edn" slurp clojure.edn/read-string)
        deps (->> (select-keys aliases aliases-to-include)
                  vals
                  (mapcat :extra-deps)
                  (into deps)
                  (reduce
                    (fn [deps [artifact info]]
                      (if-let [version (:mvn/version info)]
                        (conj deps
                          (transduce cat conj [artifact version]
                            (select-keys info [:scope :exclusions])))
                        deps))
                    []))]
    {:dependencies deps
     :source-paths []
     :resource-paths paths}))

(def project (-> (read-project-clj)
                 (merge (read-deps-edn []))
                 p/init-project))

(clean project)

(println "Building main.js")
(api/build "src" {:main          'full-stack-clj-example.core
                  :optimizations :advanced
                  :output-to     "target-js/public/main.js"
                  :output-dir    "target/main.out"})

(println "Building uberjar")
(uberjar project)
