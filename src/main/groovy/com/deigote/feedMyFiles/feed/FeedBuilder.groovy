package com.deigote.feedMyFiles.feed

import com.deigote.feedMyFiles.file.FileEntry
import groovy.text.markup.MarkupTemplateEngine
import groovy.text.markup.TemplateConfiguration

import java.time.Instant

import static java.nio.charset.StandardCharsets.UTF_8

abstract class FeedBuilder {

	final Writer buildFeed(Writer output, List<FileEntry> files, String urlPrefix = null) {
		assert urlPrefix
		new MarkupTemplateEngine(buildTemplateConfiguration()).createTemplate(getTemplateUrl()).make([
			lastBuildDate: Instant.now(),
			entries: buildFeedEntries(files, urlPrefix)
		]).writeTo(output)
	}

	private Collection<FeedEntry> buildFeedEntries(Collection<FileEntry> fileEntries, String urlPrefix) {
		fileEntries.collect { FeedEntry.from(it, urlPrefix) }
	}

	private TemplateConfiguration buildTemplateConfiguration() {
		TemplateConfiguration configuration = new TemplateConfiguration()
		configuration.setAutoNewLine(true)
		configuration.setAutoIndent(true)
		configuration.setDeclarationEncoding(UTF_8.toString())
		return configuration
	}

	private URL getTemplateUrl() {
		FeedBuilder.getClassLoader().getResource(getTemplateName())
	}

	protected abstract String getTemplateName()

}
