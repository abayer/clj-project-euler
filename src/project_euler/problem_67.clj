;; And now Project Euler 67!

;; See problem 18 for longest-triangle-route

(def proj-67-triangle (for [s (map #(.split % "\\s") (.split (slurp "triangle.txt") "\n"))](map #(Integer. %) s)))

(def proj-eul-67 (longest-triangle-route proj-67-triangle))
