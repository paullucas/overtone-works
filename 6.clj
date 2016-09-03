(defn get-samples [folder-path filenames]
  (doseq [filename filenames]
    (intern *ns* (symbol filename) (load-sample (str folder-path filename ".wav")))))

(def ugen-template
  "(definst ugen-name [buf 0 pointer 0 amp 1 att 15 lff 2000 hff 200 mix 1 room 1 damp 1 gate 1 rate 1 freq 440 start-pos 0.0 freq-scale 1 window-size 0.1 envbufnum -1 overlaps 1 interp 4] (let [src (* ugen-input (env-gen (asr :attack att :curve 1 :release 40) :gate gate :action FREE))] (* src amp)))")

(defn get-ugen [x]
  (case x
    "warp1" "(warp1 :num-channels 1 :bufnum buf :pointer pointer :freq-scale freq-scale :window-size window-size :envbufnum envbufnum :overlaps overlaps :window-rand-ratio 0.0 :interp interp)"
    "warp1-lfo" "(warp1 :num-channels 1 :bufnum buf :pointer (+ 0.4 (* 0.1 (sin-osc:kr pointer))) :freq-scale freq-scale :window-size window-size :envbufnum envbufnum :overlaps overlaps :window-rand-ratio 0.0 :interp interp)"
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

(gen-inst "wrpr" ["free-verb" "warp1"])
(gen-inst "wrplf" ["lpf" "warp1"])
(gen-inst "wrphf" ["hpf" "warp1"])
(gen-inst "wrprlf" ["free-verb" "lpf" "warp1"])
(gen-inst "wrprlfo" ["free-verb" "lpf" "warp1-lfo"])

(get-samples "~/Producing/july3rd-2016/samples/" ["smp1" "smp2" "smp3" "smp4"])

(def wrplf1 (wrplf smp1 :pointer 0.83 :freq-scale 0.4 :window-size 14 :amp 1.5 :lff 3000))
(def wrplf2 (wrprlf smp2 :pointer 0.1 :freq-scale 0.8 :window-size 8 :amp 1.1 :lff 5000))
(def wrplf3 (wrprlf smp4 :pointer 0.01 :freq-scale 0.8 :window-size 11 :amp 1.7 :lff 8000 :overlaps 3))
(ctl wrplf3 :amp 1.7 :pointer 0.4 :window-size 8 :freq-scale 0.35)
(ctl wrplf2 :pointer 0.2 :window-size 6 :freq-scale 0.6)
(ctl wrplf2 :pointer 0.2 :window-size 6 :freq-scale 0.42)
(ctl wrplf1 :pointer 0.4 :window-size 13 :freq-scale 0.63)
(ctl wrplf1 :pointer 0.4 :window-size 13 :freq-scale 0.55)
(ctl wrplf3 :gate 0)
(ctl wrplf1 :pointer 0.4 :window-size 13 :freq-scale 0.51)
(ctl wrplf2 :gate 0)
(ctl wrplf1 :pointer 0.4 :window-size 13 :freq-scale 0.48)
(ctl wrplf1 :gate 0)
(def wrprlfo1 (wrprlfo smp1 :pointer 2.3 :freq-scale 0.4 :window-size 13 :amp 1.5))
(def wrprlfo2 (wrprlfo smp3 :pointer 3.6 :freq-scale 0.7 :window-size 13))
(def wrprlfo3 (wrprlfo smp4 :pointer 2.8 :freq-scale 0.8 :window-size 10 :amp 1.7))
(ctl wrprlfo3 :pointer 2.8 :window-size 11 :freq-scale 0.95)
(ctl wrprlfo3 :pointer 2.8 :window-size 10 :freq-scale 0.875)
(ctl wrprlfo2 :pointer 3.2 :window-size 12 :freq-scale 0.82)
(ctl wrprlfo2 :freq-scale 0.8)
(ctl wrprlfo1 :pointer 2.4 :freq-scale 0.8 :window-size 9)
(ctl wrprlfo1 :pointer 2.4 :freq-scale 0.6 :window-size 9)
(ctl wrprlfo2 :gate 0)
(ctl wrprlfo3 :gate 0)
(ctl wrprlfo1 :pointer 2.9 :freq-scale 0.45 :window-size 6)
(ctl wrprlfo1 :pointer 2.91 :freq-scale 0.48 :window-size 4)
(def wrphf1 (wrphf smp1 :pointer 2.3 :freq-scale 6.2 :window-size 0.2 :amp 0.8))
(ctl wrphf1 :pointer 1.3 :freq-scale 3.1 :window-size 12)
(ctl wrphf1 :pointer 1.1 :freq-scale 2.7 :window-size 11)
(ctl wrphf1 :pointer 0.1 :freq-scale 0.7 :window-size 8)
(ctl wrphf1 :pointer 1.3 :freq-scale 4.4 :window-size 9.02)
(ctl wrphf1 :pointer 0.1 :freq-scale 0.7 :window-size 8)
(ctl wrprlfo1 :gate 0)
(ctl wrphf1 :gate 0)
(ctl wrphf1 :hff 100)

;;

(get-samples "~/Producing/june17th-2016/" ["s1" "s2" "s3"])

(def wrplf1 (wrplf s1 :pointer 0.83 :freq-scale 0.4 :window-size 10 :amp 1.5 :lff 4000))
(def wrpr1 (wrpr s2 :pointer 0.27 :freq-scale 0.5 :window-size 9 :amp 1.2))
(ctl wrplf1 :freq-scale 0.25)
(ctl wrplf1 :window-size 12)
(ctl wrpr1 :freq-scale 0.5 :window-size 9)
(ctl wrpr1 :freq-scale 0.675 :window-size 13)
(ctl wrpr1 :overlaps 2)
(ctl wrplf1 :freq-scale 0.4 :window-size 14)
(ctl wrplf1 :overlaps 3)
(ctl wrpr1 :freq-scale 0.275 :window-size 10)
(ctl wrplf1 :gate 0)
(ctl wrpr1 :overlaps 1)
(ctl wrpr1 :freq-scale 0.22)
(def wrphf1 (wrphf s3 :pointer 0.23 :freq-scale 0.8 :window-size 16.0 :amp 1.4 :hff 400))
(ctl wrphf1 :pointer 0.7)
(ctl wrphf1 :freq-scale 1.1)
(ctl wrphf1 :freq-scale 0.65 :pointer 0.1 :overlaps 2)
(ctl wrpr1 :gate 0)
(ctl wrphf1 :gate 0)
