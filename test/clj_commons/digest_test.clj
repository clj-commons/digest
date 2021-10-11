(ns clj-commons.digest-test
  (:require [clj-commons.digest :as d]
            [clojure.string :refer [lower-case includes?]]
            [clojure.test :refer [deftest is]])
  (:import java.io.File))

(deftest md5-test
  (is (= (d/digest "md5" "clojure") "32c0d97f82a20e67c6d184620f6bd322")))

(deftest sha-256-test
  (is (= (d/sha-256 "clojure")
         "4f3ea34e0a3a6196a18ec24b51c02b41d5f15bd04b4a94aa29e4f6badba0f5b0")))

(deftest algorithms-test
  (let [names (d/algorithms)]
    (is (seq names))
    (is (names "SHA-1"))))

(deftest utils-test
  (for [name (d/algorithms)]
    (dorun (is (ns-resolve *ns* (symbol (lower-case name)))))))

(deftest function-metadata-test
  (is (includes? (:doc (meta #'d/sha-256))
                 "SHA-256"))
  (is (= '([message])
         (:arglists (meta #'d/md5)))))

(def ^:dynamic *image-md5* "49c39580caf91363e4a4cacfa5564489")
(def ^:dynamic *image-sha1*
  "96f2328cf279b95ddb1dee36df0c91cd7821e741")

(deftest file-test
  (let [f (File. "test/snail.png")]
    (is (= (d/md5 f) *image-md5*))
    (is (= (d/sha-1 f) *image-sha1*))))

; Just making sure that we don't explode on nil
(deftest nil-test
  (d/md5 nil))

(deftest length-test
  (is (= (d/sha (File. "test/quote.txt"))
         "dc93ad3c1e212bf598b9bf700914e832c9bdade5")))
