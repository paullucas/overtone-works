(ns otworks.c19
  (:require [overtone.core]
            [otworks.functions :refer [get-samples get-mono-samples]]))

(connect-external-server)

(get-mono-samples "~/Producing/november4th-2016/chops/smpls/"
                  (mapv #(str "s" %) (range 3 6)))

(definst pgrain
  [buf 0 cpos 0.0 rate 1 lff 2000 hff 200 rel 10 gate 1]
  (->
   (t-grains 2 (impulse:ar 0.1) buf rate (* 0.1 (sin-osc:kr cpos)) 10 0 2.5 1)
   (lpf lff)
   (hpf hff)
   (free-verb 0.6 1 1)
   (* (env-gen (asr :attack 10 :curve 1 :release rel) :gate gate :action 2))))

(def x (pgrain s5m 0.2 0.54 7000 50 20))
(def y (pgrain s4m 0.2 0.4 8000 100))
(def z (pgrain s3m 0.25 0.72 8000 50))
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
(ctl z :gate 0)

(stop)
