(ns digest-test
  (:require [clojure.string :refer [lower-case includes?]]
            [clojure.test :refer :all]
            [digest :refer :all :reload-all true])
  (:import java.io.File))

(deftest md5-test
  (is (= (digest "md5" "foo") "acbd18db4cc2f85cedef654fccc4a4d8")))

(deftest sha-256-test
  (is (= (sha-256 "foo") 
         "2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae")))

(deftest algorithms-test
  (let [names (algorithms)]
    (is not (empty? names))
    (is (names "SHA-1"))))

(deftest utils-test
  (for [name (algorithms)]
    (dorun (is (ns-resolve *ns* (symbol (lower-case name)))))))

(deftest function-metadata-test
  (is (includes? (:doc (meta #'sha-256))
                 "SHA-256"))
  (is (= '([message])
         (:arglists (meta #'md5)))))

(def ^:dynamic *logo-md5* "38cf20fa3c9dc72be56965eb1c311dfa")
(def ^:dynamic *logo-sha256* 
  "42c2af2a0509832f39d0cef3ecd1612b7857c55abbe2170470eabb2a0318701c")

(deftest file-test
  (let [f (File. "test/clojure.png")]
    (is (= (md5 f) *logo-md5*))
    (is (= (sha-256 f) *logo-sha256*))))

; Just making sure that we don't explode on nil
(deftest nil-test
  (md5 nil))

(deftest length-test
  (is (= (sha (File. "test/length.txt")) 
        "007b65165b253172d054189e8e3175f3bcb9e28e")))
