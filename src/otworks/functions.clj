(ns otworks.functions
  (:require [overtone.core :refer [load-sample]]))

;; get-samples - Load all samples in a folder

(defn get-samples [folder-path filenames]
  (doseq [filename filenames]
    (intern *ns* (symbol filename) (load-sample (str folder-path filename ".wav")))))

;; Example: (get-samples "~/Producing/july3rd-2016/samples/" ["1" "2" "3"])
