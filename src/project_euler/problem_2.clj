;; Project Euler 2
;; Find the sum of all the even valued terms in the Fibonacci sequence < 4mil

(def fibs
     (lazy-cat [0 1]
               (map + fibs (rest fibs))))

(defn proj-eul-2 []
  (reduce + (filter #(zero? (mod % 2))
                    (take-while #(< % 4000000) fibs))))
