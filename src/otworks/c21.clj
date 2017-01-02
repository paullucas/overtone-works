(ns otworks.c21
  (:use overtone.live)
  (:require [otworks.functions :refer [get-samples get-mono-samples]]))

(get-mono-samples "~/Producing/november4th-2016/chops/smpls/"
                  (mapv #(str "s" %) (range 1 9)))

(get-samples "~/Producing/october20th-2016/ot16/"
             (mapv #(str "s" %) (range 0 9)))

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

(definst wrphf
  [buf 0 ptr 0 amp 1 att 15 rel 40 hff 200 mix 1 room 1 damp 1
   gate 1 rate 1 fscale 1 wsize 0.1 ebn -1 olaps 1 interp 4] 
  (->
   (warp1 1 buf ptr fscale wsize ebn olaps 0.0 interp)   
   (hpf hff)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action FREE))
   (* amp)))

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

(definst tri
  [buf 0 amp 1 att 15 rel 40 mix 1 room 1 damp 1 gate 1 freq 440] 
  (->
   (lf-tri freq)
   (free-verb mix room damp)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action FREE))
   (* amp)))

;;

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

;;

(def wrplf1 (wrplf s4 :ptr 0.83 :fscale 0.4 :wsize 10 :amp 1.5 :lff 4000))
(def wrpr1 (wrpr s5 :ptr 0.27 :fscale 0.5 :wsize 9 :amp 1.2))
(ctl wrplf1 :fscale 0.25)
(ctl wrplf1 :wsize 12)
(ctl wrpr1 :fscale 0.5 :wsize 9)
(ctl wrpr1 :fscale 0.675 :wsize 13)
(ctl wrpr1 :olaps 2)
(ctl wrplf1 :fscale 0.4 :wsize 14)
(ctl wrplf1 :olaps 3)
(ctl wrpr1 :fscale 0.275 :wsize 10)
(ctl wrplf1 :gate 0)
(ctl wrpr1 :olaps 1)
(ctl wrpr1 :fscale 0.22)
(def wrphf1 (wrphf s6 :ptr 0.23 :fscale 0.8 :wsize 16.0 :amp 1.4 :hff 400))
(ctl wrphf1 :ptr 0.7)
(ctl wrphf1 :fscale 1.1)
(ctl wrphf1 :fscale 0.65 :ptr 0.1 :olaps 2)
(ctl wrpr1 :gate 0)
(ctl wrphf1 :gate 0)

;;

(def wrplf1 (wrplf s0 :ptr 0.83 :fscale 0.4 :wsize 14 :amp 1.5 :lff 3000))
(def wrplf2 (wrprlf s1 :ptr 0.1 :fscale 0.8 :wsize 8 :amp 1.1 :lff 5000))
(def wrplf3 (wrprlf s3 :ptr 0.01 :fscale 0.8 :wsize 11 :amp 1.7 :lff 8000 :olaps 3))
(ctl wrplf3 :amp 1.7 :ptr 0.4 :wsize 8 :fscale 0.35)
(ctl wrplf2 :ptr 0.2 :wsize 6 :fscale 0.6)
(ctl wrplf2 :fscale 0.42)
(ctl wrplf1 :ptr 0.4 :wsize 13 :fscale 0.63)
(ctl wrplf1 :fscale 0.55)
(ctl wrplf3 :gate 0)
(ctl wrplf1 :ptr 0.4 :wsize 13 :fscale 0.51)
(ctl wrplf2 :gate 0)
(ctl wrplf1 :fscale 0.48)
(ctl wrplf1 :gate 0)
(def wrprlfo1 (wrprlfo s0 :ptr 2.3 :fscale 0.4 :wsize 13 :amp 1.5))
(def wrprlfo2 (wrprlfo s2 :ptr 3.6 :fscale 0.7 :wsize 13))
(def wrprlfo3 (wrprlfo s3 :ptr 2.8 :fscale 0.8 :wsize 10 :amp 1.7))
(ctl wrprlfo3 :ptr 2.8 :wsize 11 :fscale 0.95)
(ctl wrprlfo3 :wsize 10 :fscale 0.875)
(ctl wrprlfo2 :ptr 3.2 :wsize 12 :fscale 0.82)
(ctl wrprlfo2 :fscale 0.8)
(ctl wrprlfo1 :ptr 2.4 :fscale 0.8 :wsize 9)
(ctl wrprlfo1 :fscale 0.6)
(ctl wrprlfo2 :gate 0)
(ctl wrprlfo3 :gate 0)
(ctl wrprlfo1 :ptr 2.9 :fscale 0.45 :wsize 6)
(ctl wrprlfo1 :ptr 2.91 :fscale 0.48 :wsize 4)
(def wrphf1 (wrphf s0 :ptr 2.3 :fscale 6.2 :wsize 0.2 :amp 0.8))
(ctl wrphf1 :ptr 1.3 :fscale 3.1 :wsize 12)
(ctl wrphf1 :ptr 1.1 :fscale 2.7 :wsize 11)
(ctl wrphf1 :ptr 0.1 :fscale 0.7 :wsize 8)
(ctl wrphf1 :ptr 1.3 :fscale 4.4 :wsize 9.02)
(ctl wrphf1 :ptr 0.1 :fscale 0.7 :wsize 8)
(ctl wrprlfo1 :gate 0)
(ctl wrphf1 :gate 0)
(ctl wrphf1 :hff 100)

;; 

(def tri1 (tri :freq 64 :amp 0.4))
(def tri2 (tri :freq 72 :amp 0.4 :att 20))
(def tri3 (tri :freq 68 :amp 0.4 :att 20))
(ctl tri3 :freq 58)
(ctl tri2 :freq 59)
(ctl tri1 :freq 60)
(ctl tri1 :freq 55)
(ctl tri1 :freq 82.41)
(ctl tri2 :freq 61.74)
(ctl tri3 :freq 65.41)
(def s2-smplr (wrprlfo s7 :ptr 2.3 :fscale 0.6 :wsize 13 :amp 0.9))
(ctl s2-smplr :fscale 0.575 :wsize 4 :ptr 2.29)
(ctl tri2 :freq 58.4)
(ctl tri3 :freq 78.21)
(def s2-smplr2 (wrprlfo s7 :ptr 4.2 :fscale 1.3 :wsize 13 :amp 0.9))
(ctl tri1 :freq 110)
(ctl tri1 :freq 130.81)
(ctl s2-smplr2 :fscale 1.9 :wsize 2)
(ctl s2-smplr :fscale 1.7 :wsize 6)
(ctl s2-smplr :fscale 0.4 :wsize 6)
(ctl s2-smplr2 :fscale 0.475 :wsize 6)
(ctl tri1 :freq 73.42)
(ctl tri2 :freq 51.91)
(ctl tri2 :freq 58.27)
(ctl tri2 :freq 65.41)
(ctl s2-smplr :fscale 0.6 :wsize 0.2)
(ctl s2-smplr2 :fscale 0.65 :wsize 1.525)
(ctl s2-smplr :fscale 0.525 :wsize 12.2)
(ctl tri2 :freq 71.205)
(ctl tri3 :freq 78.21)
(ctl tri1 :freq 82.41)
(ctl tri1 :gate 0)
(ctl tri3 :gate 0)
(ctl tri2 :gate 0)
(ctl s2-smplr2 :fscale 0.5125 :wsize 14)
(ctl s2-smplr2 :fscale 4.941 :wsize 0.04)
(ctl s2-smplr2 :fscale 4.2 :wsize 0.04)
(ctl s2-smplr :fscale 4.9 :wsize 0.039)
(ctl s2-smplr2 :wsize 0.038)
(ctl s2-smplr :wsize 0.3)
(ctl s2-smplr :fscale 0.49 :wsize 13)
(ctl s2-smplr2 :fscale 0.42 :wsize 0.04)
(ctl s2-smplr2 :gate 0)
(ctl s2-smplr :gate 0)

;; 

(stop)
