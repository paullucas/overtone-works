(ns otworks.c13
  (:require [overtone.core :refer :all]
            [otworks.functions :refer [get-samples boot]]))

;; Init

(boot)
(get-samples "~/Producing/october20th-2016/sc8/" (mapv #(str "s" %) (range 13 15)))


;; SynthDefs

(definst pbufm
  [buf 0 spos 0.0 rate 1 amp 1 att 15 rel 40 gate 1]
  (-> (play-buf 1 buf rate 1 spos 1 2)
      (pan2 0 1)
      (free-verb 1 1 1)
      (* amp)
      (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))))


;; Track - Autonomy
;; https://paullucas.bandcamp.com/track/autonomy

(def a (pbufm s13 0.0 1 0.7 10 20))
(def b (pbufm s13 0.0 0.5 0.8 10 20))
(ctl a :rate 0.8)
(ctl a :rate 0.4)
(def c (pbufm s14 0.0 1.2 1.1 10 20))
(ctl b :rate 0.6)
(ctl a :rate 0.8)
(ctl c :rate 1.8)
(ctl b :rate 0.4)
(ctl c :rate 1.775)
(ctl a :rate 22.8)
(ctl a :rate 45.6)
(ctl a :rate 3.2)
(ctl a :rate 1.6)
(ctl a :rate 0.4)
(ctl c :rate 1.8)
(ctl c :gate 0)
(ctl a :gate 0)
(ctl b :gate 0)


(stop)
