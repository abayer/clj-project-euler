(defn make-vec-comp-select
  ([selector] (make-vec-comp-select > selector))
  ([comp selector]
     (fn [v1 v2]
       (if (comp (selector v1) (selector v2)) v1 v2))))

(defn make-nth [n]
  (fn [x] (nth x n)))

;; for gcd, expt, abs, sqrt
(use 'clojure.contrib.math)
(use '[clojure.contrib.lazy-seqs :only (primes)])

(defn prime-factors [n]
  (loop [x n prs primes facs nil]
    (cond
     (= 1 x) facs
     (zero? (rem x (first prs))) (recur (/ x (first prs)) prs (conj facs (first prs)))
     :else (recur x (rest prs) facs))))


(defn digits [n]
  (count
   (take-while
    #(> % 0)
    (iterate
     #(quot % 10) n))))

(defn seq-to-int [s]
  (Integer. (apply str s)))

(defn reverse-num [n]
  (reverse (str n)))

(defn add-if-not-in-set [dset x]
  (if (dset x)
    dset
    (conj dset x)))

(defn num-digits [n]
  (if (zero? n)
    '(0)
    (loop [x n v []]
      (if (zero? x)
        v
        (recur (quot x 10) (cons (rem x 10) v))))))

(defn uniq-num-digits [n]
  (if (zero? n)
    #{0}
    (loop [x n dset #{}]
      (if (zero? x)
        dset
        (recur (quot x 10) (add-if-not-in-set dset (rem x 10)))))))

(defn fact [n]
  (reduce * (range 1 (+ 1 n))))

(defn fact2 [x]
  (loop [n x f 1]
    (cond
     (= n 1) f
     (= n 0) 0
     :else (recur (dec n) (* f n)))))