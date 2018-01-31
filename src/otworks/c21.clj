(ns otworks.c21
  (:require [overtone.live :refer :all :rename {ctl c}]
            ;; [overtone.core :refer :all :rename {ctl c}]
            [otworks.functions :refer [get-samples get-mono-samples boot]]))

;; Init

;; (boot)
(get-samples "~/Producing/october20th-2016/ot16/" (mapv #(str "s" %) (range 0 9)))
(get-mono-samples "~/Producing/november4th-2016/chops/smpls/" (mapv #(str "s" %) (range 1 9)))


;; SynthDefs

(definst tg
  [d 5 r 1 lf 2000 hf 200 a 1 rl 40 g 1]
  (-> (t-grains 1 (impulse:ar 4) s8m r (* 0.1 (sin-osc:kr 0.03)) d 0.5 0.7 1)
      (lpf lf)
      (hpf hf)
      (g-verb 15 6 0.5 0.5 20 0)
      (* (env-gen (asr :attack 25 :curve 1 :release rl) :gate g :action 2))
      (* a)))


(definst tgl
  [r 1 a 1.8 g 1]
  (-> (t-grains 1 (impulse:ar 0.15) s7m r (* 0.1 (sin-osc:kr 0.06)) 7 0.5 1.5 1)
      (lpf 1000)
      (g-verb 15 6 0.5 0.5 20 0.2)
      (* (env-gen (asr :attack 10 :curve 1 :release 40) :gate g :action 2))
      (* a)))


(definst sio
  [f 220 a 1 rl 40 g 1]
  (-> (sin-osc [f (+ f 1)])
      (* (env-gen (asr :attack 15 :curve 1 :release rl) :gate g :action 2))
      (* a)))


(definst wrpr
  [b 0 p 0 a 1 at 15 rl 40 m 1 rm 1 dp 1 g 1 fs 1 ws 0.1 e -1 ol 1 i 4] 
  (-> (warp1 1 b p fs ws e ol 0.0 i)
      (free-verb m rm dp)
      (* (env-gen (asr :attack at :curve 1 :release rl) :gate g :action FREE))
      (* a)))


(definst wrplf
  [b 0 p 0 a 1 at 15 rl 40 lf 2000 g 1 fs 1 ws 0.1 e -1 ol 1 i 4] 
  (-> (warp1 1 b p fs ws e ol 0.0 i)
      (lpf lf)
      (* (env-gen (asr :attack at :curve 1 :release rl) :gate g :action FREE))
      (* a)))


(definst wrphf
  [b 0 p 0 a 1 at 15 rl 40 hf 200 g 1 fs 1 ws 0.1 e -1 ol 1 i 4] 
  (-> (warp1 1 b p fs ws e ol 0.0 i)
      (hpf hf)
      (* (env-gen (asr :attack at :curve 1 :release rl) :gate g :action FREE))
      (* a)))


(definst wrprlf
  [b 0 p 0 a 1 at 15 rl 40 lf 2000 m 1 rm 1 dp 1 g 1 fs 1 ws 0.1 e -1 ol 1 i 4] 
  (-> (warp1 1 b p fs ws e ol 0.0 i)
      (lpf lf)
      (free-verb m rm dp)
      (* (env-gen (asr :attack at :curve 1 :release rl) :gate g :action FREE))
      (* a)))


(definst wrprlfo
  [b 0 p 0 a 1 at 15 rl 40 lf 2000 m 1 rm 1 dp 1 g 1 fs 1 ws 0.1 e -1 ol 1 i 4] 
  (-> (warp1 1 b (+ 0.4 (* 0.1 (sin-osc:kr p))) fs ws e ol 0.0 i)
      (lpf lf)
      (free-verb m rm dp)
      (* (env-gen (asr :attack at :curve 1 :release rl) :gate g :action FREE))
      (* a)))


(definst tri
  [a 1 at 15 rl 40 m 1 rm 1 dp 1 g 1 f 440] 
  (-> (lf-tri f)
      (free-verb m rm dp)
      (* (env-gen (asr :attack at :curve 1 :release rl) :gate g :action FREE))
      (* a)))


;; Track 1

(def t1 (tg 5 0.6 2000 125 0.5))
(def t2 (tgl 0.18))
(c t1 :r 0.4)
(c t1 :r 0.35)
(c t2 :r 0.4)
(c t2 :r 0.36)
(c t1 :r 0.55)
(c t2 :r 0.5 :a 1.6)
(do
  (c t1 :r 0.2 :a 0.45)
  (c t2 :r 0.2 :a 1.55))
(def s1 (sio 66.5 0.5))
(c s1 :f 58.4 :a 0.35)
(def t3 (tg 10 2 8000 400 0.29 20))
(c t3 :r 1.4)
(c s1 :g 0)
(def s2 (sio 5919.91 0.020 10))
(c s2 :g 0)
(c t3 :g 0)
(do
  (c t1 :r 0.36)
  (c t2 :r 0.4))
(c t2 :r 0.36)
(c t1 :g 0)
(c t2 :r 0.2)
(c t2 :r 0.18 :g 0)


;; Track 2

(def wpl (wrplf s4 :p 0.83 :fs 0.4 :ws 10 :a 1.5 :lf 4000))
(def wpr (wrpr s5 :p 0.27 :fs 0.5 :ws 9 :a 1.2))
(c wpl :fs 0.25)
(c wpl :ws 12)
(c wpr :fs 0.5 :ws 9)
(c wpr :fs 0.675 :ws 13)
(c wpr :ol 2)
(c wpl :fs 0.4 :ws 14)
(c wpl :ol 3)
(c wpr :fs 0.275 :ws 10)
(c wpl :g 0)
(c wpr :ol 1)
(c wpr :fs 0.22)
(def wph (wrphf s6 :p 0.23 :fs 0.8 :ws 16.0 :a 1.4 :hf 400))
(c wph :p 0.7)
(c wph :fs 1.1)
(c wph :fs 0.65 :p 0.1 :ol 2)
(c wpr :g 0)
(c wph :g 0)


;; Track 3

(def wr1 (wrplf s0 :p 0.83 :fs 0.4 :ws 14 :a 1.5 :lf 3000))
(def wr2 (wrprlf s1 :p 0.1 :fs 0.8 :ws 8 :a 1.1 :lf 5000))
(def wr3 (wrprlf s3 :p 0.01 :fs 0.8 :ws 11 :a 1.7 :lf 8000 :ol 3))
(c wr3 :a 1.7 :p 0.4 :ws 8 :fs 0.35)
(c wr2 :p 0.2 :ws 6 :fs 0.6)
(c wr2 :fs 0.42)
(c wr1 :p 0.4 :ws 13 :fs 0.63)
(c wr1 :fs 0.55)
(c wr3 :g 0)
(c wr1 :p 0.4 :ws 13 :fs 0.51)
(c wr2 :g 0)
(c wr1 :fs 0.48)
(c wr1 :g 0)
(def wl1 (wrprlfo s0 :p 2.3 :fs 0.4 :ws 13 :a 1.5))
(def wl2 (wrprlfo s2 :p 3.6 :fs 0.7 :ws 13))
(def wl3 (wrprlfo s3 :p 2.8 :fs 0.8 :ws 10 :a 1.7))
(c wl3 :p 2.8 :ws 11 :fs 0.95)
(c wl3 :ws 10 :fs 0.875)
(c wl2 :p 3.2 :ws 12 :fs 0.82)
(c wl2 :fs 0.8)
(c wl1 :p 2.4 :fs 0.8 :ws 9)
(c wl1 :fs 0.6)
(c wl2 :g 0)
(c wl3 :g 0)
(c wl1 :p 2.9 :fs 0.45 :ws 6)
(c wl1 :p 2.91 :fs 0.48 :ws 4)
(def wrh (wrphf s0 :p 2.3 :fs 6.2 :ws 0.2 :a 0.8))
(c wrh :p 1.3 :fs 3.1 :ws 12)
(c wrh :p 1.1 :fs 2.7 :ws 11)
(c wrh :p 0.1 :fs 0.7 :ws 8)
(c wrh :p 1.3 :fs 4.4 :ws 9.02)
(c wrh :p 0.1 :fs 0.7 :ws 8)
(c wl1 :g 0)
(c wrh :g 0)
(c wrh :hf 100)


;; Track 4

(def tr1 (tri :f 64 :a 0.4))
(def tr2 (tri :f 72 :a 0.4 :at 20))
(def tr3 (tri :f 68 :a 0.4 :at 20))
(c tr3 :f 58)
(c tr2 :f 59)
(c tr1 :f 60)
(c tr1 :f 55)
(c tr1 :f 82.41)
(c tr2 :f 61.74)
(c tr3 :f 65.41)
(def sm1 (wrprlfo s7 :p 2.3 :fs 0.6 :ws 13 :a 0.9))
(c sm1 :fs 0.575 :ws 4 :p 2.29)
(c tr2 :f 58.4)
(c tr3 :f 78.21)
(def sm2 (wrprlfo s7 :p 4.2 :fs 1.3 :ws 13 :a 0.9))
(c tr1 :f 110)
(c tr1 :f 130.81)
(c sm2 :fs 1.9 :ws 2)
(c sm1 :fs 1.7 :ws 6)
(c sm1 :fs 0.4 :ws 6)
(c sm2 :fs 0.475 :ws 6)
(c tr1 :f 73.42)
(c tr2 :f 51.91)
(c tr2 :f 58.27)
(c tr2 :f 65.41)
(c sm1 :fs 0.6 :ws 0.2)
(c sm2 :fs 0.65 :ws 1.525)
(c sm1 :fs 0.525 :ws 12.2)
(c tr2 :f 71.205)
(c tr3 :f 78.21)
(c tr1 :f 82.41)
(c tr1 :g 0)
(c tr3 :g 0)
(c tr2 :g 0)
(c sm2 :fs 0.5125 :ws 14)
(c sm2 :fs 4.941 :ws 0.04)
(c sm2 :fs 4.2 :ws 0.04)
(c sm1 :fs 4.9 :ws 0.039)
(c sm2 :ws 0.038)
(c sm1 :ws 0.3)
(c sm1 :fs 0.49 :ws 13)
(c sm2 :fs 0.42 :ws 0.04)
(c sm2 :g 0)
(c sm1 :g 0)


(stop)
