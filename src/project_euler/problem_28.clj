;; Project Euler 28
;; Sum of numbers in diagonals in a 1001x1001 spiral

;; 1
;; 3 5 7 9
;; 13 17 21 25
;; 31 37 43 49
;; Ah-ha - top number of each iteration is the square of the next odd number,
;; and the rest of the numbers are declining by one less than that next odd number.

;; Ah, dude, the odd number is the width of the square!

;; So for 1001 - 1001^2 + (1001^2 - 1000) + (1001^2 - 1000 - 1000) + (1001^2 - 1000 - 1000 - 1000)
;; Or 4*1001^2 - 6000 - 4 x^2 - (6 * (x - 1))
;; 5->76
;; 3->24
;; 1->1

(defn corners [x]
  (- (* 4 (* x x)) (* 6 (- x 1))))

(defn proj-eul-28 [grid-num]
  (if (= grid-num 1)
    1
    (+ (corners grid-num) (proj-eul-28 (- grid-num 2)))))
