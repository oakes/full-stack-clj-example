This is a sample project that demonstrates how to create a full stack (Clojure + ClojureScript) project using the [Clojure CLI](https://clojure.org/guides/getting_started) tool. With `clj -M:cljs:dev`, you can instantly see your project at [http://localhost:3000/](http://localhost:3000/) and any edits to the ClojureScript will be automatically pushed to the browser. With `clj -M:cljs:prod`, you can make a standalone jar file that includes your entire client and server code. The prod command works by programmatically using Leiningen to build an uberjar. It works even if Leiningen is not installed on your system!

## Development

* Install the latest JDK
* Install [the Clojure CLI tool](https://clojure.org/guides/getting_started#_clojure_installer_and_cli_tools)
* Develop with `clj -M:cljs:dev`
* Build JAR file with `clj -M:cljs:prod`
