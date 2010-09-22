;; Project Euler 27


(defn divisible? [a b]
  (zero? (rem a b)))
 
(defn prime? [n]
  (cond
   (<= n 1) false
   (< n 4) true
   (divisible? n 2) false
   (< n 9) true
   (divisible? n 3) false
   true (not-any? #(or (divisible? n %)
               (divisible? n (+ % 2)))
          (take-while #(<= % (Math/sqrt n))
                  (iterate #(+ % 6) 5)))))
 
(defn is-prime? [n]
  (zero? (count
          (filter #(zero? (rem n %))
                  (range 3 n 2)))))

(defn proj-eul-27-formula [a b n]
  (+ (* n n) (* a n) b))

;; Get both the count and the product, since we want that for the answer
(defn proj-eul-27-primes-for [a b prime-fn]
  (vector
   (count (take-while prime-fn (map #(proj-eul-27-formula a b %) (iterate inc 0))))
   (* a b) a b))

(defn comp-first-in-vec [v1 v2]
  (if (> (first v1) (first v2)) v1 v2))

(defn proj-eul-27 []
  (let [nums (range -999 1000)]
    (reduce
     comp-first-in-vec
     (map
      (fn [a]
        (do
          (println "checking all against " a)
          (reduce comp-first-in-vec (map #(proj-eul-27-primes-for a % prime?) nums))))
      nums))))

;; prime? kicks is-prime?'s ass.
