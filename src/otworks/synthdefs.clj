(ns otworks.synthdefs
  (:require [overtone.core :refer [definst]]))

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

(definst rsin
  [dens 100 rmul 1000 lff 2000 hff 200 amp 1 att 15 rel 40 rmix 0.5 gate 1]
  (->
   (sin-osc (* rmul (lf-noise1:kr dens)))
   (* amp)
   (lpf lff)
   (hpf hff)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))
   (free-verb rmix 1 1)))
