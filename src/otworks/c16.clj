(ns otworks.c16
  (:require [overtone.core]
            [otworks.functions :refer [get-samples]]
            [leipzig.melody :refer [bpm phrase where tempo]]
            [leipzig.chord :refer [root triad seventh ninth]]
            [leipzig.live :as live]
            [leipzig.scale :as scale]))

(connect-external-server)

(get-samples "~/Producing/october20th-2016/ot16/"
             (mapv #(str "s" %) (range 0 9)))

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

(definst shf
  [buf 0 ptr 0 amp 1 att 15 rel 40 hff 200 mix 1 room 1 damp 1
   gate 1 rate 1 fscale 1 wsize 0.1 ebn -1 olaps 1 interp 4] 
  (->
   (warp1 1 buf ptr fscale wsize ebn olaps 0.0 interp)
   (hpf hff)
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

(def wrplf1 (wrplf s0 :ptr 0.83 :fscale 0.4 :wsize 14 :amp 1.5 :lff 3000))
(def wrplf2 (wrprlf s1 :ptr 0.1 :fscale 0.8 :wsize 8 :amp 1.1 :lff 5000))
(def wrplf3 (wrprlf s3 :ptr 0.01 :fscale 0.8 :wsize 11 :amp 1.7 :lff 8000 :olaps 3))
(ctl wrplf3 :amp 1.7 :ptr 0.4 :wsize 8 :fscale 0.35)
(ctl wrplf2 :ptr 0.2 :wsize 6 :fscale 0.6)
(ctl wrplf2 :ptr 0.2 :wsize 6 :fscale 0.42)
(ctl wrplf1 :ptr 0.4 :wsize 13 :fscale 0.63)
(ctl wrplf1 :ptr 0.4 :wsize 13 :fscale 0.55)
(ctl wrplf3 :gate 0)
(ctl wrplf1 :ptr 0.4 :wsize 13 :fscale 0.51)
(ctl wrplf2 :gate 0)
(ctl wrplf1 :ptr 0.4 :wsize 13 :fscale 0.48)
(ctl wrplf1 :gate 0)
(def wrprlfo1 (wrprlfo s0 :ptr 2.3 :fscale 0.4 :wsize 13 :amp 1.5))
(def wrprlfo2 (wrprlfo s2 :ptr 3.6 :fscale 0.7 :wsize 13))
(def wrprlfo3 (wrprlfo s3 :ptr 2.8 :fscale 0.8 :wsize 10 :amp 1.7))
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

(do
  (definst sino [freq 440 dur 1.0]
    (-> freq
        sin-osc
        (* (env-gen (perc 0.2 dur) :action FREE))
        (free-verb :room 1 :mix 1 :damp 1)
        (* 0.2)))

  (defmethod live/play-note :default [{midi :pitch seconds :duration}]
    (-> midi midi->hz (sino seconds)))

  (defn melody [phrase]
    (def track
      (->>
       phrase
       (tempo (bpm 45))
       (where :pitch (comp scale/lower scale/lower))
       (where :pitch (comp scale/D scale/mixolydian)))))

  (defn sino-amp [amp]
    (definst sino [freq 440 dur 1.0]
      (-> freq
          sin-osc
          (* (env-gen (perc 0.2 dur) :action FREE))
          (free-verb :room 1 :mix 1 :damp 1)
          (* amp))))

  (melody
   (phrase
    (cycle [6 8])
    [(-> triad (root 1))
     (-> seventh (root 3))])))

(live/jam (var track))
(def sm1 (shf s8 :ptr 0.5 :fscale 0.19 :wsize 12 :hff 50 :amp 2))
(melody
 (phrase
  (cycle [6 8])
  [(-> triad (root 0))
   (-> ninth (root 2))]))
(melody
 (phrase
  (cycle [6 8])
  [(-> triad (root 0))
   (-> ninth (root 1))]))
(ctl sm1 :fscale 0.235)
(def tri1 (tri :freq (midi->hz 88) :amp 0.115 :rel 12))
(ctl tri1 :gate 0)
(def tri1 (tri :freq (midi->hz 80) :amp 0.115 :rel 12))
(ctl tri1 :gate 0)
(melody
 (phrase
  (cycle [7 8])
  [(-> triad (root 3))
   (-> ninth (root 2))]))
(ctl sm1 :fscale 0.205)
(ctl sm1 :fscale 0.19)
(ctl sm1 :gate 0)
(sino-amp 0.1)
(sino-amp 0.05)
(sino-amp 0.0)

(live/stop)
(stop)
(sino-amp 0.2)
