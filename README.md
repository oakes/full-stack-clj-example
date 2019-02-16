This is a sample project that demonstrates how to create a full stack (Clojure + ClojureScript) project using the [Clojure CLI](https://clojure.org/guides/getting_started) tool. With `clj -A:dev dev.clj`, you can instantly see your project at [http://localhost:3000/](http://localhost:3000/) and any edits to the ClojureScript will be automatically pushed to the browser. With `clj -A:prod prod.clj`, you can make a standalone jar file that includes your entire client and server code. The prod command works by programmatically using Leiningen to build an uberjar. It works even if Leiningen is not installed on your system!

## Build Instructions

* Install the latest JDK
* Install [the clj tool](https://clojure.org/guides/getting_started)
* Develop with `clj -A:dev dev.clj`
* Build JAR file with `clj -A:prod prod.clj`

## Contents

* `resources` The assets
* `src/full_stack_clj_example/core.clj` The server-side code
* `src/full_stack_clj_example/core.cljs` The client-side code
* `src/full_stack_clj_example/utils.cljc` The client and server agnostic code
