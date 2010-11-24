#!/bin/bash
# Push new version to clojars.org

set -x

lein jar
lein pom
scp pom.xml digest-*.jar clojars@clojars.org:
