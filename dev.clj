(require
  '[figwheel.main :as figwheel]
  '[full-stack-clj-example.core :refer [dev-main]])

(dev-main)
(figwheel/-main "--build" "dev")

