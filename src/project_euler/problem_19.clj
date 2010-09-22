;; Project Euler 19
;; How many Sundays fell on the first of the month from 1/1/1901 -> 12/31/2000?

(defn leap-year? [y]
  (and (zero? (rem y 4))
       (or (zero? (rem y 400))
           (not (zero? (rem y 100))))))

(def leap-days [31 29 31 30 31 30 31 31 30 31 30 31])

(def non-leap-days [31 28 31 30 31 30 31 31 30 31 30 31])

(def years (range 1900 2001))

(def year-days
     (mapcat
      #(if (leap-year? %) leap-days non-leap-days)
      years))

(defn dows [start days]
  (when
      (not-empty days) ;; when there are more days left to process
    (lazy-cat ;; smush!
     [start] ;; the start number - that's the first day we saw, so here 0 for Monday
     (dows ;; Recur!
      (rem (first (map + [start] days)) 7) ;; Since we know start is the base day of the week, if we add it to the days per month,
      ;; and then get that mod 7, we have the day of the week! 
      (rest days))))) ;; And now handle the rest of the days-of-the-month

(defn proj-eul-19 []
  (count
   (filter #(= 6 %) ;; Get all the Sundays...
           (drop 12 (dows 0 year-days))))) ;; Except the first 12, since that's the year 1900
