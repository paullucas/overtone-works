(ns otworks.c4
  (:require [overtone.core]
            [otworks.functions :refer [get-samples]]))

(connect-external-server)

(get-samples "~/Producing/july3rd-2016/samples/"
             (mapv #(str "smp" %) (range 1 5)))

(definst wrprlf
  [buf 0 ptr 0 amp 1 att 15 rel 40 lff 2000 mix 1 room 1 damp 1
   gate 1 rate 1 fscale 1 wsize 0.1 ebn -1 olaps 1 interp 4] 
  (->
   (warp1 1 buf ptr fscale wsize ebn olaps 0.0 interp)
   (lpf lff)
   (free-verb mix room damp)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action FREE))
   (* amp)))

(definst wrprlfo
  [buf 0 ptr 0 amp 1 att 15 rel 40 lff 2000 mix 1 room 1 damp 1
   gate 1 rate 1 fscale 1 wsize 0.1 ebn -1 olaps 1 interp 4] 
  (->
   (warp1 1 buf (+ 0.4 (* 0.1 (sin-osc:kr ptr))) fscale wsize ebn olaps 0.0 interp)
   (lpf lff)
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

(definst wrphf
  [buf 0 ptr 0 amp 1 att 15 rel 40 hff 200 mix 1 room 1 damp 1
   gate 1 rate 1 fscale 1 wsize 0.1 ebn -1 olaps 1 interp 4] 
  (->
   (warp1 1 buf ptr fscale wsize ebn olaps 0.0 interp)   
   (hpf hff)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action FREE))
   (* amp)))

(def wrplf1 (wrplf smp1 :ptr 0.83 :fscale 0.4 :wsize 14 :amp 1.5 :lff 3000))
(def wrprlf1 (wrprlf smp2 :ptr 0.1 :fscale 0.8 :wsize 8 :amp 1.1 :lff 5000))
(def wrprlf2 (wrprlf smp4 :ptr 0.01 :fscale 0.8 :wsize 11 :amp 1.7 :lff 8000 :olaps 3))
(ctl wrprlf2 :amp 1.7 :ptr 0.4 :wsize 8 :fscale 0.35)
(ctl wrprlf1 :ptr 0.2 :wsize 6 :fscale 0.6)
(ctl wrprlf1 :ptr 0.2 :wsize 6 :fscale 0.42)
(ctl wrplf1 :ptr 0.4 :wsize 13 :fscale 0.63)
(ctl wrplf1 :ptr 0.4 :wsize 13 :fscale 0.55)
(ctl wrprlf2 :gate 0)
(ctl wrplf1 :ptr 0.4 :wsize 13 :fscale 0.51)
(ctl wrprlf1 :gate 0)
(ctl wrplf1 :ptr 0.4 :wsize 13 :fscale 0.48)
(ctl wrplf1 :gate 0)
(def wrprlfo1 (wrprlfo smp1 :ptr 2.3 :fscale 0.4 :wsize 13 :amp 1.5))
(def wrprlfo2 (wrprlfo smp3 :ptr 3.6 :fscale 0.7 :wsize 13))
(def wrprlfo3 (wrprlfo smp4 :ptr 2.8 :fscale 0.8 :wsize 10 :amp 1.7))
(ctl wrprlfo3 :ptr 2.8 :wsize 11 :fscale 0.95)
(ctl wrprlfo3 :ptr 2.8 :wsize 10 :fscale 0.875)
(ctl wrprlfo2 :ptr 3.2 :wsize 12 :fscale 0.82)
(ctl wrprlfo2 :fscale 0.8)
(ctl wrprlfo1 :ptr 2.4 :fscale 0.8 :wsize 9)
(ctl wrprlfo1 :ptr 2.4 :fscale 0.6 :wsize 9)
(ctl wrprlfo2 :gate 0)
(ctl wrprlfo3 :gate 0)
(ctl wrprlfo1 :ptr 2.9 :fscale 0.45 :wsize 6)
(ctl wrprlfo1 :ptr 2.91 :fscale 0.48 :wsize 4)
(def wrphf1 (wrphf smp1 :ptr 2.3 :fscale 6.2 :wsize 0.2 :amp 0.8))
(ctl wrphf1 :ptr 1.3 :fscale 3.1 :wsize 12)
(ctl wrphf1 :ptr 1.1 :fscale 2.7 :wsize 11)
(ctl wrphf1 :ptr 0.1 :fscale 0.7 :wsize 8)
(ctl wrphf1 :ptr 1.3 :fscale 4.4 :wsize 9.02)
(ctl wrphf1 :ptr 0.1 :fscale 0.7 :wsize 8)
(ctl wrphf1 :gate 0)
(ctl wrprlfo1 :gate 0)

(stop)
