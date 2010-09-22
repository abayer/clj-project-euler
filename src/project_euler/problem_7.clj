;; Project Euler 7
;; Find the 10001st prime number
(defn is-prime? [n]
  (zero? (count
          (filter #(zero? (rem n %))
                  (range 3 n 2)))))

(defn smallest-prime-factor [n]
  (loop [d 2]
    (cond
     ;; If d is bigger than the square root of n, n is the smallest prime factor
     ;; e.g., d=2, n=3
     (> (* d d) n) n
     ;; If n == d, n is the smallest prime factor
     ;; e.g., d=7, n=7
     (= n d) n
     ;; If d divides evenly into n, d is the smallest prime factor
     ;; e.g., d=2, n=14
     (divides? n d) d
     ;; Else, try again with d = d+1
     :else (recur (+ 1 d)))))

(def primes
     ;; Toss together a lazy seq, starting with 2 and 3
     (lazy-cat '(2 3)
               ;; Filter for primes from numbers that we know aren't divisible by 2 or 5
               (filter #(= % (smallest-prime-factor %))
                       (take-nth 2 (iterate inc 5)))))

;; (nth primes 10000)
;; or...
(defn proj-eul-7 [nth-prime]
  (loop [n 0 prms primes]
    (if (= n nth-prime)
      (first prms)
      (recur (inc n) (rest prms)))))
