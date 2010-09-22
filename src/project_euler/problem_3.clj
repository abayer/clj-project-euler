;; Project Euler 3
;; Find the largest prime factor of 600851475143

(defn max-div [number divisor]
  (if (zero? (mod number divisor)) (max-div (/ number divisor) divisor) number))

(defn largest-prime-factor [num]
    (loop [p 2 n num]
      (let [remaind (max-div n p)]
        (if (or (= remaind 1)
                (> (square p) num))
          p
          (recur (inc p) remaind) ))))

(defn orig-largest-prime-factor [num]
  (loop [p 2 n num]
    (let [remaind (max-div n p)]
      (if (= remaind 1)
        p
        (recur (inc p) remaind)))))
