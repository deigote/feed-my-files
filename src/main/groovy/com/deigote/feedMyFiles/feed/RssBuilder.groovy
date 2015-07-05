package com.deigote.feedMyFiles.feed

import com.deigote.feedMyFiles.file.FileEntry
import groovy.text.markup.MarkupTemplateEngine
import groovy.text.markup.TemplateConfiguration

import java.time.Instant

@Singleton
class RssBuilder extends FeedBuilder {

	@Override
	protected String getTemplateName() {
		'rss2.xml.tpl'
	}
}
