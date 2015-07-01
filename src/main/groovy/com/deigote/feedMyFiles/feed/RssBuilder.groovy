package com.deigote.feedMyFiles.feed

import com.deigote.feedMyFiles.file.FileEntry
import groovy.text.Template
import groovy.text.markup.MarkupTemplateEngine
import groovy.text.markup.TemplateConfiguration

import java.time.Instant

@Singleton
class RssBuilder {

	String buildFeed(List<FileEntry> files) {
		StringWriter writer = new StringWriter()
		new MarkupTemplateEngine(buildTemplateConfiguration()).createTemplate(getTemplateUrl()).make([
			lastBuildDate: Instant.now(),
			entries: files.collect(buildFeedEntry)
		]).writeTo(writer)
		return writer.toString()
	}

	private static Closure<RssFeedEntry> buildFeedEntry = { FileEntry fileEntry ->
		new RssFeedEntry(
			fileEntry.path.fileName.toString(),
			fileEntry.path.toString(),
			fileEntry.path.parent.toString(),
			fileEntry.lastModified
		)
	}

	private TemplateConfiguration buildTemplateConfiguration() {
		TemplateConfiguration configuration = new TemplateConfiguration()
		configuration.setAutoNewLine(true)
		configuration.setAutoIndent(true)
		return configuration
	}

	private URL getTemplateUrl() {
		RssBuilder.getClassLoader().getResource('rss2.xml.tpl')
	}

}
