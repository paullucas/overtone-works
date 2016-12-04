(ns otworks.c19
  (:use overtone.live)
  (:require [otworks.functions :refer [get-samples get-mono-samples]]))

(get-mono-samples "~/Producing/november4th-2016/chops/smpls/"
                  (mapv #(str "s" %) (range 3 6)))

(get-mono-samples "~/Producing/november19th-2016/smpls/"
                  (mapv #(str "s" %) (range 0 2)))

(definst pgrain
  [buf 0 dur 1 cpos 0.0 rate 1 lff 2000 hff 200 amp 1 att 15 rel 40 t_trig 0 gate 1]
  (->
   (t-grains 2 (impulse:ar t_trig) buf rate (* 0.1 (sin-osc:kr cpos)) dur 0 amp 1)
   (lpf lff)
   (hpf hff)
   (free-verb 0.6 1 1)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))))

(def x (pgrain s5m 10 0.2 0.54 7000 50 2.5 10 20 0.1))
(def y (pgrain s4m 10 0.2 0.4 8000 100 2.5 10 10 0.1))
(def z (pgrain s3m 10 0.25 0.72 8000 50 2.5 10 10 0.1))
(do
  (ctl x :rate 3.9)
  (ctl z :rate 0.7))
(do
  (ctl x :rate 0.7)
  (ctl y :rate 0.45)
  (ctl z :rate 0.67))
(do
  (ctl x :rate 2.1)
  (ctl z :rate 0.65))
(ctl x :rate 5.7)
(do
  (ctl x :rate 0.95)
  (ctl y :rate 0.4))
(do
  (ctl x :rate 8)
  (ctl z :rate 0.6))
(do
  (ctl x :rate 2 :cpos 0.35)
  (ctl z :rate 0.7 :cpos 0.3))
(do
  (ctl x :rate 0.7 :cpos 0.15)
  (ctl z :cpos 0.45))
(ctl z :rate 0.73)
(ctl z :rate 0.72 :cpos 0.5)
(do
  (ctl x :rate 2 :cpos 0.75)
  (ctl y :rate 22 :amp 2))
(do
  (ctl x :rate 8)
  (ctl y :rate 28))
(ctl y :rate 32)
(do
  (ctl y :rate 8)
  (ctl z :rate 0.6)
  (ctl x :rate 6))
(do
  (ctl x :rate 22)
  (ctl y :rate 32)
  (ctl z :rate 0.64))
(do
  (ctl x :rate 12)
  (ctl y :rate 16)
  (ctl z :rate 0.62))
(ctl z :rate 0.72)
(do
  (ctl z :rate 1.41)
  (ctl x :rate 4.3)
  (ctl y :rate 7.1))
(do
  (ctl x :rate 22)
  (ctl y :rate 32)
  (ctl z :rate 2.64))
(do
  (ctl x :rate 12)
  (ctl y :rate 34)
  (ctl z :rate 0.94))
(do
  (ctl x :rate 0.42)
  (ctl y :rate 0.62)
  (ctl z :rate 0.84))
(do
  (ctl x :rate 44)
  (ctl y :rate 32))
(do
  (ctl x :rate 0.94)
  (ctl y :rate 0.92))
(do
  (ctl x :rate 0.7)
  (ctl y :rate 0.45)
  (ctl z :rate 0.7))
(ctl z :rate 0.67)
(do
  (ctl x :rate 22)
  (ctl y :rate 32)
  (ctl z :rate 0.64))
(do
  (ctl x :rate 12)
  (ctl y :rate 16)
  (ctl z :rate 0.62))
(do
  (ctl x :gate 0)
  (ctl y :gate 0))
(ctl z :cpos 0.2 :lpf 1000 :hff 50)
(ctl z :rate 0.7 :cpos 0.8)
(ctl z :gate 0)

(stop)

(def a (pgrain s0m 12 0.5 0.92 4000 100 2 10 10 0.075))
(def b (pgrain s1m 13 0.2 0.82 2000 100 2.5 10 10 0.0725))

(stop)

