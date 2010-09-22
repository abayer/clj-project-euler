;; Project Euler 42
;; How many words in words.txt are triangle words?
;; Triangle word - num value of letters summed up is a triangle number

(def proj-eul-42-words
     (re-seq #"[A-Z]+" (slurp "/Users/abayer/src/scheme/words.txt")))

;; Triangle formula - Tn = 1/2n * (n + 1)
;; 1/2n^2 + 1/2n - X = 0
;; n = (-.5 +- (sqrt (.5^2 - 4*.5*-X)))/2*.5
;; n = (-.5 +- (sqrt (.25 - 2*-X)))
;; I am just crap at solving quadratic equations.
;; Ok, one more time: X = (n*(n+1))/2
;; 2X = n^2 + n
;; 0 = n^2 + n - 2X
;; so a=1, b=1, c=-2X
;; n = (-b+- (sqrt (b^2 - 4ac)))/(2a)
;; n = (-(1) +- (sqrt (1 + 8X)))/2
;; n = (sqrt(8X+1) - 1) / 2
;; GOT IT
(defn triangle-num? [t]
  (let [n (/ (- (Math/sqrt (+ (* 8 t) 1)) 1) 2)]
    (== n (int n))))

(defn proj-eul-42 []
  (count (filter #(triangle-num? (word-score %)) proj-eul-42-words)))
