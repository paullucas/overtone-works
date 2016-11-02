(ns otworks.c17
  (:use overtone.live)
  (:require [otworks.functions :refer [get-samples gen-inst]]
            [leipzig.melody :refer [bpm phrase where tempo]]
            [leipzig.chord :refer [root triad seventh ninth]]
            [leipzig.live :as live]
            [leipzig.scale :as scale]))

;; Load files "s0.wav", "s1.wav", "s2.wav" from the folder path "~/FOLDER/CONTAINING/WAV/FILES/"
(get-samples "~/FOLDER/CONTAINING/WAV/FILES/"
             (mapv #(str "s" %) (range 0 3)))

;; Basic SynthDef 
(definst pbuf [buf 0 start-pos 0.0 rate 1 amp 1 att 10 rel 40 gate 1 loop 1]
  (->
   (play-buf 2 buf rate 1 start-pos loop 2)
   (* amp)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))))

;; Create instance of pbuf, call it "a"
;; Plays the sample "s1.wav"
;; Start position of 0.0
;; Rate of 0.7
;; Amp of 1.2
;; Attack of 10 seconds
;; Release of 20 seconds
(def a (pbuf s1 0.0 0.7 1.2 10 20))

;; Fade out (trigger the release)
(ctl a :gate 0)
