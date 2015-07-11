package com.deigote.feedMyFiles.feed

import com.deigote.feedMyFiles.file.FileEntry
import groovy.transform.ToString

import java.nio.file.Path
import java.time.Instant

import static java.nio.charset.StandardCharsets.UTF_8

@ToString(includeNames = true, includePackage = false)
class FeedEntry {
	final String name
	final URI link, dirLink
	final Instant date

	static FeedEntry from(
		FileEntry fileEntry, String urlPrefix = null, String filePrefixToIgnoreInUrl = null
	) {
		new FeedEntry(
			fileEntry.path.fileName.toString(),
			prepareFileUri(fileEntry.path, urlPrefix, filePrefixToIgnoreInUrl),
			prepareFileUri(fileEntry.path.parent, urlPrefix, filePrefixToIgnoreInUrl),
			fileEntry.lastModified
		)
	}

	private static URI prepareFileUri(Path path, String urlPrefix, String filePrefixToIgnoreInUrl) {
		urlPrefix ? (urlPrefix.endsWith('/') ? urlPrefix : urlPrefix.concat('/')).concat(
			path.toString().replaceFirst(filePrefixToIgnoreInUrl ?: '', '').tokenize('/').collect {
				URLEncoder.encode(it, UTF_8.toString())
			}.join('/')
		).toURI() : path.toUri()
	}

	private FeedEntry(String name, URI link, URI dirLink, Instant date) {
		this.name = name
		this.link = link
		this.dirLink = dirLink
		this.date = date
	}

	private FeedEntry(Map attrs) {
		throw new UnsupportedOperationException()
	}
}
