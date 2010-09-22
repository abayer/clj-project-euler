;; Project Euler 30
;; Find the sum of all numbers that can be written as the sum of the fifth powers of their digits

(defn fifth-power [x]
  (* x x x x x))

(def pre-calc-pents
     (vec (map #(fifth-power %) (range 0 10))))

(defn digs-1s-up [x]
  (loop [n x result nil]
    (if (<= n 0)
      result
      (recur (int (/ n 10)) (cons (rem n 10) result))))) 

(defn fifth-pow-of-digits-sum [orig]
  (reduce + (map #(nth pre-calc-pents %) (digs-1s-up orig))))

(defn proj-eul-30 []
  (reduce + (filter #(= % (fifth-pow-of-digits-sum %)) (range 2 1000000))))
