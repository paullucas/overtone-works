(ns otworks.c8
  (:use overtone.live)
  (:require [otworks.functions :refer [get-samples gen-inst]]
            [leipzig.melody :refer [bpm phrase where tempo]]
            [leipzig.chord :refer [root triad seventh ninth]]
            [leipzig.live :as live]
            [leipzig.scale :as scale]))

(get-samples "~/Producing/october2nd-2016/" ["s1n" "s2n" "s3n" "s4n"])

(gen-inst "shf" ["free-verb" "hpf" "warp1"])
(gen-inst "tri" ["free-verb" "lf-tri"])

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
   (-> seventh (root 3))]))

;; ;;

(live/jam (var track))

(def sm1 (shf s2n :pointer 0.5 :freq-scale 0.19 :window-size 12 :hff 50 :amp 2))

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
