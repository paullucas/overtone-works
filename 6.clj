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

  (definst wrprlfo [buf 0 pointer 0.1 amp 1 att 15 lff 2000 gate 1 freq-scale 1 window-size 0.1 envbufnum -1 overlaps 1 interp 4 mix 1 room 1 damp 1]
    (let [src(* (free-verb :in (lpf :in (warp1 :num-channels 1 :bufnum buf :pointer (+ 0.4 (* 0.1 (sin-osc:kr pointer))) :freq-scale freq-scale :window-size window-size
                                               :envbufnum envbufnum :overlaps overlaps :window-rand-ratio 0.0 :interp interp) :freq lff) :mix mix :room room :damp damp)
                (env-gen (asr :attack att :curve 1 :release 40) :gate gate :action FREE))]
      (* src amp)))
)

(wrplf one :pointer 0.83 :freq-scale 0.4 :window-size 14 :amp 1.5 :lff 3000)                       ;;54
(wrprlf two :pointer 0.1 :freq-scale 0.8 :window-size 8 :amp 1.1 :lff 5000)                        ;;55
(wrprlf four :pointer 0.01 :freq-scale 0.8 :window-size 11 :amp 1.7 :lff 8000 :overlaps 3)         ;;56
(ctl 56 :amp 1.7 :pointer 0.4 :window-size 8 :freq-scale 0.35)
(ctl 55 :pointer 0.2 :window-size 6 :freq-scale 0.6)
(ctl 55 :pointer 0.2 :window-size 6 :freq-scale 0.42)
(ctl 54 :pointer 0.4 :window-size 13 :freq-scale 0.63)
(ctl 54 :pointer 0.4 :window-size 13 :freq-scale 0.55)
(ctl 56 :gate 0)
(ctl 54 :pointer 0.4 :window-size 13 :freq-scale 0.51)
(ctl 55 :gate 0)
(ctl 54 :pointer 0.4 :window-size 13 :freq-scale 0.48)
(ctl 54 :gate 0)
(wrprlfo one :pointer 2.3 :freq-scale 0.4 :window-size 13 :amp 1.5)                                ;;57
(wrprlfo three :pointer 3.6 :freq-scale 0.7 :window-size 13)                                       ;;58
(wrprlfo four :pointer 2.8 :freq-scale 0.8 :window-size 10 :amp 1.7)                               ;;59
(ctl 59 :pointer 2.8 :window-size 11 :freq-scale 0.95)
(ctl 59 :pointer 2.8 :window-size 10 :freq-scale 0.875)
(ctl 58 :pointer 3.2 :window-size 12 :freq-scale 0.82)
(ctl 58 :freq-scale 0.8)
(ctl 57 :pointer 2.4 :freq-scale 0.8 :window-size 9)
(ctl 57 :pointer 2.4 :freq-scale 0.6 :window-size 9)
(ctl 58 :gate 0)
(ctl 59 :gate 0)
(ctl 57 :pointer 2.9 :freq-scale 0.45 :window-size 6)
(ctl 57 :pointer 2.91 :freq-scale 0.48 :window-size 4)
(wrphf one :pointer 2.3 :freq-scale 6.2 :window-size 0.2 :amp 0.8)                                 ;;60
(ctl 60 :pointer 1.3 :freq-scale 3.1 :window-size 12)
(ctl 60 :pointer 1.1 :freq-scale 2.7 :window-size 11)
(ctl 60 :pointer 0.1 :freq-scale 0.7 :window-size 8)
(ctl 60 :pointer 1.3 :freq-scale 4.4 :window-size 9.02)
(ctl 60 :pointer 0.1 :freq-scale 0.7 :window-size 8)
(ctl 57 :gate 0)
(ctl 60 :gate 0)
(ctl 60 :hff 100)

;;
;;
;;

(do
  (def sample-path "~/Producing/june17th-2016/")
  (defn get-sample [path]
    (sample (str sample-path path ".wav")))
  (def five (get-sample "1"))
  (def six (get-sample "2"))
  (def seven (get-sample "3"))
)

(wrplf five :pointer 0.83 :freq-scale 0.4 :window-size 10 :amp 1.5 :lff 4000)                      ;;61
(wrpr six :pointer 0.27 :freq-scale 0.5 :window-size 9 :amp 1.2)                                   ;;62
(ctl 61 :freq-scale 0.25)
(ctl 61 :window-size 12)
(ctl 62 :freq-scale 0.5 :window-size 9)
(ctl 62 :freq-scale 0.675 :window-size 13)
(ctl 62 :overlaps 2)
(ctl 61 :freq-scale 0.4 :window-size 14)
(ctl 61 :overlaps 3)
(ctl 62 :freq-scale 0.275 :window-size 10)
(ctl 61 :gate 0)
(ctl 62 :overlaps 1)
(ctl 62 :freq-scale 0.22)
(wrphf seven :pointer 0.23 :freq-scale 0.8 :window-size 16.0 :amp 1.4 :hff 400)                    ;;63
(ctl 63 :pointer 0.7)
(ctl 63 :freq-scale 1.1)
(ctl 63 :freq-scale 0.65 :pointer 0.1 :overlaps 2)
(ctl 62 :gate 0)
(ctl 63 :gate 0)
