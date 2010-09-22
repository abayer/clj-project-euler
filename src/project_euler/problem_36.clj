;; Project Euler 36
;; Find all numbers < 1mil that are palindromes in both base 10 and base 2

;; Gist is to do base 10 to base 2
(defn convert-to-base-seq [n base]
  (reverse ;; the list spat back is in reverse order!
   (map #(rem % base) 
        (take-while
         (complement zero?) ;; This means take until the result is not 0
         (iterate #(int (/ % base)) n))))) ;; this'll just keep dividing n by the base, but the first item will be the original


(defn palin-seq? [a]
  (= a (reverse a)))

(defn proj-eul-36 [max-num]
  (apply +
         (filter #(and (palin-seq? (convert-to-base-seq % 2))
                       (palindrome? %))
                 (range 1 max-num)))) ;; going straight to palindrome? for base 10 is faster, but not a ton

(defn palindromic? [n]
  (= (seq (str n)) (reverse (str n))))
 
(defn binary-palindromic? [n]
  (palindromic? (Integer/toBinaryString n)))
 
(defn euler-36 [max-num]
  (reduce + (filter #(and (palindromic? %)
              (binary-palindromic? %))
                    (range 1 max-num))))

;; So yeah, taking advantage of Integer/toBinaryString helps the speed a *lot*.
;; But hell, I wrote a base converter!
