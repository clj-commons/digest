all:
	$(error please pick a target)

test:
	lein test

publish:
	lein deploy clojars-https


.PHONY: all test publish
