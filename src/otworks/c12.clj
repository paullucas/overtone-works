(ns otworks.c12
  (:use overtone.live)
  (:require [otworks.functions :refer [get-samples]]))

(get-samples "~/Producing/october20th-2016/sc8/"
             (mapv #(str "s" %) (range 9 13)))

(definst pbuf [buf 0 start-pos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 gate 1 loop 1]
  (->
   (play-buf 2 buf rate 1 start-pos loop 2)
   (* amp)
   (lpf lff)
   (hpf hff)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))
   (free-verb 0.5 1 1)))

(def x (pbuf s9 0.0 0.8 1000 60 0.7 10 10))
(def y (pbuf s12 0.0 1.25 6000 800 0.8 10 10))
(def z (pbuf s10 0.0 1.8 2000 200 0.8 10 20))
(ctl z :rate 1.4)
(ctl x :rate 0.82)
(ctl y :gate 0)
(ctl z :rate 1)
(ctl x :rate 0.7)
(def a (pbuf s11 0.0 2 3500 150 1 10 20))
(def y (pbuf s12 0.0 1.05 6000 3000 0.8 10 10))
(ctl x :gate 0)
(ctl z :rate 0.8)
(ctl a :rate 0.8)
(ctl y :rate 1.2)
(ctl z :gate 0)
(ctl y :gate 0)
(ctl a :gate 0)

(stop)
