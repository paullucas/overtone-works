(ns otworks.c16
  (:use overtone.live)
  (:require [otworks.functions :refer [get-samples gen-inst]]
            [leipzig.melody :refer [bpm phrase where tempo]]
            [leipzig.chord :refer [root triad seventh ninth]]
            [leipzig.live :as live]
            [leipzig.scale :as scale]))

(get-samples "~/Producing/october20th-2016/ot16/"
             (mapv #(str "s" %) (range 0 9)))

(gen-inst "wrpr" ["free-verb" "warp1"])
(gen-inst "wrplf" ["lpf" "warp1"])
(gen-inst "wrphf" ["hpf" "warp1"])
(gen-inst "wrprlf" ["free-verb" "lpf" "warp1"])
(gen-inst "wrprlfo" ["free-verb" "lpf" "warp1-lfo"])
(gen-inst "tri" ["free-verb" "lf-tri"])
(gen-inst "shf" ["free-verb" "hpf" "warp1"])

(def wrplf1 (wrplf s0 :pointer 0.83 :freq-scale 0.4 :window-size 14 :amp 1.5 :lff 3000))
(def wrplf2 (wrprlf s1 :pointer 0.1 :freq-scale 0.8 :window-size 8 :amp 1.1 :lff 5000))
(def wrplf3 (wrprlf s3 :pointer 0.01 :freq-scale 0.8 :window-size 11 :amp 1.7 :lff 8000 :overlaps 3))
(ctl wrplf3 :amp 1.7 :pointer 0.4 :window-size 8 :freq-scale 0.35)
(ctl wrplf2 :pointer 0.2 :window-size 6 :freq-scale 0.6)
(ctl wrplf2 :pointer 0.2 :window-size 6 :freq-scale 0.42)
(ctl wrplf1 :pointer 0.4 :window-size 13 :freq-scale 0.63)
(ctl wrplf1 :pointer 0.4 :window-size 13 :freq-scale 0.55)
(ctl wrplf3 :gate 0)
(ctl wrplf1 :pointer 0.4 :window-size 13 :freq-scale 0.51)
(ctl wrplf2 :gate 0)
(ctl wrplf1 :pointer 0.4 :window-size 13 :freq-scale 0.48)
(ctl wrplf1 :gate 0)
(def wrprlfo1 (wrprlfo s0 :pointer 2.3 :freq-scale 0.4 :window-size 13 :amp 1.5))
(def wrprlfo2 (wrprlfo s2 :pointer 3.6 :freq-scale 0.7 :window-size 13))
(def wrprlfo3 (wrprlfo s3 :pointer 2.8 :freq-scale 0.8 :window-size 10 :amp 1.7))
(ctl wrprlfo3 :pointer 2.8 :window-size 11 :freq-scale 0.95)
(ctl wrprlfo3 :pointer 2.8 :window-size 10 :freq-scale 0.875)
(ctl wrprlfo2 :pointer 3.2 :window-size 12 :freq-scale 0.82)
(ctl wrprlfo2 :freq-scale 0.8)
(ctl wrprlfo1 :pointer 2.4 :freq-scale 0.8 :window-size 9)
(ctl wrprlfo1 :pointer 2.4 :freq-scale 0.6 :window-size 9)
(ctl wrprlfo2 :gate 0)
(ctl wrprlfo3 :gate 0)
(ctl wrprlfo1 :pointer 2.9 :freq-scale 0.45 :window-size 6)
(ctl wrprlfo1 :pointer 2.91 :freq-scale 0.48 :window-size 4)
(def wrphf1 (wrphf s0 :pointer 2.3 :freq-scale 6.2 :window-size 0.2 :amp 0.8))
(ctl wrphf1 :pointer 1.3 :freq-scale 3.1 :window-size 12)
(ctl wrphf1 :pointer 1.1 :freq-scale 2.7 :window-size 11)
(ctl wrphf1 :pointer 0.1 :freq-scale 0.7 :window-size 8)
(ctl wrphf1 :pointer 1.3 :freq-scale 4.4 :window-size 9.02)
(ctl wrphf1 :pointer 0.1 :freq-scale 0.7 :window-size 8)
(ctl wrprlfo1 :gate 0)
(ctl wrphf1 :gate 0)
(ctl wrphf1 :hff 100)

;;

(def wrplf1 (wrplf s4 :pointer 0.83 :freq-scale 0.4 :window-size 10 :amp 1.5 :lff 4000))
(def wrpr1 (wrpr s5 :pointer 0.27 :freq-scale 0.5 :window-size 9 :amp 1.2))
(ctl wrplf1 :freq-scale 0.25)
(ctl wrplf1 :window-size 12)
(ctl wrpr1 :freq-scale 0.5 :window-size 9)
(ctl wrpr1 :freq-scale 0.675 :window-size 13)
(ctl wrpr1 :overlaps 2)
(ctl wrplf1 :freq-scale 0.4 :window-size 14)
(ctl wrplf1 :overlaps 3)
(ctl wrpr1 :freq-scale 0.275 :window-size 10)
(ctl wrplf1 :gate 0)
(ctl wrpr1 :overlaps 1)
(ctl wrpr1 :freq-scale 0.22)
(def wrphf1 (wrphf s6 :pointer 0.23 :freq-scale 0.8 :window-size 16.0 :amp 1.4 :hff 400))
(ctl wrphf1 :pointer 0.7)
(ctl wrphf1 :freq-scale 1.1)
(ctl wrphf1 :freq-scale 0.65 :pointer 0.1 :overlaps 2)
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
(def s2-smplr (wrprlfo s7 :pointer 2.3 :freq-scale 0.6 :window-size 13 :amp 0.9))
(ctl s2-smplr :freq-scale 0.575 :window-size 4 :pointer 2.29)
(ctl tri2 :freq 58.4)
(ctl tri3 :freq 78.21)
(def s2-smplr2 (wrprlfo s7 :pointer 4.2 :freq-scale 1.3 :window-size 13 :amp 0.9))
(ctl tri1 :freq 110)
(ctl tri1 :freq 130.81)
(ctl s2-smplr2 :freq-scale 1.9 :window-size 2)
(ctl s2-smplr :freq-scale 1.7 :window-size 6)
(ctl s2-smplr :freq-scale 0.4 :window-size 6)
(ctl s2-smplr2 :freq-scale 0.475 :window-size 6)
(ctl tri1 :freq 73.42)
(ctl tri2 :freq 51.91)
(ctl tri2 :freq 58.27)
(ctl tri2 :freq 65.41)
(ctl s2-smplr :freq-scale 0.6 :window-size 0.2)
(ctl s2-smplr2 :freq-scale 0.65 :window-size 1.525)
(ctl s2-smplr :freq-scale 0.525 :window-size 12.2)
(ctl tri2 :freq 71.205)
(ctl tri3 :freq 78.21)
(ctl tri1 :freq 82.41)
(ctl tri1 :gate 0)
(ctl tri3 :gate 0)
(ctl tri2 :gate 0)
(ctl s2-smplr2 :freq-scale 0.5125 :window-size 14)
(ctl s2-smplr2 :freq-scale 4.941 :window-size 0.04)
(ctl s2-smplr2 :freq-scale 4.2 :window-size 0.04)
(ctl s2-smplr :freq-scale 4.9 :window-size 0.039)
(ctl s2-smplr2 :window-size 0.038)
(ctl s2-smplr :window-size 0.3)
(ctl s2-smplr :freq-scale 0.49 :window-size 13)
(ctl s2-smplr2 :freq-scale 0.42 :window-size 0.04)
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
(def sm1 (shf s8 :pointer 0.5 :freq-scale 0.19 :window-size 12 :hff 50 :amp 2))
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
(ctl sm1 :freq-scale 0.235)
(def tri1 (tri :freq (midi->hz 88) :amp 0.115 :rel 12))
(ctl tri1 :gate 0)
(def tri1 (tri :freq (midi->hz 80) :amp 0.115 :rel 12))
(ctl tri1 :gate 0)
(melody
 (phrase
  (cycle [7 8])
  [(-> triad (root 3))
   (-> ninth (root 2))]))
(ctl sm1 :freq-scale 0.205)
(ctl sm1 :freq-scale 0.19)
(ctl sm1 :gate 0)
(sino-amp 0.1)
(sino-amp 0.05)
(sino-amp 0.0)

(live/stop)
(stop)
(sino-amp 0.2)
