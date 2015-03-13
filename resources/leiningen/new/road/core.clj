(ns {{name}}.core (:gen-class)
  (:use [road.core])
  (:require [road.core :as road]
            [ring.middleware.params :as params]
            [ring.util.response :as resp]
            [clojure.java.io :as io]
            [clojure.tools.logging :as log]
            [ring.adapter.jetty :as jetty]))

(defn render-test [ret tmt]
  (-> (resp/response (:text ret)) 
    (#(resp/content-type %1 "text/plain"))
    (#(resp/charset %1 "utf-8"))))

(defn foo
  "I don't do a whole lot."
  [x]
  (str "来自源码目录的参数：" x))

(defn index [] {:text "welcom to clojure road web framwork! fast road!!!"})

(defn handler [^Integer x ^Integer y]
    {:$r render-test :text (str "hello world, road goes sucess!" (foo x) (* x y))})

(defn home [req content ^Integer num]
    {:hiccup "home.clj" :content (str "home" content) :num num})

(defroad road (GET "/{{name}}" index) 
              (GET "/{{name}}/main" handler) 
              (GET "/{{name}}/home/:num{\\d+}" home))
 

(defn -main [& args]
  (log/info "---------log4j test-------")
  (jetty/run-jetty road {:port 3000}))
