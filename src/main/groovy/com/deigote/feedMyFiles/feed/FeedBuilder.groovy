package com.deigote.feedMyFiles.feed

import com.deigote.feedMyFiles.file.FileEntry
import groovy.text.markup.MarkupTemplateEngine
import groovy.text.markup.TemplateConfiguration

import java.time.Instant

abstract class FeedBuilder {

	final Writer buildFeed(List<FileEntry> files, Writer output) {
		new MarkupTemplateEngine(buildTemplateConfiguration()).createTemplate(getTemplateUrl()).make([
			lastBuildDate: Instant.now(),
			entries: files.collect(buildFeedEntry)
		]).writeTo(output)
	}

	private static Closure<FeedEntry> buildFeedEntry = { FileEntry fileEntry ->
		new FeedEntry(
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
		FeedBuilder.getClassLoader().getResource(getTemplateName())
	}

	protected abstract String getTemplateName()

}
