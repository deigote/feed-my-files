package com.deigote.feedMyFiles.feed

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Singleton
class RssBuilder extends FeedBuilder {

	private static DateTimeFormatter dateFormatter =
		DateTimeFormatter
			.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z")
			.withZone(ZoneId.systemDefault())

	@Override
	protected String getTemplateName() {
		'rss2.xml.tpl'
	}

	@Override
	protected String formatDate(Instant instant) {
		dateFormatter.format(instant)
	}
}
