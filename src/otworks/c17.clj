(ns otworks.c17
  (:use overtone.live)
  (:require [otworks.functions :refer [get-samples gen-inst]]
            [leipzig.melody :refer [bpm phrase where tempo]]
            [leipzig.chord :refer [root triad seventh ninth]]
            [leipzig.live :as live]
            [leipzig.scale :as scale]))

(get-samples "~/Producing/october26th-2016/smp/"
             (mapv #(str "s" %) (range 0 3)))

(def s0m (buffer-mix-to-mono s0))

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

(def x (rsin 80 4000 2500 100 1 10 10 0.9))
(ctl x :rmix 0)
(ctl x :hff 200)
(ctl x :dens 350)
(ctl x :dens 150)
(ctl x :dens 375)
(ctl x :hff 100)
(ctl x :lff 2000)
(ctl x :lff 200 :dens 100 :rmix 1)
(def y (pgrain s0m 10 0.2 2.52 5000 1000 2.5 1 1 0.1))
(ctl y :rate 2.12 :dur 8)
(ctl y :rate 0.62 :dur 10)
(ctl y :hff 50)
(ctl y :gate 0)
(ctl x :rmix 0 :dens 450 :lff 800 :hff 100)
(ctl x :rmul 2000 :dens 200)
(ctl x :hff 100 :lff 2000)
(ctl x :dens 600)
(ctl x :hff 100)
(ctl x :lff 2700)
(ctl x :dens 200)
(ctl x :dens 600)
(ctl x :rmul 2500)
(ctl x :rmix 1)
(ctl x :hff 500)
(ctl x :hff 700)
(ctl x :lff 900)
(ctl x :dens 150)
(ctl x :gate 0)

(stop)






