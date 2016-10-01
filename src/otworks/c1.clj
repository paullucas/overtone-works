(def sample-path "~/Producing/April5th-2016/samples/")
(defn get-sample [path]
  (sample (str sample-path path ".wav")))
(def one (get-sample "1"))
(def two (get-sample "2"))

(definst pbv [buf 0 rate 1 amp 1 att 10 mix 1 room 1 damp 1 gate 1]
  (let [src(* (free-verb :in (play-buf :num-channels 1 :bufnum buf :rate rate :trigger 1.0 :start-pos 0.0 :loop 1.0 :action 1) :mix mix :room room :damp damp)
              (env-gen (asr :attack att :curve 1 :release 20) :gate gate :action FREE))]
    (* src amp)))

(pbv one :amp 1.0) ;;38
(pbv one :rate 0.5 :amp 1.5 :attack 15 :release 40) ;;39
(ctl 38 :rate 0.8)
(ctl 38 :rate 0.4)
(pbv two :rate 1.2 :amp 2) ;;40
(ctl 39 :rate 0.6)
(ctl 38 :rate 0.8)
(ctl 40 :rate 1.8)
(ctl 39 :rate 0.4)
(ctl 40 :rate 1.775)
(ctl 38 :rate 22.8)
(ctl 38 :rate 45.6)
(ctl 38 :rate 3.2)
(ctl 38 :rate 1.6)
(ctl 38 :rate 0.4)
(ctl 40 :rate 1.8)
(ctl 40 :gate 0)
(ctl 38 :gate 0)
(ctl 39 :gate 0)

(stop)

(pp-node-tree)
