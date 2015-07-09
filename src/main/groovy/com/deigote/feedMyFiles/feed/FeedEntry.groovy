package com.deigote.feedMyFiles.feed

import com.deigote.feedMyFiles.file.FileEntry

import java.nio.file.Path
import java.time.Instant

class FeedEntry {
	final String name
	final URI link, dirLink
	final Instant date

	static FeedEntry from(FileEntry fileEntry, String urlPrefix = null) {
		new FeedEntry(
			fileEntry.path.fileName.toString(),
			prepareFileUri(fileEntry.path, urlPrefix),
			prepareFileUri(fileEntry.path.parent, urlPrefix),
			fileEntry.lastModified
		)
	}

	private static URI prepareFileUri(Path path, String urlPrefix) {
		urlPrefix ? new URI(urlPrefix + path.toString()) : path.toUri()
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
