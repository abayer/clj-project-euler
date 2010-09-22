;; Project Euler 29
;; How many distinct terms are there in the sequence a^b where 2<=a<=100, 2<=b<=100?

;; This seems weirdly easy.

(defn power-of [a b]
  (loop [total 1
         cnt 1]
    (if (> cnt b)
      total
      (recur (* total a) (inc cnt)))))

(defn proj-eul-29 [max-num]
  (count
   (distinct
    (for [a (range 2 (inc max-num))
          b (range 2 (inc max-num))]
      (power-of a b)))))
