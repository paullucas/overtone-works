(ns otworks.m2
  (:use overtone.live)
  (:require [otworks.functions :refer [get-samples gen-inst]]
            [leipzig.melody :refer [bpm phrase where tempo]]
            [leipzig.chord :refer [root triad seventh ninth]]
            [leipzig.live :as live]
            [leipzig.scale :as scale]))

(get-samples "~/Producing/october26th-2016/smp/"
             (mapv #(str "s" %) (range 0 7)))

(def s0m (buffer-mix-to-mono s0))
(def s1m (buffer-mix-to-mono s1))
(def s2m (buffer-mix-to-mono s2))
(def s3m (buffer-mix-to-mono s3))
(def s4m (buffer-mix-to-mono s4))
(def s5m (buffer-mix-to-mono s5))
(def s6m (buffer-mix-to-mono s6))

(definst pgrain [buf 0 dur 1 cpos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 t_trig 0 gate 1]
  (->
   (t-grains 2 (impulse:ar t_trig) buf rate (* 0.1 (sin-osc:kr cpos)) dur 0 amp 1)
   (lpf lff)
   (hpf hff)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))
   (free-verb 0.6 1 1)))

(definst rsin [dens 100 rmul 1000 lff 2000 hff 200 amp 1 att 15 rel 40 rmix 0.5 gate 1]
  (->
   (sin-osc (* rmul (lf-noise1:kr dens)))
   (* amp)
   (lpf lff)
   (hpf hff)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))
   (free-verb rmix 1 1)))

(definst pbuf [buf 0 start-pos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 gate 1 loop 1]
    (->
     (play-buf 2 buf rate 1 start-pos loop 2)
     (* amp)
     (lpf lff)
     (hpf hff)
     (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))
     (free-verb 0.5 1 1)))

(gen-inst "tri" ["free-verb" "lf-tri"])

(def x (pgrain s1m 10 0.2 0.42 5000 100 2.5 1 1 0.1))
(ctl x :cpos 0.5 :rate 0.42 :dur 2)
(def y (rsin 80 4000 2500 100 0.1 10 10 0.1))
(ctl y :dens 800 :rmul 2200)
(ctl y :rmix 1)
(ctl y :dens 1 :rmul 4000 :rmix 1)
(ctl y :lff 4000)
(ctl y :hff 100)
(ctl y :rmul 1000)
(ctl y :rmix 1)
(ctl y :gate 0)

(stop)

;; 

(def x (pgrain s5m 10 0.2 0.42 5000 100 2.5 1 1 0.1))
(def y (pgrain s6m 10 0.3 0.72 5000 100 2.5 1 1 0.1))
(ctl y :rate 0.7)
(ctl y :lff 7000 :hff 200)
(ctl x :rate 0.54)
(ctl y :cpos 0.4)
(ctl x :cpos 0.5)
(ctl y :t_trig 0.3)
(def z (pbuf s5 0.0 0.4 3000 100 1 10 10))
(do
  (ctl x :rate 0.48)
  (ctl y :rate 0.62))
(def a (pbuf s2 0.0 3.2 2000 1000 0.7 10 10))
(ctl a :rate 2.9)
(ctl a :rate 3.2)
(ctl a :rate 2.5)
(ctl a :rate 1.2 :amp 1.4)
(ctl a :rate 1.12)
(ctl a :rate 1.2)
(ctl a :rate 0.4 :lff 1000 :hff 70)
(ctl a :lff 2000 :hff 1000)
(do
  (ctl x :rate 1.48)
  (ctl y :rate 1.62))
(do
  (ctl x :rate 2.48)
  (ctl y :rate 2.62))
(do
  (ctl x :rate 0.48)
  (ctl y :rate 0.62))
(do
  (ctl x :rate 2.48)
  (ctl y :rate 2.62))
(do
  (ctl x :rate 2.68)
  (ctl y :rate 2.82))
(do
  (ctl x :rate 2.28)
  (ctl y :rate 2.12))
(do
  (ctl x :rate 0.48)
  (ctl y :rate 0.62))
(ctl a :rate 2.9)
(ctl a :rate 2.5)
(ctl a :rate 3.2)
(ctl a :rate 0.4)
(ctl a :rate 5.2)
(ctl a :rate 0.4)
(ctl a :rate 5.2)
(ctl a :rate 0.4)
(ctl a :rate 6.2)
(ctl a :gate 0)
(ctl z :rate 0.8)
(ctl z :rate 0.5)
(do
  (ctl x :rate 2.28)
  (ctl y :rate 2.12))
(do
  (ctl x :rate 2.48)
  (ctl y :rate 2.62))
(do
  (ctl x :rate 1.48)
  (ctl y :rate 1.62))
(do
  (ctl x :rate 0.48)
  (ctl y :rate 0.62))
(ctl z :rate 0.8)
(ctl z :rate 3.2)
(ctl z :rate 0.72)
(ctl z :rate 3.6)
(ctl z :rate 0.68)
(do
  (ctl x :rate 0.52)
  (ctl y :rate 0.66))
(do
  (ctl x :rate 0.58)
  (ctl y :rate 0.72))
(do
  (ctl x :rate 0.48)
  (ctl y :rate 0.62))
(ctl z :gate 0)
(def f (rsin 5 4000 2500 100 0.1 10 10 1))
(ctl f :rmul 4200)
(ctl f :rmix 0)
(ctl f :rmix 1)
(ctl f :rmul 1200)
(ctl f :gate 0)
(def tri1 (tri :freq 64 :amp 0.4))
(ctl tri1 :amp 0.5)
(ctl tri1 :freq 128)
(ctl tri1 :gate 0)
(do
  (ctl x :rate 2.28)
  (ctl y :rate 2.12))
(do
  (ctl x :rate 2.48)
  (ctl y :rate 2.62))
(do
  (ctl x :rate 1.48)
  (ctl y :rate 1.62))
(do
  (ctl x :rate 0.48)
  (ctl y :rate 0.62))
(ctl x :gate 0)
(ctl y :gate 0)
(ctl z :gate 0)
(def d (pbuf s6 0.0 0.35 8000 50 6 10 10))
(ctl d :rate 0.3)
(ctl d :gate 0)

(stop)

;; ;; ;;

(def x (pgrain s5m 10 0.2 0.42 5000 100 2.5 1 1 0.1))
(def y (pgrain s6m 10 0.3 0.72 5000 100 2.5 1 1 0.1))
(ctl y :rate 0.7)
(ctl y :lff 7000 :hff 200)
(ctl x :rate 0.54)
(ctl y :cpos 0.4)
(ctl x :cpos 0.5)
(ctl y :t_trig 0.3)
(def z (pbuf s5 0.0 0.4 3000 100 1 10 10))
(do
  (ctl x :rate 0.48)
  (ctl y :rate 0.62))

(def a (pbuf s2 0.0 3.2 2000 1000 0.7 10 10))

(ctl a :rate 2.9)
(ctl a :rate 3.2)
(ctl a :rate 2.5)

(ctl a :rate 3.2)
(ctl a :rate 2.9)
(ctl a :rate 2.5)

(ctl a :rate 3.2)
(ctl a :rate 2.9)
(ctl a :rate 2.5)

(ctl a :rate 4.2)
(ctl a :rate 4.1)
(ctl a :rate 3.8)

(ctl a :rate 4.2)
(ctl a :rate 4.1)
(ctl a :rate 4.6)

(ctl a :rate 4.9)
(ctl a :rate 4.85)
(ctl a :rate 2.25)

(ctl a :rate 0.55)

(ctl a :gate 0)

(do
  (ctl x :rate 2.28)
  (ctl y :rate 2.12))

(do
  (ctl x :rate 1.48)
  (ctl y :rate 1.62))

(do
  (ctl x :rate 0.48)
  (ctl y :rate 0.62))

(do
  (ctl x :rate 2.52)
  (ctl y :rate 2.12))

(do
  (ctl x :rate 2.32)
  (ctl y :rate 2.02))

(do
  (ctl x :rate 2.72)
  (ctl y :rate 2.12))

(do
  (ctl x :rate 1.2)
  (ctl y :rate 1.12))

(do
  (ctl x :rate 1.01)
  (ctl y :rate 1.99))

(do
  (ctl x :rate 1.1)
  (ctl y :rate 0.99))

(do
  (ctl x :rate 2.32)
  (ctl y :rate 2.02))

(do
  (ctl x :rate 2.72)
  (ctl y :rate 2.12))

(do
  (ctl x :rate 2.16)
  (ctl y :rate 1.89))

(do
  (ctl x :rate 1.2)
  (ctl y :rate 1.12))

(do
  (ctl x :rate 1.01)
  (ctl y :rate 1.99))

(do
  (ctl x :rate 1.1)
  (ctl y :rate 0.99))

(ctl x :gate 0)
(ctl y :gate 0)
(ctl a :gate 0)
(ctl z :gate 0)

;;

(def y (rsin 80 4000 2500 100 0.1 10 10 0.1))
(ctl y :dens 800 :rmul 2200)
(ctl y :rmix 1)
(ctl y :dens 1 :rmul 4000 :rmix 1)
(ctl y :lff 4000)
(ctl y :hff 100)
(ctl y :rmul 1000)
(ctl y :rmix 1)
(ctl y :gate 0)

(stop)

;;





