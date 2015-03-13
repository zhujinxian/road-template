(ns leiningen.new.road
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "road"))

(defn road
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' road project.")
    (->files data
             ["README.md" (render "README.md" data)]
             ["project.clj" (render "project.clj" data)]
             ["web.xml" (render "web.xml" data)]
             ["resources/log4j.properties" (render "resources/log4j.properties" data)]
             ["resources/views/home.clj" (render "resources/views/home.clj" data)]
             ["src/{{sanitized}}/core.clj" (render "core.clj" data)])))
