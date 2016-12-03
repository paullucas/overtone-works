(ns otworks.c15
  (:use overtone.live)
  (:require [otworks.functions :refer [get-samples]]))

(get-samples "~/Producing/october20th-2016/sc8/"
             (mapv #(str "s" %) (range 0 18)))

(definst pbuf
  [buf 0 spos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 gate 1]
  (->
   (play-buf 2 buf rate 1 spos 1 2)
   (* amp)
   (lpf lff)
   (hpf hff)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))))

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

;;

(do
  (definst pbuf
    [buf 0 spos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 gate 1 loop 1]
    (->
     (play-buf 2 buf rate 1 spos loop 2)
     (* amp)
     (lpf lff)
     (hpf hff)
     (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))
     (free-verb 0.5 1 1)))

  (definst pbufm
    [buf 0 spos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 gate 1]
    (->
     (play-buf 1 buf rate 1 spos 1 2)
     (* amp)
     (lpf lff)
     (hpf hff)
     (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))
     (free-verb 0.5 1 1)))

  (definst pgrain
    [buf 0 dur 1 cpos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 t_trig 0 gate 1]
    (->
     (t-grains 2 (impulse:ar t_trig) buf rate cpos dur 0 amp 1)
     (lpf lff)
     (hpf hff)
     (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))
     (free-verb 0.5 1 1))))

(def x (pbuf s2 0 0.4 4000 100 0.8 10 10))
(def y (pbufm s3 0 0.5 2000 50 0.93 10 10))
(ctl x :gate 0)
(def x (pbuf s2 0 0.3 3000 100 0.8 10 10))
(ctl y :gate 0)
(def y (pbufm s3 0 0.3 2000 30 0.93 10 10))
(ctl x :gate 0)
(def x (pbuf s2 0 0.27 4000 80 0.8 10 10))
(ctl x :gate 0)
(def x (pbuf s2 0 0.2 3500 100 0.8 10 10))
(def z (pgrain s4 25 1222 0.8 2000 1000 0.9 10 20 0.165))
(ctl x :gate 0)
(ctl y :gate 0)
(ctl z :gate 0)

;; 

(definst pgrain
  [buf 0 dur 1 cpos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 t-trig 0 gate 1 mamp 1]
  (->
   (t-grains 2 (impulse:ar t-trig) buf rate cpos dur 0 amp 2)
   (lpf lff)
   (hpf hff)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))
   (* mamp)
   (free-verb 0.5 1 1)))

(def x (pbufm s6 0.0 0.45 2000 80 0.8 10 10))
(def y (pbufm s5 0.0 0.85 2000 200 1.7 10 10))
(ctl x :rate 0.60)
(ctl y :rate 0.75)
(ctl y :rate 0.65)
(def b (pbuf s8 0.0 0.85 19000 3000 0.15 10 40 1 1))
(ctl y :gate 0)
(def z (pgrain s7 10 222 1.5 2000 1000 1 10 1 0.075 1 6))
(do (ctl x :rate 0.65)
    (ctl z :rate 1.4 :t-trig 0.065)
    (ctl b :loop 0))
(ctl z :gate 0)
(ctl x :rate 0.4)
(def z (pgrain s7 10 222 6.5 2000 1000 1 10 25 0.045 1 3))
(def b (pbuf s8 0.0 1 19000 3000 0.15 10 40 1 1))
(ctl z :rate 28 :mamp 2.85)
(ctl z :rate 38)
(ctl z :rate 0.5 :amp 7 :mamp 8 :t-trig 1.08 :lff 500)
(ctl b :loop 0)
(ctl x :gate 0)
(ctl z :gate 0)

;;

(def x (pbuf s9 0.0 0.8 1000 60 0.7 10 10))
(def y (pbuf s12 0.0 1.25 6000 800 0.8 10 10))
(def z (pbuf s10 0.0 1.8 2000 200 0.8 10 20))
(ctl z :rate 1.4)
(ctl x :rate 0.82)
(ctl y :gate 0)
(ctl z :rate 1)
(ctl x :rate 0.7)
(def a (pbuf s11 0.0 2 3500 150 1 10 20))
(def y (pbuf s12 0.0 1.05 6000 3000 0.8 10 10))
(ctl x :gate 0)
(ctl z :rate 0.8)
(ctl a :rate 0.8)
(ctl y :rate 1.2)
(ctl z :gate 0)
(ctl y :gate 0)
(ctl a :gate 0)

;; 

(definst pbufm
  [buf 0 spos 0.0 rate 1 amp 1 att 15 rel 40 gate 1 loop 1]
  (->
   (play-buf 1 buf rate 1 spos loop 2)
   (pan2 0 1)
   (free-verb 1 1 1)
   (* amp)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))))

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

;;

(do
  (definst pbuf
    [buf 0 spos 0.0 rate 1 amp 1 att 10 rel 40 gate 1 loop 1]
    (->
     (play-buf 2 buf rate 1 spos loop 2)
     (free-verb 1 1 1)
     (* amp)
     (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))))

  (definst pbufm2
    [buf 0 spos 0.0 rate 1 amp 1 att 3 rel 40 gate 1 loop 1]
    (->
     (play-buf 1 buf rate 1 spos loop 2)
     (pan2 0 1)
     (* amp)
     (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2)))))

(def a (pbuf s15 0.0 0.7 1.2 10 20))
(def b (pbufm s16 0.0 1 0.7 10 20))
(def c (pbufm2 s17 0.0 1 1 3 1 1 0))
(ctl a :rate 0.5)
(ctl b :rate 0.8)
(def c (pbufm2 s17 0.0 0.5 1 3 1 1 0))
(ctl a :gate 0)
(ctl b :gate 0)

(stop)
