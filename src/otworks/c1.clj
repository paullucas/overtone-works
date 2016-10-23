(ns otworks.c1
  (:use overtone.live)
  (:require [otworks.functions :refer [get-samples gen-inst]]))

(get-samples "~/Producing/April5th-2016/samples/"
             (mapv #(str "s" %) (range 1 3)))

(gen-inst "pbv" ["free-verb" "play-buf"])

(def pbv1 (pbv one :amp 1.0 :att 10 :rel 20))
(def pbv2 (pbv one :rate 0.5 :amp 1.5 :att 15 :rel 20))
(ctl pbv1 :rate 0.8)
(ctl pbv1 :rate 0.4)
(def pbv3 (pbv two :rate 1.2 :amp 2 :att 10 :rel 20))
(ctl pbv2 :rate 0.6)
(ctl pbv1 :rate 0.8)
(ctl pbv3 :rate 1.8)
(ctl pbv2 :rate 0.4)
(ctl pbv3 :rate 1.775)
(ctl pbv1 :rate 22.8)
(ctl pbv1 :rate 45.6)
(ctl pbv1 :rate 3.2)
(ctl pbv1 :rate 1.6)
(ctl pbv1 :rate 0.4)
(ctl pbv3 :rate 1.8)
(ctl pbv3 :gate 0)
(ctl pbv1 :gate 0)
(ctl pbv2 :gate 0)

(stop)
(pp-node-tree)
