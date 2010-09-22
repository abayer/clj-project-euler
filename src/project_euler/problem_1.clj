;; Project Euler #1
;; Find the sum of all multiples of 3 or 5 under 1000

(defn is-mult-3? [x]
  (if (= (mod x 3) 0)
    true
    false))

(defn is-mult-5? [x]
  (if (= (mod x 5) 0)
    true
    false))

(defn num-or-0-divis [x]
  (if (or (is-mult-3? x)
          (is-mult-5? x))
    x
    0))

(defn proj-eul-1 [start]
  (loop [counter start acc 0]
    (if (= counter 1000)
      acc
      (recur (+ 1 counter) (+ acc (num-or-0-divis counter))))))
