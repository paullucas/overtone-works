(ns otworks.c14
  (:require [overtone.core]
            [otworks.functions :refer [get-samples]]))

(connect-external-server)

(get-samples "~/Producing/october20th-2016/sc8/"
             (mapv #(str "s" %) (range 15 18)))

(definst pbuf
  [buf 0 spos 0.0 rate 1 amp 1 att 10 rel 40 gate 1 loop 1]
  (->
   (play-buf 2 buf rate 1 spos loop 2)
   (free-verb 1 1 1)
   (* amp)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))))

(definst pbufm
  [buf 0 spos 0.0 rate 1 amp 1 att 10 rel 40 gate 1 loop 1]
  (->
   (play-buf 1 buf rate 1 spos loop 2)
   (pan2 0 1)
   (free-verb 1 1 1)
   (* amp)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))))

(definst pbufm2
  [buf 0 spos 0.0 rate 1 amp 1 att 3 rel 40 gate 1 loop 1]
  (->
   (play-buf 1 buf rate 1 spos loop 2)
   (pan2 0 1)
   (* amp)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))))

(def a (pbuf s15 0.0 0.7 1.2 10 20))
(def b (pbufm s16 0.0 1 0.7 10 20))
(def c (pbufm2 s17 0.0 1 1 3 1 1 0))
(ctl a :rate 0.5)
(ctl b :rate 0.8)
(def c (pbufm2 s17 0.0 0.5 1 3 1 1 0))
(ctl a :gate 0)
(ctl b :gate 0)

(stop)
