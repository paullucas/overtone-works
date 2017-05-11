(ns otworks.c7
  (:require
   [overtone.core]
   [otworks.functions :refer [get-samples]]
   [leipzig.melody :refer [bpm is phrase then times where with tempo]]
   [leipzig.chord :as chord]
   [leipzig.live :as live]
   [leipzig.scale :as scale]))

(connect-external-server)

(definst tri [freq 440 dur 1.0]
  (-> freq
      lf-tri
      (* (env-gen (perc 0.05 dur) :action FREE))
      (free-verb)
      (* 0.5)))

(definst sino [freq 440 dur 1.0]
  (-> freq
      sin-osc
      (* (env-gen (perc 0.09 dur) :action FREE))
      (free-verb :room 1 :mix 0.5 :damp 1)
      (* 0.5)))

(definst beep [freq 440 dur 1.0]
  (-> freq
      formant
      (* (env-gen (perc 0.05 dur) :action FREE))
      (free-verb)
      (* 0.4)))

(definst beep2 [freq 440 dur 1.0]
  (-> freq
      saw
      (* (env-gen (perc 0.05 0.5) :action FREE))
      (free-verb)
      (* 0.6)))

(defmethod live/play-note :default [{midi :pitch seconds :duration}]
  (-> midi midi->hz (tri seconds))
  (-> midi midi->hz (sino seconds))
  (-> midi midi->hz (beep seconds))
  (-> midi midi->hz (beep2 seconds)))

(defn r []
  (rand-int 12))

(defn r []
  (rand-int -9))

(defn r []
  (rand-int -12))

(defn melody []
  (phrase
   (cycle [6 8])
   [(-> chord/triad (chord/root (r)))
    (-> chord/seventh (chord/root (r)))
    (-> chord/triad (chord/root (r)))
    (-> chord/ninth (chord/root (r)))]))

(def track
  (->>
   (melody)
   (tempo (bpm 4420))
   (where :pitch (comp scale/D scale/mixolydian))))

(def track
  (->>
   (melody)
   (tempo (bpm 1420))
   (where :pitch (comp scale/C scale/pentatonic))))

(def track
  (->>
   (melody)
   (tempo (bpm 4420))
   (where :pitch (comp scale/C scale/pentatonic))))

(def track
  (->>
   (melody)
   (tempo (bpm 1420))
   (where :pitch (comp scale/D scale/pentatonic))))

(def track
  (->>
   (melody)
   (tempo (bpm 1420))
   (where :pitch (comp scale/E scale/pentatonic))))

(def track
  (->>
   (melody)
   (tempo (bpm 5420))
   (where :pitch (comp scale/D scale/pentatonic))))

(def track
  (->>
   (melody)
   (tempo (bpm 6420))
   (where :pitch (comp scale/D scale/pentatonic))))

(def track
  (->>
   (melody)
   (tempo (bpm 820))
   (where :pitch (comp scale/E scale/pentatonic))))

(live/jam (var track))

(live/stop)
