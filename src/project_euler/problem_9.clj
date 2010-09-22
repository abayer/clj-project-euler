;; Project Euler 9
;; There exists only one Pythagorean triplet (a<b<c, where a^2 + b^2 = c^2) where a+b+c = 1000 - find the product a*b*c

(defn is-pyth-triplet? [a b c]
  (if (= (+ (* a a) (* b b)) (* c c))
    true
    false))

;; Somehow, get a lazy seq of all a/b/c where a+b+c=1000

;; go through that lazy seq until one is a pyth triplet
(defn proj-eul-9 [target-num]
  (for [a (range 1 target-num)
        b (range a target-num)
        c [(max 0 (- target-num a b))]
        :when (is-pyth-triplet? a b c)]
    (* a b c)))
