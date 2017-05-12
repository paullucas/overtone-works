(ns otworks.c20
  (:require [overtone.core :refer :all]
            [otworks.functions :refer [get-mono-samples boot]]))

(boot)

(get-mono-samples "~/Producing/november4th-2016/chops/smpls/"
                  (mapv #(str "s" %) (range 1 9)))

(definst tg
  [dur 5 rate 1 lff 2000 hff 200 amp 1 rel 40 gate 1]
  (->
   (t-grains 1 (impulse:ar 4) s8m rate (* 0.1 (sin-osc:kr 0.03)) dur 0.5 0.7 1)
   (lpf lff)
   (hpf hff)
   (g-verb 15 6 0.5 0.5 20 0)
   (* (env-gen (asr :attack 25 :curve 1 :release rel) :gate gate :action 2))
   (* amp)))

(definst tgl
  [rate 1 amp 1.8 gate 1]
  (->
   (t-grains 1 (impulse:ar 0.15) s7m rate (* 0.1 (sin-osc:kr 0.06)) 7 0.5 1.5 1)
   (lpf 1000)
   (g-verb 15 6 0.5 0.5 20 0.2)
   (* (env-gen (asr :attack 10 :curve 1 :release 40) :gate gate :action 2))
   (* amp)))

(definst sio
  [freq 220 amp 1 rel 40 gate 1]
  (->
   (sin-osc [freq (+ freq 1)])
   (* (env-gen (asr :attack 15 :curve 1 :release rel) :gate gate :action 2))
   (* amp)))

(def tg1 (tg 5 0.6 2000 125 0.5))
(def tg2 (tgl 0.18))
(ctl tg1 :rate 0.4)
(ctl tg1 :rate 0.35)
(ctl tg2 :rate 0.4)
(ctl tg2 :rate 0.36)
(ctl tg1 :rate 0.55)
(ctl tg2 :rate 0.5 :amp 1.6)
(do
  (ctl tg1 :rate 0.2 :amp 0.45)
  (ctl tg2 :rate 0.2 :amp 1.55))
(def si1 (sio 66.5 0.5))
(ctl si1 :freq 58.4 :amp 0.35)
(def tg3 (tg 10 2 8000 400 0.29 20))
(ctl tg3 :rate 1.4)
(ctl si1 :gate 0)
(def si2 (sio 5919.91 0.020 10))
(ctl si2 :gate 0)
(ctl tg3 :gate 0)
(do
  (ctl tg1 :rate 0.36)
  (ctl tg2 :rate 0.4))
(ctl tg2 :rate 0.36)
(ctl tg1 :gate 0)
(ctl tg2 :rate 0.2)
(ctl tg2 :rate 0.18 :gate 0)

(stop)
