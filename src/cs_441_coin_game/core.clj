(ns cs-441-coin-game.core)

(require '[clojure.string :as cstr])

(def sum1 0)
(def sum2 0)

(defn readFile [fileName]
	(with-open [rdr (clojure.java.io/reader (str "/Users/jeet/Workspace/Clojure_projects/cs-441-coin-game/resources/" fileName ".txt"))](clojure.string/join "\n" (line-seq rdr)))
)

(defn convertStringToList [numbers]
	(drop 1 (map #(Integer/parseInt %)(cstr/split numbers #" ")))
)

(defn process [numbers evenSum oddSum]
	(println numbers evenSum oddSum)
	(if (even? (count numbers))
		(if (> evenSum oddSum)
			(def sum1 (+ sum1 (first (reverse numbers))))
			(def sum1 (+ sum1 (first numbers)))
		)
		(if (> (first numbers) (first (reverse numbers)))
			(def sum1 (+ sum1 (first numbers)))
			(def sum1 (+ sum1 (first (reverse numbers))))
		)
	)
)

(defn -main [& args]
	(def numbersStringList (readFile 10))
	(def numbers (convertStringToList numbersStringList))
	(def evenSum (reduce + (vec (take-nth 2 (rest numbers)))))
	(def oddSum (reduce + (vec (take-nth 2 (vec numbers)))))
	(process numbers evenSum oddSum)
	(println sum1)
)