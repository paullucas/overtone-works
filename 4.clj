(def sample-path "~/Producing/june17th-2016/")

(defn get-sample [path]
  (sample (str sample-path path ".wav")))

(do 
  (def one (get-sample "1"))
  (def two (get-sample "2"))
  (def three (get-sample "3"))
)

(definst wrpr [buf 0 pointer 0 amp 1 att 15 mix 1 room 1 damp 1 gate 1 freq-scale 1 window-size 0.1 envbufnum -1 overlaps 1 interp 4]
 (let [src(* (free-verb :in (warp1 :num-channels 1 :bufnum buf :pointer pointer :freq-scale freq-scale :window-size window-size 
                                   :envbufnum envbufnum :overlaps overlaps :window-rand-ratio 0.0 :interp interp) :mix mix :room room :damp damp)
             (env-gen (asr :attack att :curve 1 :release 40) :gate gate :action FREE))]
     (* src amp)))

(definst wrplf [buf 0 pointer 0 amp 1 att 15 lff 2000 gate 1 freq-scale 1 window-size 0.1 envbufnum -1 overlaps 1 interp 4]
 (let [src(* (lpf :in (warp1 :num-channels 1 :bufnum buf :pointer pointer :freq-scale freq-scale :window-size window-size 
                                   :envbufnum envbufnum :overlaps overlaps :window-rand-ratio 0.0 :interp interp) :freq lff)
             (env-gen (asr :attack att :curve 1 :release 40) :gate gate :action FREE))]
     (* src amp)))

(definst wrphf [buf 0 pointer 0 amp 1 att 15 hff 200 gate 1 freq-scale 1 window-size 0.1 envbufnum -1 overlaps 1 interp 4]
 (let [src(* (hpf :in (warp1 :num-channels 1 :bufnum buf :pointer pointer :freq-scale freq-scale :window-size window-size 
                                   :envbufnum envbufnum :overlaps overlaps :window-rand-ratio 0.0 :interp interp) :freq hff)
             (env-gen (asr :attack att :curve 1 :release 40) :gate gate :action FREE))]
     (* src amp)))


(wrplf one :pointer 0.83 :freq-scale 0.4 :window-size 10 :amp 1.5 :lff 4000)              ;;46
(wrpr two :pointer 0.27 :freq-scale 0.5 :window-size 9 :amp 1.2)                          ;;47
(ctl 46 :freq-scale 0.25)
(ctl 46 :window-size 12)
(ctl 47 :freq-scale 0.5 :window-size 9)
(ctl 47 :freq-scale 0.675 :window-size 13)
(ctl 47 :overlaps 2)
(ctl 46 :freq-scale 0.4 :window-size 14)
(ctl 46 :overlaps 3)
(ctl 47 :freq-scale 0.275 :window-size 10)
(ctl 46 :gate 0)
(ctl 47 :overlaps 1)
(ctl 47 :freq-scale 0.22)
(wrphf three :pointer 0.23 :freq-scale 0.8 :window-size 16.0 :amp 1.4 :hff 400)           ;;48
(ctl 48 :pointer 0.7)
(ctl 48 :freq-scale 1.1)
(ctl 48 :freq-scale 0.65 :pointer 0.1 :overlaps 2)
(ctl 47 :gate 0)
(ctl 48 :gate 0)

(stop)
