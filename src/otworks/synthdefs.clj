(ns otworks.synthdefs
  (:require
   [overtone.core :refer [definst env-gen asr warp1 free-verb
                          lpf hpf sin-osc sin-osc:kr lf-tri
                          t-grains play-buf lf-noise1:kr impulse:ar FREE]]))

(definst wrpr
  "Granular -> Reverb -> Envelope -> Amp"
  [buf 0
   ptr 0
   amp 1
   att 15
   rel 40
   mix 1
   room 1
   damp 1
   gate 1
   rate 1
   fscale 1
   wsize 0.1
   ebn -1
   olaps 1
   interp 4] 
  (->
   (warp1 1 buf ptr fscale wsize ebn olaps 0.0 interp)
   (free-verb mix room damp)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action FREE))
   (* amp)))

(definst wrplf
  "Granular -> Lowpass Filter -> Envelope -> Amp"
  [buf 0
   ptr 0
   amp 1
   att 15
   rel 40
   lff 2000
   mix 1
   room 1
   damp 1
   gate 1
   rate 1
   fscale 1
   wsize 0.1
   ebn -1
   olaps 1
   interp 4] 
  (->
   (warp1 1 buf ptr fscale wsize ebn olaps 0.0 interp)   
   (lpf lff)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action FREE))
   (* amp)))

(definst wrphf
  "Granular -> Highpass Filter -> Envelope -> Amp"
  [buf 0
   ptr 0
   amp 1
   att 15
   rel 40
   hff 200
   mix 1
   room 1
   damp 1
   gate 1
   rate 1
   fscale 1
   wsize 0.1
   ebn -1
   olaps 1
   interp 4] 
  (->
   (warp1 1 buf ptr fscale wsize ebn olaps 0.0 interp)   
   (hpf hff)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action FREE))
   (* amp)))

(definst wrprlf
  "Granular -> Lowpass Filter -> Reverb -> Envelope -> Amp"
  [buf 0
   ptr 0
   amp 1
   att 15
   rel 40
   lff 2000
   mix 1
   room 1
   damp 1
   gate 1
   rate 1
   fscale 1
   wsize 0.1
   ebn -1
   olaps 1
   interp 4] 
  (->
   (warp1 1 buf ptr fscale wsize ebn olaps 0.0 interp)
   (lpf lff)
   (free-verb mix room damp)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action FREE))
   (* amp)))

(definst wrprlfo
  "Granular -> Lowpass Filter -> Reverb -> Envelope -> Amp"
  [buf 0
   ptr 0
   amp 1
   att 15
   rel 40
   lff 2000
   mix 1
   room 1
   damp 1
   gate 1
   rate 1
   fscale 1
   wsize 0.1
   ebn -1
   olaps 1
   interp 4] 
  (->
   (warp1 1 buf (+ 0.4 (* 0.1 (sin-osc:kr ptr))) fscale wsize ebn olaps 0.0 interp)
   (lpf lff)
   (free-verb mix room damp)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action FREE))
   (* amp)))

(definst tri
  "Triangle Oscillator -> Reverb -> Envelope -> Amp"
  [buf 0
   amp 1
   att 15
   rel 40
   mix 1
   room 1
   damp 1
   gate 1
   freq 440] 
  (->
   (lf-tri freq)
   (free-verb mix room damp)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action FREE))
   (* amp)))

(definst pgrain
  "Granular -> Lowpass Filter -> Highpass Filter -> Reverb -> Envelope"
  [buf 0
   dur 1
   cpos 0.0
   rate 1
   lff 2000
   hff 200
   amp 1
   att 15
   rel 40
   t_trig 0
   gate 1]
  (->
   (t-grains 2 (impulse:ar t_trig) buf rate (* 0.1 (sin-osc:kr cpos)) dur 0 amp 1)
   (lpf lff)
   (hpf hff)
   (free-verb 0.6 1 1)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))))

(definst pbuf
  "Looper -> Amp -> Lowpass Filter -> Highpass Filter -> Reverb -> Envelope"
  [buf 0
   spos 0.0
   rate 1
   lff 2000
   hff 200
   amp 1
   att 15
   rel 40
   gate 1
   loop 1]
  (->
   (play-buf 2 buf rate 1 spos loop 2)
   (* amp)
   (lpf lff)
   (hpf hff)
   (free-verb 0.5 1 1)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))))

(definst rsin
  "Sine Oscillator -> Amp -> Lowpass Filter -> Highpass Filter -> Envelope -> Reverb"
  [dens 100
   rmul 1000
   lff 2000
   hff 200
   amp 1
   att 15
   rel 40
   rmix 0.5
   gate 1]
  (->
   (sin-osc (* rmul (lf-noise1:kr dens)))
   (* amp)
   (lpf lff)
   (hpf hff)
   (* (env-gen (asr :attack att :curve 1 :release rel) :gate gate :action 2))
   (free-verb rmix 1 1)))
