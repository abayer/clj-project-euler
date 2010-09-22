;; Project Euler 40
;; The fractional thing is a red herring. What we're doing is creating a string composed
;; of some big-ass number of sequential integers and then getting the first digit, 10th digit,
;; 100th digit, 1000th digit, 10,000th digit, 100,000th digit, and 1,000,000th digit, and multiplying
;; 'em together.

(def all-nums-concat
     (apply str (range 1 250000)))

(defn pows-of-10 [x]
  (loop [n 1 count 1 result nil]
    (if (= count x)
      (cons n result)
      (recur (* n 10) (inc count) (cons n result)))))

(defn proj-eul-40 [pow-of-10]
  (reduce *
          (map #(Character/getNumericValue (nth all-nums-concat (dec %))) (pows-of-10 pow-of-10))))
