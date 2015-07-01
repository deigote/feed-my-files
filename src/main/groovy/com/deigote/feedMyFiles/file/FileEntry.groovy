package com.deigote.feedMyFiles.file

import java.nio.file.Path
import java.nio.file.attribute.BasicFileAttributes
import java.time.Instant

class FileEntry {

	final Path path
	final Instant lastModified

	FileEntry(Path path, BasicFileAttributes attrs) {
		this.path = path
		this.lastModified = attrs.lastModifiedTime().toInstant()
	}

	private FileEntry(Map args) {
		throw new UnsupportedOperationException()
	}

}
