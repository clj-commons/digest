# digest

[![Build Status](https://travis-ci.org/tebeka/clj-digest.svg?branch=master)](https://travis-ci.org/tebeka/clj-digest)

`digest` - Message digest library for Clojure. Providing md5, sha-256, ...

There are several digest functions (such as `md5`, `sha-256` ...) in this
namespace. Each can handle the following input types:

* java.lang.String
* byte array
* java.io.File
* java.io.InputStream
* Sequence of byte array

# Usage

    user=> (require 'digest)
    nil
    ; On a string
    user=> (digest/md5 "foo")
    "acbd18db4cc2f85cedef654fccc4a4d8"
    ; On a file
    user=> (use 'clojure.java.io)
    nil
    user=> (digest/sha-256 (as-file "/tmp/hello.txt"))
    "163883d3e0e3b0c028d35b626b98564be8d9d649ed8adb8b929cb8c94c735c59"

# Installation
Add `[digest "1.4.9"]` to your `project.clj`.

# License
Copyright&copy; 2017 Miki Tebeka <miki.tebeka@gmail.com>

Distributed under the Eclipse Public License (same as Clojure).
