;; Project Euler 4
;; Largest palindrome made from two 3 digit numbers

(defn palindrome? [n]
  (let [s (seq (str n))]
    (= s (reverse s))))

(defn proj-eul-4 [min-num max-num]
  (let [products
        (for [a (range min-num max-num)
              b (range min-num a)]
          (* a b))]
    (reduce max (filter palindrome? products))))
