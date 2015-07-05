package com.deigote.feedMyFiles.feed

import java.time.Instant

class FeedEntry {
	final String name, link, dirLink
	final Instant date

	FeedEntry(String name, String link, String dirLink, date) {
		this.name = name
		this.link = link
		this.dirLink = dirLink
		this.date = date
	}

	private FeedEntry(Map attrs) {
		throw new UnsupportedOperationException()
	}
}
