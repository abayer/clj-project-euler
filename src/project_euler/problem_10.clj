;; Project Euler 10
;; Calculate the sum of all primes < 2000000

(defn proj-eul-10 [max-num]
  (loop [n 0 prms primes]
    (let [this-prime (first prms)]
      (if (>= this-prime max-num)
        n
        (recur (+ n this-prime) (rest prms))))))
