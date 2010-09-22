;; Project Euler 14
;; Given sequence:
;; - n even: n/2
;; - n odd: 3n + 1
;; (generating, for 13 as start, 13->40->20->10->5->16->8->4->2->1)
;; Which starting number < 1,000,000 produces the largest chain?

(defn hs-step [n]
  (if (even? n)
    (/ n 2)
    (inc (* n 3))))

(def memo-hs-step (memoize hs-step))

(defn memo-hs-seq [start]
  (loop [result (list start) n start]
    (if (= n 1)
      result
      (let [next-step (memo-hs-step n)]
        (recur (conj result next-step)
               next-step)))))
    
(defn hailstone-sequence [start]
  (loop [n start hs (list start)]
    (let [n-even (/ n 2)
          n-odd (+ (* 3 n) 1)]
      (cond
       (= n 1) hs
       (zero? (rem n 2)) (recur n-even (concat hs [n-even]))
       :else (recur n-odd (concat hs [n-odd]))))))

(defn hs-take3 [start]
  (loop [n start hs (list start)]
    (let [n-next (hs-step n)]
      (cond
       (= n 1) hs
       :else (recur n-next (concat hs [n-next]))))))

(defn hs-take4 [start]
  (loop [n start hs (list start)]
    (let [n-next (memo-hs-step n)]
      (cond
       (= n 1) hs
       :else (recur n-next (concat hs [n-next]))))))

(defn hs-count [start]
  [start (inc (count (take-while (partial < 1) (iterate memo-hs-step start))))])

;; Memoize doesn't actually help at all if I'm already keeping track of numbers
;; seen in sequences.

(defn get-longest
  ([old-len new-len]
     (get-longest old-len new-len old-len new-len))
  ([old-len new-len old-val new-val]
     (if (> new-len old-len)
       new-val
       old-val)))

(defn proj-eul-14 [end-num]
  (loop [counter 3
         longest-length 0
         longest-num 0
         seen-in-seq (lazy-seq [1 2])]
    (cond
     (> counter end-num) [longest-num longest-length]
     (contains? seen-in-seq counter) (recur (inc counter) longest-length longest-num seen-in-seq)
     :else (let [hs (hailstone-sequence counter)]
             (let [hs-len (count hs)]
               (recur (inc counter)
                      (get-longest longest-length hs-len)
                      (get-longest longest-length hs-len longest-num counter)
                      (cons counter seen-in-seq)))))))

(defn proj-eul-14-faster [end-num]
  (loop [counter 3
         longest-length 0
         longest-num 0
         ]
    (cond
     (> counter end-num) [longest-num longest-length]
     :else (let [hs (hs-take4 counter)]
             (let [hs-len (count hs)]
               (recur (inc counter)
                      (get-longest longest-length hs-len)
                      (get-longest longest-length hs-len longest-num counter)))))))

(defn proj-eul-14-fastest [end-num]
  (reduce #(if
               (> (nth %1 1) ;; i.e., the second element in the first arg
                  (nth %2 1) ;; i.e., second in second
                  )
             %1
             %2) ;; i.e., will end up returning whichever tuple has the larger second element
          (map #(hs-count %) ;; gets the hailstone sequence count for a number
               (range 3 end-num)))) ;; The range of 3 to whatever the end num is

;; Yeeps - 5480ms for -faster vs 469 for -fastest with end-num = 5000

(defn vl [n]
  (if (even? n)
    (/ n 2)
    (inc (* 3 n))))
 
(defn dist [n chain]
  (if (< n 2)
    chain
    (if (= n 2)
      (inc chain)
      (recur (vl n) (inc chain)))))
 
(def dist-memo (memoize dist))
 
(defn E14 [limit start-num]
  (loop [n start-num longest-chain 0 wanted-number 1]
    (if (<= limit n)
      (println "Done. longest chain under" limit "is:" longest-chain
               "starting at" wanted-number)
      (let [new-longest-chain (max longest-chain (dist n 0))]
        (recur (inc n)
               new-longest-chain
               (if (= longest-chain new-longest-chain)
                 wanted-number
                 n))))))
