(ns otworks.c17
  (:use overtone.live)
  (:require [otworks.functions :refer [get-samples get-mono-samples]]
            [leipzig.melody :refer [bpm phrase where tempo]]
            [leipzig.chord :refer [root triad seventh ninth]]
            [leipzig.live :as live]
            [leipzig.scale :as scale]))

(get-samples "~/Producing/october26th-2016/smp/"
             (mapv #(str "s" %) (range 0 3)))

(get-mono-samples "~/Producing/october26th-2016/smp/"
                  (mapv #(str "s" %) (range 0 1)))

(definst pgrain
  [rate 2.52 dur 10 lff 5000 hff 1000 amp 2.5 att 1 rel 1 t_trig 0.1 gate 1]
  (->
   (t-grains 2 (impulse:ar t_trig) s0m rate (* 0.1 (sin-osc:kr 0.2)) dur 0 amp 1)
   (lpf lff)
   (hpf hff)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))
   (free-verb 0.6 1 1)))

(definst rsin
  [dens 80 rmul 4000 lff 2500 hff 100 amp 1 att 10 rel 10 rmix 0.9 gate 1]
  (->
   (sin-osc (* rmul (lf-noise1:kr dens)))
   (* amp)
   (lpf lff)
   (hpf hff)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))
   (free-verb rmix 1 1)))

(def x (rsin))
(ctl x :rmix 0)
(ctl x :hff 200)
(ctl x :dens 350)
(ctl x :dens 150)
(ctl x :dens 375)
(ctl x :hff 100)
(ctl x :lff 2000)
(ctl x :lff 200 :dens 100 :rmix 1)
(def y (pgrain))
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
