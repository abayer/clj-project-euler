;; Project Euler 52

;; Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.

(use '[clojure.contrib.combinatorics])

;; Ok - how do I tell whether a number contains the same digits as another one?
(defn num-to-digit-seq [n]
  (sort (str n)))

(defn is-mult-same-digits? [n mult]
  (= (num-to-digit-seq n)
     (num-to-digit-seq (* mult n))))

(defn proj-eul-52
  ([] (proj-eul-52 1))
  ([x]
     (if (apply = (map #(is-mult-same-digits? x %) (range 1 7)))
       x
       (recur (inc x)))))
