This is a sample project that demonstrates how to create a full stack (Clojure + ClojureScript) project using the [Clojure CLI](https://clojure.org/guides/getting_started) tool. With `clj -A:dev build.clj run`, you can instantly see your project at [http://localhost:3000/](http://localhost:3000/) and any edits to the ClojureScript will be automatically pushed to the browser. With `clj -A:dev build.clj uberjar`, you can make a standalone jar file that includes your entire client and server code.

The uberjar command works by programmatically using Leiningen. It works even if Leiningen is not installed on your system! You may also run this project with Leiningen, and it will read the dependencies from deps.edn. For example, with `clj -A:dev build.clj build-cljs && lein run` it will use the clj tool to build the ClojureScript and then run the server with Leiningen.

## Build Instructions

* Install the latest JDK
* Install [the clj tool](https://clojure.org/guides/getting_started)
* Develop with `clj -A:dev build.clj run`
* Build JAR file with `clj -A:dev build.clj uberjar`

## Contents

* `resources` The assets
* `src/clj` The server-side code
* `src/cljc` The client and server agnostic code
* `src/cljs` The client-side code
