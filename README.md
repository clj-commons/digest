# clj-commons/digest

[![Clojars Project](https://img.shields.io/clojars/v/org.clj-commons/digest.svg)](https://clojars.org/org.clj-commons/digest) 
[![cljdoc badge](https://cljdoc.org/badge/org.clj-commons/digest)](https://cljdoc.org/d/org.clj-commons/digest)
[![CircleCI Status](https://circleci.com/gh/clj-commons/clj-digest.svg?style=svg)](https://circleci.com/gh/clj-commons/clj-digest)

`clj-commons/digest` - A message digest library for Clojure. Providing md5, sha-256, ...

There are several digest functions (such as `md5`, `sha-256` ...) in this
namespace. Each can handle the following input types:

* `java.lang.String`
* `byte array`
* `java.io.File`
* `java.io.InputStream`
* Sequence of byte array

# Usage

    user=> (require '[clj-commons.digest :as digest])
    nil
    ; On a string
    user=> (digest/md5 "clojure")
    "32c0d97f82a20e67c6d184620f6bd322"
    ; On a file
    user=> (use 'clojure.java.io)
    nil
    user=> (digest/sha-256 (as-file "/tmp/hello.txt"))
    "163883d3e0e3b0c028d35b626b98564be8d9d649ed8adb8b929cb8c94c735c59"

# Deprecation

The single-segment `digest` namespace is deprecated since `1.4.10+`. Use
`clj-commons.digest` instead.

# Installation
Add `[org.clojars/digest "1.4.10"]` to your `project.clj`.

# License
Copyright&copy; 2017 Miki Tebeka <miki.tebeka@gmail.com>

Distributed under the Eclipse Public License (same as Clojure).

Snail image in `tests` is public domain by Miki Tebeka
