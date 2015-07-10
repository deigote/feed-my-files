package com.deigote.feedMyFiles.feed

import com.deigote.feedMyFiles.file.FileEntry
import groovy.text.markup.MarkupTemplateEngine
import groovy.text.markup.TemplateConfiguration
import groovy.util.logging.Log4j2

import java.time.Instant

import static java.nio.charset.StandardCharsets.UTF_8

@Log4j2
abstract class FeedBuilder {

	final Writer buildFeed(
		Writer output, List<FileEntry> files, String urlPrefix = null,
		String filePrefixToIgnoreInUrl = null
	) {
		assert urlPrefix
		new MarkupTemplateEngine(buildTemplateConfiguration()).createTemplate(getTemplateUrl()).make([
			lastBuildDate: Instant.now(),
			entries: buildFeedEntries(files, urlPrefix, filePrefixToIgnoreInUrl)
		]).writeTo(output)
	}

	private Collection<FeedEntry> buildFeedEntries(
		Collection<FileEntry> fileEntries, String urlPrefix, String filePrefixToIgnoreInUrl
	) {
		fileEntries.collect { FeedEntry.from(it, urlPrefix, filePrefixToIgnoreInUrl) }.each {
			log.info "Built feed entry ${it}"
		}
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
