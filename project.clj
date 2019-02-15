(defproject full-stack-clj-example "0.1.0-SNAPSHOT"
  :plugins [[lein-tools-deps "0.4.3"]]
  :middleware [lein-tools-deps.plugin/resolve-dependencies-with-deps-edn]
  :lein-tools-deps/config {:config-files [:install :user :project]}
  :aot [full-stack-clj-example.core]
  :main full-stack-clj-example.core)
