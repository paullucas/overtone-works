(ns otworks.c10
  (:require [overtone.core]
            [otworks.functions :refer [get-samples]]))

(connect-external-server)

(get-samples "~/Producing/october20th-2016/sc8/"
             (mapv #(str "s" %) (range 2 5)))

(definst pbuf
  [buf 0 spos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 gate 1]
  (->
   (play-buf 2 buf rate 1 spos 1 2)
   (* amp)
   (lpf lff)
   (hpf hff)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))
   (free-verb 0.5 1 1)))

(definst pbufm
  [buf 0 spos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 gate 1]
  (->
   (play-buf 1 buf rate 1 spos 1 2)
   (* amp)
   (lpf lff)
   (hpf hff)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))
   (free-verb 0.5 1 1)))

(definst pgrain
  [buf 0 dur 1 cpos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 t_trig 0 gate 1]
  (->
   (t-grains 2 (impulse:ar t_trig) buf rate cpos dur 0 amp 1)
   (lpf lff)
   (hpf hff)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))
   (free-verb 0.5 1 1)))

(def x (pbuf s2 0 0.4 4000 100 0.8 10 10))
(def y (pbufm s3 0 0.5 2000 50 0.93 10 10))
(ctl x :gate 0)
(def x (pbuf s2 0 0.3 3000 100 0.8 10 10))
(ctl y :gate 0)
(def y (pbufm s3 0 0.3 2000 30 0.93 10 10))
(ctl x :gate 0)
(def x (pbuf s2 0 0.27 4000 80 0.8 10 10))
(ctl x :gate 0)
(def x (pbuf s2 0 0.2 3500 100 0.8 10 10))
(def z (pgrain s4 25 1222 0.8 2000 1000 0.9 10 20 0.165))
(ctl x :gate 0)
(ctl y :gate 0)
(ctl z :gate 0)

(stop)
