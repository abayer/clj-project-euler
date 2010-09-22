;; Project Euler 5
;; What is the smallest positive number that is divisible by 1..20?

(defn divis-by-all [n]
  (loop [x 2]
    (cond
     (> x 20) true
     (> (rem n x) 0) false
     :else (recur (+ x 1)))))
      

(defn proj-eul-5 []
  (loop [x 20]
    (if (divis-by-all x)
      x
      (recur (+ x 20)))))
