(ns otworks.c22
  (:use [overtone.live :rename {ctl c}])
  (:require [otworks.functions :refer [get-samples]]))

(get-samples "~/Producing/jan2nd-2017/smp/"
             (mapv #(str "s" % "m") (range 0 9)))

(definst tgr
  [b 0 r 1 a 1 at 15 rl 40 d 5 g 1]
  (->
   (t-grains 2 (impulse:ar 4) b r (* 0.1 (sin-osc:kr 0.03)) d 0 1 1)
   (free-verb 0.5 1 1)
   (* (env-gen (asr :attack at :curve 1 :release rl) :gate g :action 2))
   (* a)))

(definst tgrf
  [b 0 r 1 lf 2000 hf 200 a 1 at 15 rl 40 d 5 g 1]
  (->
   (t-grains 2 (impulse:ar 4) b r (* 0.1 (sin-osc:kr 0.03)) d 0 1 1)
   (lpf lf)
   (hpf hf)
   (free-verb 0.5 1 1)
   (* (env-gen (asr :attack at :curve 1 :release rl) :gate g :action 2))
   (* a)))

(definst c1sio
  [f 440 a 1 rl 40 at 15 g 0] 
  (->
   (sin-osc [f (+ f 1)])
   (* (env-gen (asr :attack at :curve 1 :release rl) :gate g :action FREE))
   (* a)))

(def t1 (tgr s1m 0.67 1 15))
(def t2 (tgrf s3m 800 2000 400 1 10))
(c t2 :r 0.8)
(c t1 :r 0.625)
(c t2 :r 0.75)
(c t1 :r 0.55)
(c t2 :r 0.4)
(def s1 (c1sio 5250 0.2 10))
(c s1 :g 0)
(def s2 (c1sio 800 0.15 10))
(c s2 :f 80 :a 0.8)

(stop)
