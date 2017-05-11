(ns otworks.c2
  (:require [overtone.core]
            [otworks.functions :refer [get-samples]]))

(connect-external-server)

(get-samples "~/Producing/june13th-2016/"
             (mapv #(str "spl" %) (range 1 5)))

(definst wrpr
  [buf 0 ptr 0 amp 1 att 15 rel 40 mix 1 room 1 damp 1 gate 1
   rate 1 fscale 1 wsize 0.1 ebn -1 olaps 1 interp 4] 
  (->
   (warp1 1 buf ptr fscale wsize ebn olaps 0.0 interp)   
   (free-verb mix room damp)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action FREE))
   (* amp)))

(definst wrplf
  [buf 0 ptr 0 amp 1 att 15 rel 40 lff 2000 mix 1 room 1 damp 1
   gate 1 rate 1 fscale 1 wsize 0.1 ebn -1 olaps 1 interp 4] 
  (->
   (warp1 1 buf ptr fscale wsize ebn olaps 0.0 interp)   
   (lpf lff)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action FREE))
   (* amp)))

(def wrpr1 (wrpr spl1 :ptr 0.83 :fscale 0.4 :wsize 10.0 :amp 1.5))
(def wrplf1 (wrplf spl3 :lff 1000 :ptr 0.23 :fscale 0.8 :wsize 16.0 :amp 1.8))
(def wrpr2 (wrpr spl2 :ptr 0.27 :fscale 0.55 :wsize 9.0 :amp 0.7))
(def wrplf2 (wrplf spl4 :lff 2000 :ptr 0.27 :fscale 2.3 :wsize 12.0 :amp 1.9))
(ctl wrpr1 :gate 0)
(ctl wrpr2 :gate 0)
(ctl wrplf2 :olaps 5)
(ctl wrplf1 :olaps 3)
(ctl wrplf2 :fscale 2.8)
(ctl wrplf1 :fscale 1.0)
(ctl wrplf2 :fscale 2.2)
(ctl wrplf1 :fscale 2.0)
(ctl wrplf2 :fscale 1.2)
(ctl wrplf1 :fscale 2.3)
(def wrpr3 (wrpr spl2 :ptr 0.67 :fscale 9.55 :wsize 3.0 :amp 0.7))
(ctl wrpr3 :fscale 1.2 :wsize 11.4)
(ctl wrpr3 :fscale 0.55 :wsize 14 :amp 0.8)
(ctl wrpr3 :gate 0)
(ctl wrplf2 :fscale 0.8)
(ctl wrplf1 :fscale 0.8)
(ctl wrplf2 :olaps 1)
(ctl wrplf2 :gate 0)
(ctl wrplf1 :olaps 1)
(ctl wrplf1 :gate 0)

(stop)
