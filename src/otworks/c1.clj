(ns otworks.c1
  (:use overtone.live)
  (:require [otworks.functions :refer [get-samples]]))

(get-samples "~/Producing/April5th-2016/samples/"
             (mapv #(str "s" %) (range 1 3)))

(definst pbv
  [buf 0 amp 1 att 15 rel 40 mix 1 room 1 damp 1 gate 1 rate 1 spos 0.0]
  (->
   (play-buf 1 buf rate 1 spos 1 1)
   (free-verb mix room damp)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action FREE))
   (* amp)))

(def pbv1 (pbv s1 :amp 1.0 :att 10 :rel 20))
(def pbv2 (pbv s1 :rate 0.5 :amp 1.5 :att 15 :rel 20))
(ctl pbv1 :rate 0.8)
(ctl pbv1 :rate 0.4)
(def pbv3 (pbv s2 :rate 1.2 :amp 2 :att 10 :rel 20))
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
