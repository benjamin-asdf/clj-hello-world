(ns hello)

(defn main [& _] (println "hello"))

(comment
  ;; string, bool, numbers are atomic values
  ;; eval to themself
  "fo"

  ;; a list () - first thing is evaled as function, rest as args
  (str "hello " "world")

  (+ 1 1 2 3)


  ;; first thing in this list is a function
  ((fn [name] (str "hello "  name)) "foo")

  ;; def is special, make a var that I access later
  (def foo 'barr)

  ;; defn just combinees def and fn
  (def my-function (fn [] (random-uuid)))
  ;; same
  (defn my-function [] (random-uuid))

  ;; multi arity is defined inline in clojure
  (defn say-hi
    ([] (say-hi "world"))
    ([name] (str "hello " name)))

  (say-hi)
  (say-hi "Georg")

  (defn say-foo [] 'foo)

  (say-foo)
  foo

  (empty? ())
  (list? ())


  (comment
    ;; do you know in python this thing wont start dancing on you?
    (def my-thing [10 10])
    (say-hi 10)
    my-thing)


  (comment
    (say-hi "fo")
    (say-hi "")

    (keyword? :fa)

    (->>
     (clojure.reflect/reflect java.util.Map)
     :members
     (filter
      (comp :public :flags)
      ;; (fn [m] (:public (:flags m)))
      ))

    (contains? #{1 2 3} 1)

    (#{1 2 3} 3)
    3

    (:fo #{1 2 :fo})
    :fo

    {:fo 10}

    (eval (conj (read-string "(1 2 3)") '+))
    6

    ;; symbols and keywords
    ;; keywords are like enums in other langs
    ;; but they have a name (and namespace), instead of having an underlying number

    (identical? :fa :fa)
    true

    (type 'foo) clojure.lang.Symbol
    (identical? 'foo 'foo)

    (namespace ::fa)
    (name ::fa)
    (identical? :hello/fa ::fa)

    ;; keywords are super fast as keys in maps because
    ;; their equality operator is identical

    (def my-maps [{:fo 10 :fa 11 "foo" 12}
                  {:fo 11 :fa 11 "foo" 12}])

    (def my-map (first my-maps))

    ;; map access
    (get  my-map :fo)
    10

    (my-map :fo)
    10

    ;; special for keywords, they look themself up
    (:fo my-map)
    10

    (for [m my-maps]
      (get m :fo))

    (for [m my-maps]
      (:fo m))

    ;; typical functional toolkit `map` `filter` `reduce`
    ;; which is becomming mainstream in other langs more and more

    (map (fn [arg] (:fo arg)) my-maps)

    ;; equiv to above, just a lambda shorthand
    (map #(:fo %) my-maps)

    ;; endgame
    (map :fo my-maps)

    ;; immutability
    ;; makes everything better, for real
    ;; as good as java datastructures for reading,
    ;; only 2-4 times slower for writing
    ;; if you need the speed you use `transients`
    ;; (a lot of core library functions like `reduce-kv` do that already for you)

    ;; nobody can ever mess with `fo`
    ;; just like nobody can ever mess with the number `42`
    (let [fo [1 2]
          fo2 (drop 1 fo)
          fo3 (conj fo 10)]
      [fo fo2 fo3])


    ;; state in clojure
    ;; there is a handful of dedicated constructs for state
    ;; 95% of the time you just see `atom` together with `swap!`
    ;; these replace all the setters and getters you would have

    (def my-state (atom 10))

    (swap! my-state inc)
    (reset! my-state 0)


    ;; https://github.com/functional-koans/clojure-koans
    ;; https://github.com/seancorfield/usermanager-example
    ))
