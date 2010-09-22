;; Project Euler 45 again!
;; pentagonal formula: X = n*(3n-1)/2
;; 2X = n * (3n -1)
;; 2X = 3n^2 - n
;; 0 = 3n^2 - n - 2X
;; a=3, b=-1, c=-2X
;; n = (-(-1) + (sqrt ((-1^2) - (4*3*-2X))))/6
;; n = (1 + (sqrt (1 + 24X)))/6

(defn pentagon-num? [t]
  (let [n (/ (+ (Math/sqrt (+ (* 24 t) 1)) 1) 6)]
    (== n (int n))))

(defn hexagonize [x]
  (* x (- (* 2 x) 1)))

(defn proj-eul-45 [start-num]
  (loop [testnum start-num]
    (if (pentagon-num? (hexagonize testnum))
      (vector testnum (hexagonize testnum))
      (recur (inc testnum)))))
