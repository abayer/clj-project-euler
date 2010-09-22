;; Project Euler 46
;; First odd composite number that can't be written as the sum of a prime and twice a square

(use '[clojure.contrib.lazy-seqs :only (primes)])

(def twice-squares (map #(* 2 % %) (iterate inc 1)))

(def odd-composites (remove prime? (iterate #(+ 2 %) 3)))

(defn prime? [n]
  (every? ;; Returns true if the predicate is true for everything in the collection
   #(pos? (rem n %)) ;; Returns true if the remainder of % into n is > 0
   (range 2 (inc (Math/sqrt n))))) ;; Range of 2 'til 1 more than the square root of n

;; For each twice-square less than the number, check to see if (oc - that twice-square) is prime
;; If any are, it's summable, return false. Otherwise, return true.
(defn not-summable [oc]
  (not-any?
   #(prime? (- oc %))
   (take-while
    #(< % oc)
    twice-squares)))

(defn proj-eul-46 []
  (first
   (filter
    not-summable
    odd-composites)))
