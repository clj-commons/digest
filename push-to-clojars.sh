#!/bin/bash
# Push to clojars

set -x
set -e

if [ ! -f project.clj ]; then
    echo "error: can't find project.clj" 1>&2
    exit 1
fi

project=$(grep defproject project.clj | awk '{print $2}')
version=$(egrep -o "[0-9]+\.[0-9]+\.[0-9]+(-SNAPSHOT)?" project.clj  | head -1)
jar=target/$project-${version}.jar

if [ -f $jar ]; then
    rm $jar
fi
lein jar
lein pom
scp pom.xml $jar clojars@clojars.org:
rm pom.xml $jar
