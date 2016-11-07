(ns otworks.functions
  (:require [overtone.core :refer [load-sample buffer-mix-to-mono]]))

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
