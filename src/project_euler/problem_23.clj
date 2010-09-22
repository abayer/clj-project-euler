;; Project Euler 23
;; Find the sum of all positive integers which can't be written as the sum of two abundant numbers

(defn is-abundant? [n]
  (> (nth (get-factor-sum n) 1) n))
  
    
(defn get-abundant-numbers [max-num]
  (loop [n 13 abundants [12]]
    (if (> n max-num) ;; all ints > 28123 can be written as abundant numbers, so we don't care about abundant numbers above that
      abundants
      (if (is-abundant? n)
        (recur (inc n) (conj abundants n))
        (recur (inc n) abundants)))))

(defn abundant-sums [max-num]
  (distinct
   (let [abundants (get-abundant-numbers max-num)]
     (for [x (range 0 (count abundants))
           y (range 0 (count abundants))]
       (+ (nth abundants x) (nth abundants y))))))

(defn non-abundant-sums [max-num a-sums]
  (loop [x 1 n-a-s []]
    (if (> x max-num)
      n-a-s
      (if (not-any? #(= % x) a-sums)
        (recur (inc x) (conj n-a-s x))
        (recur (inc x) (conj n-a-s x))))))

(defn not-in-list? [coll val]
  (not-any? #(= val %) coll))

(defn non-abund-sums [max-num]
  (let [a-sums (abundant-sums max-num)]
    (filter #(not-in-list? a-sums %) (range 1 (inc max-num)))))

;; contains? sucks

(defn proj-eul-23 []
  (reduce + (non-abund-sums 28123)))
