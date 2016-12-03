(ns otworks.c11
  (:use overtone.live)
  (:require [otworks.functions :refer [get-samples]]))

(get-samples "~/Producing/october20th-2016/sc8/"
             (mapv #(str "s" %) (range 5 9)))

(definst pbuf
  [buf 0 spos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 gate 1 loop 1]
  (->
   (play-buf 2 buf rate 1 spos loop 2)
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
  [buf 0 dur 1 cpos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 t-trig 0 gate 1 mamp 1]
  (->
   (t-grains 2 (impulse:ar t-trig) buf rate cpos dur 0 amp 2)
   (lpf lff)
   (hpf hff)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))
   (* mamp)
   (free-verb 0.5 1 1)))

(def x (pbufm s6 0.0 0.45 2000 80 0.8 10 10))
(def y (pbufm s5 0.0 0.85 2000 200 1.7 10 10))
(ctl x :rate 0.60)
(ctl y :rate 0.75)
(ctl y :rate 0.65)
(def b (pbuf s8 0.0 0.85 19000 3000 0.15 10 40 1 1))
(ctl y :gate 0)
(def z (pgrain s7 10 222 1.5 2000 1000 1 10 1 0.075 1 6))
(do (ctl x :rate 0.65)
    (ctl z :rate 1.4 :t-trig 0.065)
    (ctl b :loop 0))
(ctl z :gate 0)
(ctl x :rate 0.4)
(def z (pgrain s7 10 222 6.5 2000 1000 1 10 25 0.045 1 3))
(def b (pbuf s8 0.0 1 19000 3000 0.15 10 40 1 1))
(ctl z :rate 28 :mamp 2.85)
(ctl z :rate 38)
(ctl z :rate 0.5 :amp 7 :mamp 8 :t-trig 1.08 :lff 500)
(ctl b :loop 0)
(ctl x :gate 0)
(ctl z :gate 0)

(stop)
