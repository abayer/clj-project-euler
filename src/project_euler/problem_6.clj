;; Project Euler 6
;; Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
;; i.e., (1 + 2 + ... + 100) ^ 2 - (1^2 + 2^2 + ... + 100^2)

(defn sum-of-squares [max-num]
  (reduce + (map #(* % %) (range 1 (+ 1 max-num)))))

(defn square-of-sum [max-num]
  (let [sum-num (reduce + (range 1 (+ 1 max-num)))]
    (* sum-num sum-num)))

(defn proj-eul-6 [max-num]
  (- (square-of-sum max-num) (sum-of-squares max-num)))
