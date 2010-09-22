;; Project Euler 21
;; Find the sum of all amicable numbers under 10000
;; Amicable numbers - d(220) = 284

(defn get-divisor [n divis]
  (if (zero? (rem n divis))
    divis
    0))

(defn get-factor-sum [num]
  (loop [incr 1 factor-sum 0]
    (if (>= incr num)
      [num factor-sum]
      (recur (inc incr) (+ factor-sum (get-divisor num incr))))))

(defn get-factor-sum-tuples [end-num]
  (into (sorted-map) (map #(get-factor-sum %) (range 1 end-num))))

(defn get-amicable-pairs [end-num]
  (let [all-tuples (get-factor-sum-tuples end-num)]
    (filter #(and (not (nil? (get all-tuples (nth % 1))))
                  (= (get all-tuples (nth % 1)) (nth % 0))
                  (not= (nth % 1) (nth % 0)))
            all-tuples)))

(defn sum-amic-pairs [end-num]
  (reduce + (keys (get-amicable-pairs end-num))))

(defn inv-amic [num pair-map]
  (println "X: " num " Y: " (get pair-map num)))
