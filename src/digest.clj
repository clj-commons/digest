(ns digest
  #^{ :author "Miki Tebeka <miki.tebeka@gmail.com>"
      :doc "Message digest algorithms for Clojure"}
  (:use [clojure.string :only (split lower-case)])
  (:import java.util.Arrays
           (java.security MessageDigest Security)
           (java.io FileInputStream File InputStream)))

; Default buffer size for reading
(def ^:dynamic *buffer-size* 1024)

(defn- read-some 
  "Read some data from reader. Return [data size] if there's more to read,
  otherwise nil."
  [reader]
  (let [buffer (make-array Byte/TYPE *buffer-size*)
        size (.read reader buffer)]
    (when (> size 0)
      (if (= size *buffer-size*) buffer (Arrays/copyOf buffer size)))))

(defn- byte-seq
  "Return a sequence of [data size] from reader."
  [reader]
  (take-while (complement nil?) (repeatedly (partial read-some reader))))

(defn- signature
  "Get signature (string) of digest."
  [algorithm]
  (let [size (* 2 (.getDigestLength algorithm))
        sig (.toString (BigInteger. 1 (.digest algorithm)) 16)
        padding (apply str (repeat (- size (count sig)) "0"))]
    (str padding sig)))

(defprotocol Digestible
  (-digest [message algorithm]))

(extend-protocol Digestible
  (class (make-array Byte/TYPE 0))
  (-digest  [message algorithm]
    (-digest [message] algorithm))
  
  java.util.Collection
  ;; Code "borrowed" from 
  ;; * http://www.holygoat.co.uk/blog/entry/2009-03-26-1
  ;; * http://www.rgagnon.com/javadetails/java-0416.html 
  (-digest [message algorithm]
    (let [algo (MessageDigest/getInstance algorithm)]
      (.reset algo)
      (doseq [chunk message] (.update algo chunk))
      (signature algo)))

  String
  (-digest [message algorithm]
    (-digest [(.getBytes message)] algorithm))
  
  

  InputStream
  (-digest [reader algorithm]
    (-digest (byte-seq reader) algorithm))
  
  File
  (-digest [file algorithm]
    (-digest (FileInputStream. file) algorithm))

  nil
  (-digest [message algorithm]
    nil))

(defn digest
  "Returns digest for message with given algorithm."
  [algorithm message]
  (-digest message algorithm))

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
