(ns otworks.c9
  (:require [overtone.core :refer :all]
            [otworks.functions :refer [get-samples boot]]))

;; Init

(boot)
(get-samples "~/Producing/october20th-2016/sc8/" (mapv #(str "s" %) (range 0 2)))


;; SynthDefs

(definst pbuf
  [buf 0 spos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 gate 1]
  (-> (play-buf 2 buf rate 1 spos 1 2)
      (* amp)
      (lpf lff)
      (hpf hff)
      (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))))


;; Track - Lie
;; https://paullucas.bandcamp.com/track/lie

(def x (pbuf s1 (/ @sample-rate* 4) 0.4 1500 150 2 10 1))
(def y (pbuf s1 0.0 0.5 3000 50 1 1 5))
(def z (pbuf s1 (/ @sample-rate* 8) 0.8 2000 40 1 1 5))
(def a (pbuf s0 0.0 0.2 3000 50 2 10 10))
(def b (pbuf s0 (/ @sample-rate* 2) 0.4 5000 40 1 10 10))
(def c (pbuf s1 0.0 1 800 350 1 1 10))
(ctl x :rate 0.6)
(ctl y :rate 0.3)
(ctl z :rate 0.6)
(ctl a :rate 0.3)
(ctl a :rate 0.225)
(ctl a :rate 0.2)
(ctl a :rate 0.3)
(ctl a :rate 0.225)
(ctl a :rate 0.2)
(ctl b :rate 0.4)
(ctl c :rate 0.5)
(ctl c :rate 0.9)
(ctl x :gate 0)
(ctl y :gate 0)
(ctl z :gate 0)
(ctl b :gate 0)
(ctl c :gate 0)
(ctl a :gate 0)


(stop)
