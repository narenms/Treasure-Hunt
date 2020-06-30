(ns treasure (:require [clojure.string :as str]))
(use 'clojure.java.io)

(defn read-in-board [zp]
  (str/split-lines (slurp zp)))
"Function to convert input string to 2-d vector."
(def point-nodez-2d (mapv vec (read-in-board "map.txt")))
;; (println point_2d)

"Converting 2d-vector into 2d-array."
(def map1 (to-array-2d point-nodez-2d))

;---------------------------------------------------------------

"Defining no. of rows, and no. of columns."
(def rct1 (alength map1))
;(println rct1)
(def cct1 (alength (aget map1 0)))
;(println rct1)
;(println cct1)

;---------------------------------------------------------------

(defn s-f-mpz-comp [rt ct ma]

  "Iterates inside the 2d-array to move through the map.
  In this algorithm until we reach @, when we discover -
  we change it +. We continue this until we reach a
  dead-end(# on 3 sides), when we come to a dead-end we
  make the node ! and recur back returning false. Once we
  reach the treasure @ we return true and end the recursion."

  ;(prt-th-mpz)
  ;(println)
  (if (and (and (>= rt 0)
                (>= ct 0))
           (and (< rt rct1)
                (< ct cct1)))
    (do
      (if (= (aget ma rt ct) \@)
        true
        (do
          (if (and (not= (aget ma rt ct) \#)
                   (not= (aget ma rt ct) \+))
            (do
              (aset ma rt ct \+)
              (if (= (s-f-mpz-comp rt (inc ct) ma) true)
                true
                (do
                  (if (= (s-f-mpz-comp (inc rt) ct ma) true)
                    true
                    (do
                      (if (= (s-f-mpz-comp rt (dec ct) ma) true)
                        true
                        (do
                          (if (= (s-f-mpz-comp (dec rt) ct ma) true)
                            true
                            (do
                              (aset ma rt ct \!)
                              false

                              )
                            )
                          )
                        )
                      )
                    )
                  )
                )
              )
            false
            )
          )
        )
      )
    false
    )

  )

;---------------------------------------------------------------

(defn chk-mpz-lth [c]
  "This function purpose is to validate the map by
  checking whether the no. of column elements are same."
  ;(println (alength (aget map1 c)))
  (if (< c (- rct1 1))
    (do
      (if (= cct1 (alength (aget map1 c)))
        (do
          (if (= (chk-mpz-lth (inc c)) true)
            true
            false
            )
          )
        false
        )
      )
    true
    )



  )

;---------------------------------------------------------------

(defn prt-th-mpz []
  "This function prints the map element-by-element"
  (doseq [[v row] (map vector map1 (range))]
    (doseq [[v2 column] (map vector v (range))]
      (print (aget map1 row column)))
    (println))
  )

;---------------------------------------------------------------

;(def a 1)
(defn -main []
  "If the map is valid start solving the maze, else prompt the user
  with a invalid/incorrect message"
  (if (= (chk-mpz-lth 0) true)
    (do
      (println "This is my challenge:")
      (println)
      (prt-th-mpz)
      (if (s-f-mpz-comp 0 0 map1)
        (do
          (println)
          (println)
          (println "Woo hoo, I found the treasure :-)")
          (println)
          (prt-th-mpz)
          )
        (do
          (println)
          (println)
          (println "Uh oh, I could not find the treasure :-(")
          (println)
          (prt-th-mpz)
          )
        )
      )
    (do
      (println "Invalid/incomplete map, please input a correct map"))
    )

  )

(-main)
;---------------------------------------------------------------


;(println)
;(println "Final result:")

;
;(if (doseq [[value row] (map vector map1 (range))]
;      (if (= (count value) rct1)
;        true
;        false))
;  (println "okay")
;  (println "wrong"))
