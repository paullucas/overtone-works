(defn get-samples [folder-path filenames]
  (doseq [filename filenames]
    (def filename (load-sample (str folder-path filename ".wav")))))

(get-samples "~/Producing/july3rd-2016/samples/" ["1" "2" "3"])

;;

(def ugen-template "(definst ugen-name [buf 0 pointer 0 amp 1 att 15 lff 2000 hff 200 mix 1 room 1 damp 1 gate 1 freq-scale 1 window-size 0.1 envbufnum -1 overlaps 1 interp 4] (let [src(* ugen-input (env-gen (asr :attack att :curve 1 :release 40) :gate gate :action FREE))] (* src amp)))")

(defn get-ugen [x]
  (case x
    "warp1" "(warp1 :num-channels 1 :bufnum buf :pointer pointer :freq-scale freq-scale :window-size window-size :envbufnum envbufnum :overlaps overlaps :window-rand-ratio 0.0 :interp interp)"
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

(gen-inst "sampler" ["free-verb" "lpf" "warp1"])

;; 

(sampler 1)
