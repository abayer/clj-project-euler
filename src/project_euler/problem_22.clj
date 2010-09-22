;; Project Euler 22

(def proj-eul-22-names
     (re-seq #"[A-Z]+" (slurp "names.txt")))

(def alpha-num-map
     (zipmap
      "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
      (range 1 27)))

(defn alpha-num-val [c]
  (get alpha-num-map c))

(defn word-score [w]
  (reduce + (map #(alpha-num-val %) w)))

(def name-index-map
     (zipmap
      (sort proj-eul-22-names)
      (range 1 (inc (count proj-eul-22-names)))))

(defn name-score [n]
  (* (word-score n)
     (name-index-map n)))

(defn proj-eul-22 []
  (reduce + (map #(name-score %) proj-eul-22-names)))
