(ns otworks.c18
  (:use overtone.live)
  (:require [otworks.functions :refer [get-samples get-mono-samples]]))

(get-samples "~/Producing/october26th-2016/smp/"
             (mapv #(str "s" %) (range 0 7)))

(get-mono-samples "~/Producing/october26th-2016/smp/"
                  (mapv #(str "s" %) (range 5 7)))

(definst pgrain
  [buf 0 dur 1 cpos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 t_trig 0 gate 1]
  (->
   (t-grains 2 (impulse:ar t_trig) buf rate (* 0.1 (sin-osc:kr cpos)) dur 0 amp 1)
   (lpf lff)
   (hpf hff)
   (free-verb 0.6 1 1)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))))

(definst pbuf
  [buf 0 start-pos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 gate 1 loop 1]
  (->
   (play-buf 2 buf rate 1 start-pos loop 2)
   (* amp)
   (lpf lff)
   (hpf hff)
   (free-verb 0.5 1 1)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))))

(definst tri
  [buf 0 amp 1 att 15 rel 40 mix 1 room 1 damp 1 gate 1 freq 440] 
  (->
   (lf-tri freq)
   (free-verb mix room damp)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action FREE))
   (* amp)))

(def x (pgrain s5m 10 0.2 0.42 5000 100 2.5 10 20 0.1))
(def y (pgrain s6m 10 0.3 0.72 7000 200 2.5 10 10 0.1))
(do
  (ctl x :rate 0.54)
  (ctl y :cpos 0.4))
(ctl x :cpos 0.5)
(ctl y :t_trig 0.3)
(def z (pbuf s5 0.0 0.4 3000 100 1 10 10))
(do
  (ctl x :rate 0.48)
  (ctl y :rate 0.62))
(def a (pbuf s2 0.0 3.2 2000 1000 0.7 10 10))
(ctl a :rate 1.2 :amp 1.4)
(ctl a :rate 0.4 :lff 1000 :hff 70)
(ctl a :lff 2000 :hff 1000)
(do
  (ctl x :rate 1.48)
  (ctl y :rate 1.62))
(do
  (ctl x :rate 2.48)
  (ctl y :rate 2.62))
(do
  (ctl x :rate 0.48)
  (ctl y :rate 0.62))
(do
  (ctl x :rate 2.48)
  (ctl y :rate 2.62))
(do
  (ctl x :rate 2.68)
  (ctl y :rate 2.82))
(do
  (ctl x :rate 2.28)
  (ctl y :rate 2.12))
(do
  (ctl x :rate 0.48)
  (ctl y :rate 0.62))
(ctl a :rate 5.2)
(ctl a :rate 0.4)
(ctl a :rate 2.5)
(ctl a :rate 0.4)
(ctl a :rate 6.1)
(ctl a :rate 0.4)
(ctl a :gate 0)
(ctl z :rate 0.8)
(ctl z :rate 0.5)
(ctl z :rate 0.8)
(ctl z :rate 0.5)
(do
  (ctl x :rate 2.28)
  (ctl y :rate 2.12))
(do
  (ctl x :rate 0.48)
  (ctl y :rate 0.62))
(ctl z :rate 0.8)
(do
  (ctl x :rate 2.48)
  (ctl y :rate 2.62))
(ctl z :rate 0.5)
(do
  (ctl x :rate 1.48)
  (ctl y :rate 1.62))
(ctl z :rate 0.4)
(do
  (ctl x :rate 0.48)
  (ctl y :rate 0.62))
(ctl z :rate 0.72)
(do
  (ctl x :rate 0.52)
  (ctl y :rate 0.66))
(do
  (ctl x :rate 0.58)
  (ctl y :rate 0.72)
  (ctl z :rate 0.42))
(do
  (ctl x :rate 0.48)
  (ctl y :rate 0.62))
(ctl z :gate 0)
(do
  (def tri1 (tri :freq 64 :amp 0.5 :rel 20))
  (def tri2 (tri :freq 65.2 :amp 0.5 :rel 20)))
(do
  (ctl tri1 :gate 0)
  (ctl tri2 :gate 0)
  (ctl y :t_trig 0.1))
(ctl y :gate 0)
(ctl x :gate 0)

(stop)
