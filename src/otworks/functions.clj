(ns otworks.functions
  (:require
   [clojure.string :as s]
   [overtone.core :refer [load-sample buffer-mix-to-mono]]))

(defn get-samples
  "Load all samples in a directory."
  [folder-path filenames]
  (doseq [filename filenames]
    (intern *ns* (symbol filename) (load-sample (str folder-path filename ".wav")))))

(defn get-mono-samples
  "Load all samples in a directory as monophonic buffers."
  [folder-path filenames]
  (doseq [filename filenames]
    (intern *ns* (symbol (str filename "m"))
            (buffer-mix-to-mono (load-sample (str folder-path filename ".wav"))))))

(def ugen-template
  "(definst ugen-name [buf 0 pointer 0 amp 1 att 15 rel 40 lff 2000 hff 200 mix 1 room 1 damp 1 gate 1 rate 1 freq 440 start-pos 0.0 freq-scale 1 window-size 0.1 envbufnum -1 overlaps 1 interp 4] (let [src(* ugen-input (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action FREE))] (* src amp)))")

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
  "Generate Synthdef with nested Ugens (string-based solution)"
  ([name ugens]
   (gen-inst name (drop-last ugens) (get-ugen (last ugens))))
  ([name ugens result]
   (if (= (count ugens) 1)
     (let [ugen-out (s/replace (get-ugen (first ugens)) #"ugen-input" result)
           ugen-final (s/replace (s/replace ugen-template #"ugen-input" ugen-out) #"ugen-name" name)]
       (eval (read-string ugen-final)))
     (let [ugen-out (s/replace (get-ugen (last ugens)) #"ugen-input" result)]
       (gen-inst name (drop-last ugens) ugen-out)))))

(defmacro fetch-ugen-macro [x]
  `(condp = ~x
     ~'warp1 (warp1 :num-channels 1 :bufnum ~'buf :pointer ~'pointer :freq-scale ~'freq-scale :window-size ~'window-size :envbufnum ~'envbufnum :overlaps ~'overlaps :window-rand-ratio 0.0 :interp ~'interp)
     ~'lpf (lpf :in ~''ugen-input :freq ~'lff)))

(defmacro replace-ugen-input-macro [ugen child]
  `(loop [acc# '() uu# ~ugen]
     (if-not (empty? uu#)
       (let [u# (first uu#)]
         (recur (if (= ~''ugen-input u#)
                  (conj acc# ~child)
                  (conj acc# u#))
                (rest uu#)))
       (reverse acc#))))

(defmacro gen-ugens-macro [ugens]
  `(reduce (fn [current-ugen# ugen#]
             (replace-ugen-input (fetch-ugen ugen#) current-ugen#))
           '()
           (reverse ~ugens)))

(def defaults
  '[buf 0 pointer 0 amp 1 att 15 lff 2000 hff 200 mix 1 room 1 damp 1 gate 1 rate 1 freq 440 start-pos 0.0 freq-scale 1 window-size 0.1 envbufnum -1 overlaps 1 interp 4])

(defmacro gen-inst-macro
  "Generate Synthdef with nested Ugens (WIP macro-based solution)"
  [name ugens]
  `(definst ~name ~defaults
     (let [src# (* (gen-ugens ~ugens) (env-gen (asr :attack ~'att :curve 1 :release 40) :gate ~'gate :action FREE))]
       (* src# ~'amp))))
