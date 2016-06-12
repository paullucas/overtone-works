(def sample-path "~/Producing/june10th-2016/")

(defn get-sample [path]
  (sample (str sample-path path ".wav")))

(def one (get-sample "1"))

(definst wrp [buf 0 pointer 0 amp 1 att 10 mix 1 room 1 damp 1 gate 1 freq-scale 1 window-size 0.1 envbufnum -1 overlaps 8 interp 1]
 (let [src(* (free-verb :in (warp1 :num-channels 1 :bufnum buf :pointer pointer :freq-scale freq-scale :window-size window-size :envbufnum envbufnum :overlaps overlaps :window-rand-ratio 0.0 :interp interp) :mix mix :room room :damp damp)
             (env-gen (asr :attack att :curve 1 :release 20) :gate gate :action FREE))]
     (* src amp)))

(wrp one :pointer 0.83 :freq-scale 2.0 :window-size 10.0 :overlaps 1 :interp 4 :amp 1.5 :attack 15 :release 40) ;;38
(wrp one :pointer 0.23 :freq-scale 0.6 :window-size 6.0 :overlaps 1 :interp 4 :amp 1.5 :attack 15 :release 40) ;;39
(wrp one :pointer 0.93 :freq-scale 6.6 :window-size 6.0 :overlaps 1 :interp 4 :amp 1.0 :attack 15 :release 40) ;;40

(ctl 40 :pointer 0.83 :freq-scale 2.6 :window-size 1.0 :overlaps 1 :interp 4 :amp 1.0)
(ctl 40 :pointer 0.83 :freq-scale 3.9 :window-size 0.3 :overlaps 1 :interp 4 :amp 1.0)
(ctl 40 :pointer 0.83 :freq-scale 2.4 :window-size 0.2 :overlaps 1 :interp 4 :amp 1.0)
(ctl 40 :pointer 0.83 :freq-scale 1.4 :window-size 2.2 :overlaps 1 :interp 4 :amp 1.0)

(ctl 38 :gate 0)
(ctl 39 :gate 0)
(ctl 40 :gate 0)

(stop)
