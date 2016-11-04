(ns otworks.m3
  (:use overtone.live)
  (:require [otworks.functions :refer [get-samples gen-inst]]))

(get-samples "~/Producing/november2nd-2016/spear/smpls/"
             (mapv #(str "s" % "a") (range 1 6)))

(get-samples "~/Producing/october20th-2016/sc8/"
             (mapv #(str "s" %) (range 0 18)))

(definst pbuf [buf 0 start-pos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 gate 1 loop 1]
  (->
   (play-buf 2 buf rate 1 start-pos loop 2)
   (* amp)
   (lpf lff)
   (hpf hff)
   (free-verb 0.5 1 1)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))))

(definst pgrain [buf 0 dur 1 cpos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 t_trig 0 gate 1]
  (->
   (t-grains 2 (impulse:ar t_trig) buf rate (* 0.1 (sin-osc:kr cpos)) dur 0 amp 1)
   (lpf lff)
   (hpf hff)
   (free-verb 0.6 1 1)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))))

(definst pbufm [buf 0 start-pos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 gate 1]
    (->
     (play-buf 1 buf rate 1 start-pos 1 2)
     (* amp)
     (lpf lff)
     (hpf hff)
     (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))
     (free-verb 0.5 1 1)))

(gen-inst "wrprlfo" ["free-verb" "lpf" "warp1-lfo"])
(gen-inst "tri" ["free-verb" "lf-tri"])

(def x (pgrain s4a 2 0.3 0.92 5000 100 2.5 1 20 0.5))
(def y (pgrain s3a 2 0.3 0.92 5000 100 2.5 1 20 0.5))
(ctl y :rate 1.2 :cpos 0.4 :hff 80 :amp 2)
(ctl y :rate 1.12 :cpos 0.6)

(ctl y :rate 1.08 :cpos 0.5)
(ctl y :rate 1.10 :cpos 0.5)
(ctl y :gate 0)

(ctl y :rate 0.92 :cpos 0.6)

(ctl y :rate 1.12 :cpos 0.6)
(ctl y :rate 1.92 :cpos 0.6)
(ctl y :rate 0.62 :cpos 0.5)
(def x (pgrain s1a 10 0.3 0.92 5000 100 2.5 1 20 0.5))

(ctl x :rate 1.9 :cpos 0.3)
(ctl x :rate 1.9 :cpos 0.1)

(ctl x :rate 0.8 :cpos 0.2)
(def z (pgrain s2a 10 0.3 1.2 9000 80 2.5 1 20 0.5))

(ctl z :rate 1.9 :cpos 0.3)
(ctl x :rate 1.8 :cpos 0.2)

(ctl x :rate 2.2 :cpos 0.6)
(ctl z :rate 0.7 :cpos 0.25)

(ctl x :rate 1.7 :cpos 0.25)

(def a (pgrain s5a 2 0.3 0.92 5000 100 2.5 1 20 0.5))
(ctl a :gate 0)

(def x (pbufm s5a (/ @sample-rate* 4) 0.8 1500 150 2 10 1))
(def y (pbufm s2a (/ @sample-rate* 4) 1.2 4500 150 2 10 1))
(ctl y :rate 1.4)
(ctl x :rate 1.1)
(ctl x :rate 0.875)

(ctl x :rate 0.825)
(ctl y :rate 0.875)
(do
  (ctl x :rate 1.825)
  (ctl y :rate 1.875))

(do
  (ctl x :rate 1.225)
  (ctl y :rate 1.275))
(do
  (ctl x :rate 0.825)
  (ctl y :rate 0.875))
(do
  (ctl x :rate 1.975)
  (ctl y :rate 2.025))

(do
  (ctl x :rate 1.225)
  (ctl y :rate 1.275))
(do
  (ctl x :rate 0.825)
  (ctl y :rate 0.875))
(do
  (ctl x :rate 1.625)
  (ctl y :rate 1.675))
(do
  (ctl x :rate 1.225)
  (ctl y :rate 1.275))

(do
  (ctl x :rate 0.625)
  (ctl y :rate 0.675))
(do
  (ctl x :rate 0.675)
  (ctl y :rate 0.725))


(do
  (ctl x :rate 0.965)
  (ctl y :rate 0.935))
(do
  (ctl x :rate 1.665)
  (ctl y :rate 1.535))

(do
  (ctl x :rate 1.625)
  (ctl y :rate 1.675))

(do
  (ctl x :rate 0.825)
  (ctl y :rate 0.875))
(stop)

(def x (pbufm s4a (/ @sample-rate* 4) 0.8 1500 150 2 10 1))

(def y (pbufm s3a (/ @sample-rate* 4) 1.2 4500 150 2 10 1))

(do
  (ctl x :rate 0.525)
  (ctl y :rate 0.575))
(do
  (ctl x :rate 0.825)
  (ctl y :rate 0.8926))

(do
  (ctl x :rate 0.925)
  (ctl y :rate 1.126))

(do
  (ctl x :rate 0.825)
  (ctl y :rate 1.116))

(do
  (ctl x :rate 0.425)
  (ctl y :rate 0.816))

(do
  (ctl x :rate 0.825)
  (ctl y :rate 1.116))

(stop)
(def a (wrprlfo s3a :pointer 2.3 :freq-scale 1.38 :window-size 13 :amp 2))

(def b (wrprlfo s2a :pointer 2.3 :freq-scale 0.9 :window-size 13 :amp 2))
(do
  (ctl a :freq-scale 1.24)
  (ctl b :freq-scale 1.24))
(do
  (ctl a :freq-scale 1.04)
  (ctl b :freq-scale 1.04))

(do
  (ctl a :freq-scale 1.14)
  (ctl b :freq-scale 2.04))

(do
  (ctl a :freq-scale 2.14 :amp 2 :hff 80 :lff 6000)
  (ctl b :freq-scale 2.24 :amp 2 :hff 80 :lff 6000))

(do
  (ctl a :freq-scale 0.99)
  (ctl b :freq-scale 0.94 :window-size 14))

(do
  (def x (pbufm s4a (/ @sample-rate* 4) 0.8 1500 150 2 10 1))
  (def y (pbufm s3a (/ @sample-rate* 4) 1.2 4500 150 2 10 1)))

(do
  (ctl x :rate 0.525)
  (ctl y :rate 0.575))

(do
  (ctl x :rate 0.825)
  (ctl y :rate 0.8926))

(do
  (ctl x :rate 0.825)
  (ctl y :rate 1.116))

(do
  (ctl x :rate 0.425)
  (ctl y :rate 0.816))

(do
  (ctl a :freq-scale 1.04)
  (ctl b :freq-scale 1.04))

(do
  (ctl x :rate 0.825)
  (ctl y :rate 1.116))

(do
  (ctl x :rate 1.625)
  (ctl y :rate 1.675))

(do
  (ctl x :gate 0)
  (ctl y :gate 0))

(do
  (ctl a :gate 0)
  (ctl b :gate 0))

(stop)

(do
  (def x (pbufm s15 (/ @sample-rate* 4) 1.8 1500 150 2 10 1))
  (def y (pbufm s10 (/ @sample-rate* 4) 1.5 4500 150 2 10 1)))

(ctl x :rate 0.9)
(ctl y :rate 2.25)

(stop)
