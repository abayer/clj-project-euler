;; Project Euler 34
;; Find the sum of all numbers which are equal to the sum of the factorial of all their digits
;; Max is 7 digits, 'cos 8*9! is 7 digits

(def pre-calc-facts
     (vec (map #(fact %) (range 0 10))))

(defn fact-digits-sum [orig]
  (reduce + (map #(nth pre-calc-facts %) (digs-1s-up orig))))

(defn proj-eul-34 []
  (reduce + (filter #(= % (fact-digits-sum %)) (range 3 250000))))
