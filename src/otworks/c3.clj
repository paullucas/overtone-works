(ns otworks.c3
  (:require [overtone.core :refer :all]
            [otworks.functions :refer [get-samples boot]]))

(boot)

(get-samples "~/Producing/june17th-2016/"
             (mapv #(str "s" %) (range 1 4)))

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

(def wrplf1 (wrplf s1 :ptr 0.83 :fscale 0.4 :wsize 10 :amp 1.5 :lff 4000))
(def wrpr1 (wrpr s2 :ptr 0.27 :fscale 0.5 :wsize 9 :amp 1.2))
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
(def wrphf1 (wrphf s3 :ptr 0.23 :fscale 0.8 :wsize 16.0 :amp 1.4 :hff 400))
(ctl wrphf1 :ptr 0.7)
(ctl wrphf1 :fscale 1.1)
(ctl wrphf1 :fscale 0.65 :ptr 0.1 :olaps 2)
(ctl wrpr1 :gate 0)
(ctl wrphf1 :gate 0)

(stop)
