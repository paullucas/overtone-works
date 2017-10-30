(ns otworks.c25
  (:require ;; [overtone.core :refer :all :rename {ctl c}]
            [overtone.live :refer :all :rename {ctl c}]
            [otworks.functions :refer [get-samples boot]]))

;; Init

;; (boot)


;; SynthDefs

(definst c13s
  [f 220 a 0.1 at 15 rl 40 g 1]
  (-> (sin-osc [f (+ f 1)])
      (limiter 0.8 0.001)
      (* (env-gen (asr :attack at :curve 1 :release rl) :gate g :action 2))
      (* a)))

(definst c13w
  [b 0 fs 1 ws 0.1 p 0 ol 1 lf 2000 hf 200 a 1 att 15 rl 40 g 1]
  (-> (warp1 2 b p fs ws -1 ol 0.0 4)
      (lpf lf)
      (hpf hf)
      (* (env-gen (asr :attack att :curve 1 :release rl) :gate g :action 2))
      (* a)))

(definst c13wr
  [b 0 fs 1 ws 0.1 p 0 ol 1 lf 2000 hf 200 a 1 att 15 rl 40 g 1]
  (-> (warp1 2 b p fs ws -1 ol 0.0 4)
      (lpf lf)
      (hpf hf)
      (free-verb 1 1 1)
      (* (env-gen (asr :attack att :curve 1 :release rl) :gate g :action 2))
      (* a)))

(definst c14pb
  [b 0 r 1 tr 4 lf 1000 hf 50 a 1 att 20 rl 40 g 1]
  (-> (play-buf 2 b r (impulse:kr tr) 0 1 2)
      (lpf lf)
      (hpf hf)
      (free-verb 1 1 1)
      (* (env-gen (asr :attack att :curve 1 :release rl) :gate g :action 2))
      (* a)))


;; Track 1

(get-samples "~/Producing/august25th-2017/prefixed/t1/" (mapv #(str "s" % "f") (range 1 5)))

(def s1 (c13w :b s1f :p 0.1 :db 2 :fs 0.08 :ws 10 :hf 50 :at 20))
(c s1 :ol 2)
(def s2 (c13w :b s2f :p 0.1 :db 0.8 :fs 0.2 :ws 9 :hf 80))
(def s3 (c13w :b s3f :p 0.5 :db 1 :fs 0.4 :ws 8 :lf 4000 :at 25))
(c s1 :ol 4)
(c s2 :ol 3)
(c s3 :ol 5)
(c s1 :fs 0.06)
(c s2 :fs 0.14)
(c s3 :fs 0.17)
(def s4 (c13wr :b s4f :p 0.0 :db 2 :fs 0.2 :ws 15 :lf 4000 :hf 100))
(def s5 (c13s :f 90 :a 0.4))
(c s2 :fs 0.18)
(c s1 :fs 0.09)
(c s3 :fs 0.19)
(def s6 (c13s :f 3000 :a 0.004 :rl 30))
(c s6 :g 0)
(c s1 :g 0)
(c s2 :fs 0.16)
(c s4 :g 0)
(c s2 :g 0)
(c s3 :ol 1)
(c s3 :g 0)
(c s5 :g 0)


;; Track 2

(get-samples "~/Producing/august25th-2017/prefixed/t2/" (mapv #(str "s" % "f") (range 1 9)))


(stop)
