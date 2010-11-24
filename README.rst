======
digest
======

`digest` - Message digest library for Clojure. Providing md5, sha-256, ...

Usage
=====
::

    user=> (require 'digest)
    nil
    ; On a string
    user=> (digest/md5 "foo")
    "acbd18db4cc2f85cedef654fccc4a4d8"
    ; On a seq
    user=> (use 'clojure.java.io)
    nil
    user=> (digest/sha-256 (line-seq (reader "/tmp/hello.txt")))
    "163883d3e0e3b0c028d35b626b98564be8d9d649ed8adb8b929cb8c94c735c59"

Installation
============
Add `[digest "1.0.0-SNAPSHOT"]` to your `project.clj`

License
=======
Copyright (C) 2010 Miki Tebeka <miki.tebeka@gmail.com>

Distributed under the Eclipse Public License, the same as Clojure.
