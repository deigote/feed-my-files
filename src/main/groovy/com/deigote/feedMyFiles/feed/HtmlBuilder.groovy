package com.deigote.feedMyFiles.feed

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Singleton
class HtmlBuilder extends FeedBuilder {

	private static DateTimeFormatter dateFormatter =
		DateTimeFormatter
			.ofPattern("yyyy-MM-dd HH:mm:ss")
			.withZone(ZoneId.systemDefault())

	@Override
	protected String getTemplateName() {
		'html.tpl'
	}

	@Override
	protected String formatDate(Instant instant) {
		dateFormatter.format(instant)
	}
}
