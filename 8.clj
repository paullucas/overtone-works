(defn get-samples [folder-path filenames]
  (doseq [filename filenames]
    (intern *ns* (symbol filename) (load-sample (str folder-path filename ".wav")))))

(get-samples "~/Producing/july23rd-2016/smpls/" ["s1" "s2" "s3" "s4" "s5" "s6" "s7"])

(def ugen-template
  "(definst ugen-name [buf 0 pointer 0 amp 1 att 15 lff 2000 hff 200 mix 1 room 1 damp 1 gate 1 rate 1 freq 440 start-pos 0.0 freq-scale 1 window-size 0.1 envbufnum -1 overlaps 1 interp 4] (let [src(* ugen-input (env-gen (asr :attack att :curve 1 :release 40) :gate gate :action FREE))] (* src amp)))")

(defn get-ugen [x]
  (case x
    "warp1" "(warp1 :num-channels 1 :bufnum buf :pointer pointer :freq-scale freq-scale :window-size window-size :envbufnum envbufnum :overlaps overlaps :window-rand-ratio 0.0 :interp interp)"
    "warp1-lfo" "(warp1 :num-channels 1 :bufnum buf :pointer (+ 0.4 (* 0.1 (sin-osc:kr pointer))) :freq-scale freq-scale :window-size window-size :envbufnum envbufnum :overlaps overlaps :window-rand-ratio 0.0 :interp interp)"
    "play-buf" "(play-buf :num-channels 1 :bufnum buf :rate rate :trigger 1 :start-pos start-pos :loop 1 :action 1)"
    "sin-osc" "(sin-osc :freq freq)"
    "lf-saw" "(lf-saw :freq freq)"
    "lf-tri" "(lf-tri :freq freq)"
    "free-verb" "(free-verb :in ugen-input :mix mix :room room :damp damp)"
    "lpf" "(lpf :in ugen-input :freq lff)"
    "hpf" "(hpf :in ugen-input :freq hff)"))

(defn gen-inst
  ([name ugens]
   (gen-inst name (drop-last ugens) (get-ugen (last ugens))))
  ([name ugens result]
   (if (= (count ugens) 1)
     (let [ugen-out (clojure.string/replace (get-ugen (first ugens)) #"ugen-input" result)
           ugen-final (clojure.string/replace (clojure.string/replace ugen-template #"ugen-input" ugen-out) #"ugen-name" name)]
       (eval (read-string ugen-final)))
     (let [ugen-out (clojure.string/replace (get-ugen (last ugens)) #"ugen-input" result)]
       (gen-inst name (drop-last ugens) ugen-out)))))

(gen-inst "tri" ["free-verb" "lf-tri"])
(gen-inst "smplr" ["free-verb" "lpf" "warp1-lfo"])

(def tri1 (tri :freq 64 :amp 0.4))
(def tri2 (tri :freq 72 :amp 0.4 :att 20))
(def tri3 (tri :freq 68 :amp 0.4 :att 20))
(ctl tri3 :freq 58)
(ctl tri2 :freq 59)
(ctl tri1 :freq 60)
(ctl tri1 :freq 55)
(ctl tri1 :freq 82.41)
(ctl tri2 :freq 61.74)
(ctl tri3 :freq 65.41)
(def s2-smplr (smplr s2 :pointer 2.3 :freq-scale 0.6 :window-size 13 :amp 0.9))
(ctl s2-smplr :freq-scale 0.575 :window-size 4 :pointer 2.29)
(ctl tri2 :freq 58.4)
(ctl tri3 :freq 78.21)
(def s2-smplr2 (smplr s2 :pointer 4.2 :freq-scale 1.3 :window-size 13 :amp 0.9))
(ctl tri1 :freq 110)
(ctl tri1 :freq 130.81)
(ctl s2-smplr2 :freq-scale 1.9 :window-size 2)
(ctl s2-smplr :freq-scale 1.7 :window-size 6)
(ctl s2-smplr :freq-scale 0.4 :window-size 6)
(ctl s2-smplr2 :freq-scale 0.475 :window-size 6)
(ctl tri1 :freq 73.42)
(ctl tri2 :freq 51.91)
(ctl tri2 :freq 58.27)
(ctl tri2 :freq 65.41)
(ctl s2-smplr :freq-scale 0.6 :window-size 0.2)
(ctl s2-smplr2 :freq-scale 0.65 :window-size 1.525)
(ctl s2-smplr :freq-scale 0.525 :window-size 12.2)
(ctl tri2 :freq 71.205)
(ctl tri3 :freq 78.21)
(ctl tri1 :freq 82.41)
(ctl tri1 :gate 0)
(ctl tri3 :gate 0)
(ctl tri2 :gate 0)
(ctl s2-smplr2 :freq-scale 0.5125 :window-size 14)
(ctl s2-smplr2 :freq-scale 4.941 :window-size 0.04)
(ctl s2-smplr2 :freq-scale 4.2 :window-size 0.04)
(ctl s2-smplr :freq-scale 4.9 :window-size 0.039)
(ctl s2-smplr2 :window-size 0.038)
(ctl s2-smplr :window-size 0.3)
(ctl s2-smplr :freq-scale 0.49 :window-size 13)
(ctl s2-smplr2 :freq-scale 0.42 :window-size 0.04)
(ctl s2-smplr2 :gate 0)
(ctl s2-smplr :gate 0)
