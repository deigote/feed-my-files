package com.deigote.feedMyFiles.feed

import java.time.Instant

@Singleton
class AtomBuilder extends FeedBuilder {

	@Override
	protected String getTemplateName() {
		'atom.xml.tpl'
	}

	@Override
	protected String formatDate(Instant instant) {
		instant.toString()
	}
}
