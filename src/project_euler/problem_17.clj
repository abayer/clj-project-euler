;; Project Euler 16
;; How many letters would be used in writing all numbers 1-1000 as words ("three hundred and forty-two")
;; Ignore spaces and hyphens. "and" shows up after "hundred"

(defn digit-to-word [digit]
  (cond
   (= digit 0) ""
   (= digit 1) "one"
   (= digit 2) "two"
   (= digit 3) "three"
   (= digit 4) "four"
   (= digit 5) "five"
   (= digit 6) "six"
   (= digit 7) "seven"
   (= digit 8) "eight"
   (= digit 9) "nine"
   :else (str digit "oops")))

(defn tens-dig-to-word [ten-dig]
  (cond
   (= ten-dig 0) ""
   (= ten-dig 1) ""
   (= ten-dig 2) "twenty"
   (= ten-dig 3) "thirty"
   (= ten-dig 4) "forty"
   (= ten-dig 5) "fifty"
   (= ten-dig 6) "sixty"
   (= ten-dig 7) "seventy"
   (= ten-dig 8) "eighty"
   (= ten-dig 9) "ninety"
   :else (str ten-dig "oops")))

(defn hundred-to-word [hun-dig]
  (if (= 0 hun-dig)
    ""
    (str (digit-to-word hun-dig) "hundred")))

(defn print-and-if-needed [hun-dig ten-dig one-dig]
  (if (or (and (= ten-dig 0) (= one-dig 0))
          (zero? hun-dig))
    ""
    "and"))

(defn tens-to-word [tens ones]
  (cond
   (and (= tens 0)
        (= ones 0))
        ""
   (= tens 1) (cond
               (= ones 0) "ten"
               (= ones 1) "eleven"
               (= ones 2) "twelve"
               (= ones 3) "thirteen"
               (= ones 4) "fourteen"
               (= ones 5) "fifteen"
               (= ones 6) "sixteen"
               (= ones 7) "seventeen"
               (= ones 8) "eighteen"
               (= ones 9) "nineteen")
   (= ones 0) (tens-dig-to-word tens)
   :else (str (tens-dig-to-word tens) (digit-to-word ones))))


(defn num-to-words [num]
  (if (= num 1000)
    "onethousand"
    (let [padded (map #(Character/digit % 10) (seq (format "%03d" num)))
          hun-dig (first padded)
          ten-dig (second padded)
          one-dig (nth padded 2)]
      (str (hundred-to-word hun-dig)
           (print-and-if-needed hun-dig ten-dig one-dig)
           (tens-to-word ten-dig one-dig)))))

(defn letters-in-str [s]
  (count
   (filter
    #(not (= " " (str %)))
    s)))

(def all-words-for-proj-eul-17 
     (apply str (map num-to-words (range 1 1001))))

(def proj-eul-17
     (letters-in-str all-words-for-proj-eul-17))

(def a0 '("one" "two" "three" "four" "five" "six" "seven" "eight"
             "nine" "ten" "eleven" "twelve" "thirteen" "fourteen" "fifteen"
             "sixteen" "seventeen" "eighteen" "nineteen"))
(def a1 '("twenty" "thirty" "forty" "fifty" "sixty" "seventy"
             "eighty" "ninety"))
 
(defn to_words [n]
  (cond
   (< n 20) (nth a0 (dec n))
   (< n 100) (if (zero? (rem n 10))
               (nth a1 (/ (- n 20) 10))
               (str (nth a1 (/ (- n 20) 10)) (nth a0 (dec (rem n 10)))))
   (< n 1000) (if (zero? (rem n 100))
                (str (to_words (/ n 100)) "hundred")
                (str (to_words (/ n 100)) "hundredand" (to_words (rem n 100))))
   (= n 1000) "onethousand"))
 
(.length (reduce str (map to_words (range 1 1001))))
