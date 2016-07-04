(do
  (def sample-path "~/Producing/july3rd-2016/samples/")
  (defn get-sample [path]
    (sample (str sample-path path ".wav")))

  (def one (get-sample "1"))
  (def two (get-sample "2"))
  (def three (get-sample "3"))
  (def four (get-sample "4"))

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

  (definst wrprlf [buf 0 pointer 0 amp 1 att 15 lff 2000 gate 1 freq-scale 1 window-size 0.1 envbufnum -1 overlaps 1 interp 4 mix 1 room 1 damp 1]
    (let [src(* (free-verb :in (lpf :in (warp1 :num-channels 1 :bufnum buf :pointer pointer :freq-scale freq-scale :window-size window-size
                                :envbufnum envbufnum :overlaps overlaps :window-rand-ratio 0.0 :interp interp) :freq lff) :mix mix :room room :damp damp)
                (env-gen (asr :attack att :curve 1 :release 40) :gate gate :action FREE))]
      (* src amp)))
)

(wrplf one :pointer 0.83 :freq-scale 0.4 :window-size 14 :amp 1.5 :lff 3000)
(wrprlf two :pointer 0.1 :freq-scale 0.8 :window-size 8 :amp 1.1 :lff 5000)
(wrprlf four :pointer 0.01 :freq-scale 0.8 :window-size 11 :amp 1.7 :lff 8000 :overlaps 3)
(ctl 49 :amp 1.7 :pointer 0.4 :window-size 8 :freq-scale 0.35)

(ctl 47 :gate 0)
(ctl 48 :gate 0)
(ctl 49 :gate 0)
(stop)
