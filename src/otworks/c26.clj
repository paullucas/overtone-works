(ns otworks.c28
  (:require ;; [overtone.core :refer :all :rename {ctl c}]
            [overtone.live :refer :all :rename {ctl c}]
            [otworks.functions :refer [get-samples boot]]
            [leipzig.melody :refer [bpm phrase where tempo] :as ml]
            [leipzig.chord :refer [root triad seventh ninth] :as ch]
            [leipzig.live :as live]
            [leipzig.scale :as scale]))

;; Init

;; (boot)
(get-samples "~/Producing/november25th-2017/smpl/" (mapv #(str "s" % "f") (range 1 2)))

;; SynthDefs

(definst pbuf
  [buf 0 rate 1 lff 20000 hff 100 amp 1 att 20 rel 40 gate 1 loop 1]
  (-> (play-buf 2 buf rate 1 0.0 loop 2)
      (lpf lff)
      (hpf hff)
      (* amp)
      (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))))


(definst sino
  [freq 440 dur 1.0]
  (-> freq
      sin-osc
      (* (env-gen (perc 0.2 dur) :action FREE))
      (free-verb :room 1 :mix 1 :damp 1)
      (* 0.6)))

(defmethod live/play-note :default [{midi :pitch seconds :duration}]
  (-> midi midi->hz (sino seconds)))

;; Track

(do (defn melody [phrase]
      (def track (->> phrase
                      (tempo (bpm 60))
                      (where :pitch (comp scale/lower scale/lower))
                      (where :pitch (comp scale/F scale/lydian)))))

    (defn micro [mult times chords]
      (-> (map #(/ % mult) times)
          cycle
          (phrase (map #(apply root %) chords))
          melody))

    (micro 1 [6 8] [[seventh 7]
                    [triad 1]
                    [seventh 3]
                    [ninth 2]]))

(def s1 (pbuf s1f))

(live/jam (var track))

(micro 1 [6 8]
       [[ninth 12]
        [seventh 7]
        [ninth 14]
        [triad 16]])

(do (defn melody [phrase]
      (def track (->> phrase
                      (tempo (bpm 420))
                      (where :pitch (comp scale/lower scale/lower scale/lower))
                      (where :pitch (comp scale/D scale/lydian)))))
    (micro 1 [2 1 2 3 2 1]
           [[ninth 12]
            [seventh 7]
            [ninth 14]
            [triad 16]]))

(micro 1 [2 1 2 3 2 1 1 1 2 1 2 3]
       [[ninth 12]
        [seventh 7]
        [ninth 14]
        [triad 16]
        [ninth 18]
        [ninth 12]
        [ninth 7]
        [ninth 7]])

(micro 1 [2 1 2 3 2 1 1 1 2 1 2 3]
       [[ninth 12]
        [seventh 7]
        [ninth 14]
        [ninth 7]
        [triad 16]
        [ninth 7]
        [ninth 18]
        [ninth 7]
        [ninth 12]
        [ninth 7]
        [ninth 7]])

(micro 1 [2 1 2 3 2 1 1 1 2 1 2 3]
       [[ninth 12]
        [ninth 14]
        [ninth 9]
        [triad 16]
        [ninth 9]
        [ninth 21]
        [ninth 21]
        [ninth 21]
        [ninth 12]
        [ninth 9]
        [ninth 9]])

(micro 1 [2 1 2 3 2 1 1 1 2 1 2 3]
       [[ninth 14]
        [ninth 18]
        [ninth 10]
        [triad 18]
        [ninth 10]
        [ninth 25]
        [ninth 25]
        [ninth 25]
        [triad 25]
        [ninth 12]
        [ninth 8]
        [ninth 8]])

(micro 5.75 [2 1 2 3 2 1 1 1 2 1 2 3]
       [[ninth 14]
        [triad 8]
        [triad 18]
        [ninth 4]
        [ninth 21]
        [triad 25]
        [ninth 5]
        [triad 21]
        [ninth 12]
        [triad 5]
        [ninth 8]])

(micro 5.2 [2 1 2 3 2 1 1 1 2 1 2 3]
       [[ninth 11]
        [triad 5]
        [triad 15]
        [ninth 1]
        [ninth 18]
        [triad 22]
        [ninth 2]
        [triad 18]
        [ninth 9]
        [triad 2]
        [ninth 5]])

(micro 6.25 [2 1 2 3 2 1 1 1 2 1 2 3]
       [[ninth 9]
        [triad 3]
        [triad 13]
        [ninth 1]
        [ninth 15]
        [triad 19]
        [ninth 2]
        [triad 15]
        [ninth 6]
        [triad 2]
        [ninth 5]])

(micro 4 [2 1 2 3 2 1 1 1 2 1 2 3]
       [[ninth 6]
        [triad 2]
        [triad 10]
        [ninth 1]
        [ninth 12]
        [triad 16]
        [ninth 2]
        [triad 12]
        [ninth 5]
        [triad 2]
        [ninth 4]])

(micro 4 [2 1 2 3 2 1 1 1 2 1 2 3]
       [[ninth 7]
        [triad 2]
        [triad 11]
        [ninth 1]
        [ninth 13]
        [triad 16]
        [ninth 2]
        [triad 13]
        [ninth 5]
        [triad 2]
        [ninth 4]])

(micro 4 [2 1 2 3 2 1 1 1 2 1 2 3]
       [[ninth 6]
        [triad 2]
        [triad 10]
        [ninth 1]
        [ninth 12]
        [triad 16]
        [ninth 2]
        [triad 12]
        [ninth 5]
        [triad 2]
        [ninth 4]])

(micro 4.3 [2 1 2 3 2 1 1 1 2 1 2 3]
       [[ninth 7]
        [triad 2]
        [triad 11]
        [ninth 1]
        [ninth 14]
        [triad 20]
        [ninth 2]
        [triad 13]
        [ninth 5]
        [triad 2]
        [ninth 4]])

(micro 2.3 [2 1 2 3 2 1 1 1 2 1 2 3]
       [[ninth 6]
        [triad 2]
        [triad 10]
        [ninth 1]
        [ninth 12]
        [triad 16]
        [ninth 2]
        [triad 12]
        [ninth 5]
        [triad 2]
        [ninth 4]])

(def track nil)
(c s1 :g 0)

(live/stop)
