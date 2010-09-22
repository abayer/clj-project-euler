;; Project Euler 25
;; What is the first Fibonacci number to be longer than 1000 digits?


(defn proj-eul-25 []
  (ffirst (filter
          #(>= (count (seq (str (second %)))) 1000)
          (map vector (iterate inc 1) fibs))))

;; This spits out answer-1 - my fibs sequence at the time was kinda borked.
