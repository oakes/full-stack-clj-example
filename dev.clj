(require
  '[figwheel.main :as figwheel]
  '[full-stack-clj-example.start-dev :refer [-main]])

(-main)
(figwheel/-main "--build" "dev")

