all:
	$(error please pick a target)

test:
	lein test

publish:
	lein deploy clojars

.PHONY: all test publish
