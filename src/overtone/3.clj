(def sample-path "~/Producing/june13th-2016/")

(defn get-sample [path]
  (sample (str sample-path path ".wav")))

(do 
  (def one (get-sample "1"))
  (def two (get-sample "2"))
  (def three (get-sample "3"))
  (def four (get-sample "4"))
)


(definst wrpr [buf 0 pointer 0 amp 1 att 15 mix 1 room 1 damp 1 gate 1 freq-scale 1 window-size 0.1 envbufnum -1 overlaps 1 interp 4]
 (let [src(* (free-verb :in (warp1 :num-channels 1 :bufnum buf :pointer pointer :freq-scale freq-scale :window-size window-size 
                                   :envbufnum envbufnum :overlaps overlaps :window-rand-ratio 0.0 :interp interp) :mix mix :room room :damp damp)
             (env-gen (asr :attack att :curve 1 :release 40) :gate gate :action FREE))]
     (* src amp)))

(definst wrpf [buf 0 pointer 0 amp 1 att 15 lff 2000 gate 1 freq-scale 1 window-size 0.1 envbufnum -1 overlaps 1 interp 4]
 (let [src(* (lpf :in (warp1 :num-channels 1 :bufnum buf :pointer pointer :freq-scale freq-scale :window-size window-size 
                                   :envbufnum envbufnum :overlaps overlaps :window-rand-ratio 0.0 :interp interp) :freq lff)
             (env-gen (asr :attack att :curve 1 :release 40) :gate gate :action FREE))]
     (* src amp)))

(wrpr one :pointer 0.83 :freq-scale 0.4 :window-size 10.0 :amp 1.5)               ;;42
(wrpf three :lff 1000 :pointer 0.23 :freq-scale 0.8 :window-size 16.0 :amp 1.8)   ;;43
(wrpr two :pointer 0.27 :freq-scale 0.55 :window-size 9.0 :amp 0.7)               ;;44
(wrpf four :lff 2000 :pointer 0.27 :freq-scale 2.3 :window-size 12.0 :amp 1.9)    ;;45

(ctl 42 :gate 0)
(ctl 44 :gate 0)
(ctl 45 :overlaps 5)
(ctl 43 :overlaps 3)
(ctl 45 :freq-scale 2.8)
(ctl 43 :freq-scale 1.0)
(ctl 45 :freq-scale 2.2)
(ctl 43 :freq-scale 2.0)
(ctl 45 :freq-scale 1.2)
(ctl 43 :freq-scale 2.3)
(wrpr two :pointer 0.67 :freq-scale 9.55 :window-size 3.0 :amp 0.7)               ;;46
(ctl 46 :freq-scale 1.2 :window-size 11.4)
(ctl 46 :freq-scale 0.55 :window-size 14 :amp 0.8)
(ctl 46 :gate 0)
(ctl 45 :freq-scale 0.8)
(ctl 43 :freq-scale 0.8)
(ctl 45 :overlaps 1)
(ctl 45 :gate 0)
(ctl 43 :overlaps 1)
(ctl 43 :gate 0)

(stop)
