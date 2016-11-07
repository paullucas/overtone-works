(ns otworks.c19
  (:use overtone.live)
  (:require [otworks.functions :refer [get-samples get-mono-samples]]))

(get-samples "~/Producing/november4th-2016/chops/smpls/"
             (mapv #(str "s" %) (range 1 9)))

(get-mono-samples "~/Producing/november4th-2016/chops/smpls/"
                  (mapv #(str "s" %) (range 1 9)))

(definst pgrain [buf 0 dur 1 cpos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 t_trig 0 gate 1]
  (->
   (t-grains 2 (impulse:ar t_trig) buf rate (* 0.1 (sin-osc:kr cpos)) dur 0 amp 1)
   (lpf lff)
   (hpf hff)
   (free-verb 0.6 1 1)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))))

(def x (pgrain s5m 10 0.2 0.54 7000 50 2.5 10 20 0.1))
(def y (pgrain s4m 10 0.2 0.4 8000 100 2.5 10 10 0.1))
(def z (pgrain s3m 10 0.25 0.72 8000 50 2.5 10 10 0.1))

(ctl x :rate 3.9)
(ctl x :rate 0.7)
(ctl x :rate 2.1)
(ctl x :rate 5.7)
(ctl x :rate 0.95)
(ctl x :rate 8)
(ctl x :rate 2 :cpos 0.35)
(ctl x :rate 0.7 :cpos 0.15)
(ctl x :rate 2 :cpos 0.75)

(stop)
