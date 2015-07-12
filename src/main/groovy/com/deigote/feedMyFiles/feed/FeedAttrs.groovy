package com.deigote.feedMyFiles.feed

import groovy.transform.ToString
import groovy.transform.TupleConstructor

@ToString(includeNames = true, includePackage = false)
@TupleConstructor(includes = ['title', 'description', 'link'])
class FeedAttrs {
	final String title, description, link

	Map asMap() {
		['title', 'description', 'link'].collectEntries { [(it): this[it] ]}
	}
}
