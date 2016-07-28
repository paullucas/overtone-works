;; get-samples - Load all samples in a folder

(defn get-samples [folder-path filenames]
  (doseq [filename filenames]
    (def filename (load-sample (str folder-path filename ".wav")))))

(get-samples "~/Producing/july3rd-2016/samples/" ["1" "2" "3"])

;;

;; gen-inst - Generate Synthdef with nested Ugens (string-based solution)

(def ugen-template
  "(definst ugen-name [buf 0 pointer 0 amp 1 att 15 lff 2000 hff 200 mix 1 room 1 damp 1 gate 1 rate 1 freq 440 start-pos 0.0 freq-scale 1 window-size 0.1 envbufnum -1 overlaps 1 interp 4] (let [src(* ugen-input (env-gen (asr :attack att :curve 1 :release 40) :gate gate :action FREE))] (* src amp)))")

(defn get-ugen [x]
  (case x
    "warp1" "(warp1 :num-channels 1 :bufnum buf :pointer pointer :freq-scale freq-scale :window-size window-size :envbufnum envbufnum :overlaps overlaps :window-rand-ratio 0.0 :interp interp)"
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

(gen-inst "sampler" ["free-verb" "lpf" "warp1"])

;;

;; gen-inst - Generate Synthdef with nested Ugens (macro-based solution)

(defmacro fetch-ugen [x]
  `(condp = ~x
     ~'warp1 (warp1 :num-channels 1 :bufnum ~'buf :pointer ~'pointer :freq-scale ~'freq-scale :window-size ~'window-size :envbufnum ~'envbufnum :overlaps ~'overlaps :window-rand-ratio 0.0 :interp ~'interp)
     ~'lpf (lpf :in ~''ugen-input :freq ~'lff)))

(defmacro replace-ugen-input [ugen child]
  `(loop [acc# '() uu# ~ugen]
     (if-not (empty? uu#)
       (let [u# (first uu#)]
         (recur (if (= ~''ugen-input u#)
                  (conj acc# ~child)
                  (conj acc# u#))
                (rest uu#)))
       (reverse acc#))))

(defmacro gen-ugens [ugens]
  `(reduce (fn [current-ugen# ugen#]
             (replace-ugen-input (fetch-ugen ugen#) current-ugen#))
           '()
           (reverse ~ugens)))

(def defaults
  '[buf 0 pointer 0 amp 1 att 15 lff 2000 hff 200 mix 1 room 1 damp 1 gate 1 rate 1 freq 440 start-pos 0.0 freq-scale 1 window-size 0.1 envbufnum -1 overlaps 1 interp 4])

(defmacro gen-inst
  [name ugens]
  `(definst ~name ~defaults
     (let [src# (* (gen-ugens ~ugens) (env-gen (asr :attack ~'att :curve 1 :release 40) :gate ~'gate :action FREE))]
       (* src# ~'amp))))

;; 
