(ns otworks.c6
  (:require [overtone.core :refer :all]
            [otworks.functions :refer [get-samples boot]]))

(boot)

(get-samples "~/Producing/july23rd-2016/smpls/"
             (mapv #(str "s" %) (range 1 8)))

(definst tri
  [buf 0 amp 1 att 15 rel 40 mix 1 room 1 damp 1 gate 1 freq 440] 
  (->
   (lf-tri freq)
   (free-verb mix room damp)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action FREE))
   (* amp)))

(definst smplr
  [buf 0 ptr 0 amp 1 att 15 rel 40 lff 2000 mix 1 room 1 damp 1
   gate 1 rate 1 fscale 1 wsize 0.1 ebn -1 olaps 1 interp 4] 
  (->
   (warp1 1 buf (+ 0.4 (* 0.1 (sin-osc:kr ptr))) fscale wsize ebn olaps 0.0 interp)
   (lpf lff)
   (free-verb mix room damp)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action FREE))
   (* amp)))

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
(def s2-smplr (smplr s2 :ptr 2.3 :fscale 0.6 :wsize 13 :amp 0.9))
(ctl s2-smplr :fscale 0.575 :wsize 4 :ptr 2.29)
(ctl tri2 :freq 58.4)
(ctl tri3 :freq 78.21)
(def s2-smplr2 (smplr s2 :ptr 4.2 :fscale 1.3 :wsize 13 :amp 0.9))
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

(stop)
