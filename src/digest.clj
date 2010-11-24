(ns digest
  #^{ :author "Miki Tebeka <miki.tebeka@gmail.com>"
      :doc "Message digest algorithms for Clojure"}
  (:use [clojure.string :only (split lower-case)])
  (:import (java.security MessageDigest Security)))


(defmulti digest
  "Returns digest for message with given algorithm."
  (fn [algorithm message] (class message)))

(defmethod digest String [algorithm message]
  (digest algorithm [message]))

; Code "borrowed" from http://www.holygoat.co.uk/blog/entry/2009-03-26-1
(defmethod digest :default
  [algorithm messages]
  (let [algo (MessageDigest/getInstance algorithm)]
    (.reset algo)
    (dorun (map #(.update algo (.getBytes %)) messages))
    (.toString (BigInteger. 1 (.digest algo)) 16)))

(defn algorithms []
  "List support digest algorithms."
  (let [providers (into [] (Security/getProviders))
        names (mapcat #(enumeration-seq (.keys %)) providers)
        digest-names (filter #(re-find #"MessageDigest\.[A-Z0-9-]+$" %) names)]
    (set (map #(last (split % #"\.")) digest-names))))

(defn- create-fns []
  "Create utility function for each digest algorithms.
   For example will create an md5 function for MD5 algorithm."
  (dorun (map #(intern 'digest (symbol (lower-case %)) (partial digest %))
              (algorithms))))

; Create utililty functions such as md5, sha-2 ...
(create-fns)
