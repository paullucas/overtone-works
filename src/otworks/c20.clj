(ns otworks.c20
  (:use overtone.live)
  (:require [otworks.functions :refer [get-mono-samples]]))

(get-mono-samples "~/Producing/november4th-2016/chops/smpls/"
                  (mapv #(str "s" %) (range 1 9)))

(definst tgg
  [buf 0 dur 1 cpos 0.0 rate 1  lff 2000 hff 200 amp 1 att 15 rel 40 trig 0 gate 1]
  (->
   (t-grains 1 (impulse:ar trig) buf rate (* 0.1 (sin-osc:kr cpos)) dur 0.5 0.75 1)
   (lpf lff)
   (hpf hff)
   (g-verb 15 6 0.5 0.5 20 0)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))
   (* amp)))

(definst tgl
  [buf 0 dur 1 cpos 0.0 rate 1  lff 2000 amp 1 att 15 rel 40 trig 0 gate 1]
  (->
   (t-grains 1 (impulse:ar trig) buf rate (* 0.1 (sin-osc:kr cpos)) dur 0.5 1.5 1)
   (lpf lff)
   (g-verb 15 6 0.5 0.5 20 0.2)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))
   (* amp)))

(definst sio
  [freq 220 amp 1 att 15 rel 40 gate 1]
  (->
   (sin-osc [freq (+ freq 1)])
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))
   (* amp)))

(def tg1 (tgg s8m 5 0.03 0.6 2000 125 0.5 25 40 4))
(def tg2 (tgl s7m 7 0.06 0.2 1000 1.8 10 40 0.15))
(ctl tg1 :rate 0.4)
(ctl tg1 :rate 0.35)
(ctl tg2 :rate 0.4)
(ctl tg2 :rate 0.36)
(ctl tg1 :rate 0.55)
(ctl tg2 :rate 0.46)
(do
  (ctl tg2 :rate 0.2)
  (ctl tg1 :rate 0.2))
(def si1 (sio 66.5 0.5))
(ctl si1 :freq 58.4)
(def tg3 (tgg s8m 10 0.03 2 8000 400 0.45 25 20 4))
(ctl tg3 :rate 1.4)
(ctl si1 :gate 0)
(def si2 (sio 5919.91 0.020 15 10))
(ctl si2 :gate 0)
(ctl tg3 :gate 0)
(do
  (ctl tg2 :rate 0.4)
  (ctl tg1 :rate 0.36))
(ctl tg1 :gate 0)
(ctl tg2 :rate 0.2)
(ctl tg2 :gate 0)

(stop)
