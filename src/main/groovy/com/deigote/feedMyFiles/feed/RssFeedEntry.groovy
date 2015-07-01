package com.deigote.feedMyFiles.feed

import java.time.Instant

class RssFeedEntry {
	final String name, link, dirLink
	final Instant rssFullDate

	RssFeedEntry(String name, String link, String dirLink, rssFullDate) {
		this.name = name
		this.link = link
		this.dirLink = dirLink
		this.rssFullDate = rssFullDate
	}

	private RssFeedEntry(Map attrs) {
		throw new UnsupportedOperationException()
	}
}
