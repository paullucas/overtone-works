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

    (melody (phrase (cycle [6 8]) [(-> seventh (root 7))
                                   (-> triad (root 1))
                                   (-> seventh (root 3))
                                   (-> ninth (root 2))])))

(def s1 (pbuf s1f))

(live/jam (var track))

(melody (phrase (cycle [6 8]) [(-> ninth   (root 12))
                               (-> seventh (root 7))
                               (-> ninth   (root 14))
                               (-> triad   (root 16))]))


(do (defn melody [phrase]
      (def track (->> phrase
                      (tempo (bpm 420))
                      (where :pitch (comp scale/lower scale/lower scale/lower))
                      (where :pitch (comp scale/D scale/lydian)))))
    (melody (phrase (cycle [2 1 2 3 2 1])
                    [(-> ninth (root 12))
                     (-> seventh (root 7))
                     (-> ninth (root 14))
                     (-> triad (root 16))])))

(melody (phrase (cycle [2 1 2 3 2 1 1 1 2 1 2 3])
                [(-> ninth (root 12))
                 (-> seventh (root 7))
                 (-> ninth (root 14))
                 (-> triad (root 16))
                 (-> ninth (root 18))
                 (-> ninth (root 12))
                 (-> ninth (root 7))
                 (-> ninth (root 7))]))

(melody (phrase (cycle [2 1 2 3 2 1 1 1 2 1 2 3])
                [(-> ninth (root 12))
                 (-> seventh (root 7))
                 (-> ninth (root 14))
                 (-> ninth (root 7))
                 (-> triad (root 16))
                 (-> ninth (root 7))
                 (-> ninth (root 18))
                 (-> ninth (root 7))
                 (-> ninth (root 12))
                 (-> ninth (root 7))
                 (-> ninth (root 7))]))

(melody (phrase (cycle [2 1 2 3 2 1 1 1 2 1 2 3])
                [(-> ninth (root 12))
                 (-> ninth (root 14))
                 (-> ninth (root 9))
                 (-> triad (root 16))
                 (-> ninth (root 9))
                 (-> ninth (root 21))
                 (-> ninth (root 21))
                 (-> ninth (root 21))
                 (-> ninth (root 12))
                 (-> ninth (root 9))
                 (-> ninth (root 9))]))

(def track nil)
(c s1 :g 0)

(live/stop)
