(ns digest-test
  (:use [digest] :reload-all)
  (:use [clojure.test]))

(deftest md5-test
  (is (= (digest "md5" "foo") "acbd18db4cc2f85cedef654fccc4a4d8")))


