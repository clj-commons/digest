all:
	$(error please pick a target)

test:
	lein test

publish:
	lein deploy clojars-https

github:
	hg bookmark -r default master
	hg push github

.PHONY: all test publish
