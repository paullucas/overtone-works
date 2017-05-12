(ns otworks.c23
  (:require [overtone.core :refer :all :rename {ctl c}]
            [otworks.functions :refer [get-samples boot]]))

(boot)

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
  [f 440 a 1 rl 40 at 15 g 1] 
  (->
   (sin-osc [f (+ f 1)])
   (* (env-gen (asr :attack at :curve 1 :release rl) :gate g :action FREE))
   (* a)))

(def t1 (tgrf s2m 0.8 1000 80 1))
(def t2 (tgrf s8m 0.4 3500 100 0.8 10))
(def t3 (tgr s2m 8 0.4))
(c t1 :r 0.74)
(c t2 :r 0.6)
(c t3 :r 4.4)
(do
  (c t1 :r 0.68)
  (c t3 :r 0.4))
(c t2 :r 3.64 :a 1)
(c t1 :r 0.62)
(c t2 :r 0.64 :a 0.8)
(c t3 :r 4.3)
(c t1 :r 0.54 :hf 40)
(def s1 (c1sio 72 1 10))
(c t2 :r 0.8 :a 1)
(c t3 :r 2.1)
(c t3 :g 0)
(c t1 :g 0)
(c t2 :r 0.3 :lf 2000)
(c s1 :g 0)
(c t2 :g 0 :r 0.28)

(stop)
