;; Project Euler 12
;; What is the value of the first triangle number to have over 500 divisors?
;; (triangle number X: (1+2+..+X)

(defn divis-1-0 [n divis]
  (if (zero? (rem n divis))
    1
    0))

(defn get-factor-cnt [num]
  (loop [incr 1 factors 0]
    (if (> incr num)
      factors
      (recur (inc incr) (+ factors (divis-1-0 num incr))))))

(defn get-factor-cnt-faster [num]
  (* 2
     (count
      (filter #(zero? (rem num %))
              (range 1 (Math/sqrt num))))))

(defn triangle-num [x]
  (loop [counter 1 total 0]
    (if (> counter x)
      total
      (recur (inc counter) (+ total counter)))))

(defn proj-eul-12 []
  (loop [incr 1]
    (let [tri (triangle-num incr)]
      (if (> (get-factor-cnt-faster tri) 500)
        [incr tri]
        (recur (inc incr))))))
