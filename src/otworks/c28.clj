(ns otworks.c28
  (:require ;; [overtone.core :refer :all :rename {ctl c}]
   [overtone.live :refer :all :rename {ctl c}]
   [otworks.functions :refer [get-samples boot]]))

;; Init

;; (boot)


;; SynthDefs

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

;; 

(get-samples "~/Producing/august25th-2017/prefixed/t2/" (mapv #(str "s" % "f") (range 1 9)))

(def p1 (c13wr :b s2f :fs 0.1 :p 0.8 :ws 4 :lf 2000 :db 1.2 :hf 50 :at 20 :rl 40))
(def p2 (c13wr :b s1f :fs 0.05 :p 0.8 :ws 5 :lf 800 :db 1.2 :hf 50 :at 20 :rl 40))
(def p3 (c14pb :b s5f :r 0.25))
(c p3 :tr 0.3 :a 1.2)
(c p3 :tr 0.028)
(c p3 :tr 4 :g 0)
(c p1 :p 0.4 :ws 7)
(c p2 :p 0.1 :ws 8)
(def p4 (c13wr :b s3f :fs 0.08 :p 0.1 :ws 10 :lf 3000 :db 1.5 :hf 50 :at 20 :rl 40))
(def p5 (c13wr :b s6f :fs 0.35 :p 0.1 :ws 10 :lf 6000 :db 1.5 :hf 50 :at 20 :rl 40))
(c p4 :fs 0.5 :a 1.2)
(c p4 :fs 0.8 :ws 12)
(c p4 :fs 1.7)
(c p4 :fs 0.6)
(c p4 :fs 0.45)
(c p4 :fs 0.5)
(c p1 :fs 0.09)
(c p2 :fs 0.09)
(c p5 :g 0)
(c p4 :g 0)
(c p1 :fs 0.06)
(c p2 :fs 0.06)
(c p2 :g 0)
(c p1 :g 0)

(stop)
