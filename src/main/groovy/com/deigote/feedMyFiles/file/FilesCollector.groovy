package com.deigote.feedMyFiles.file

import java.nio.file.FileVisitResult
import java.nio.file.FileVisitor
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.attribute.BasicFileAttributes

class FilesCollector implements FileVisitor<Path> {

	private final Set<FileEntry> collectedFiles = []

	@Override
	FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		FileVisitResult.CONTINUE
	}

	@Override
	FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		collectedFiles << new FileEntry(file, attrs)
		FileVisitResult.CONTINUE
	}

	@Override
	FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		FileVisitResult.CONTINUE
	}

	@Override
	FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		FileVisitResult.CONTINUE
	}

	List<FileEntry> getSortedByLastModifiedFiles() {
		collectedFiles.sort { it.lastModified }
	}

	static FilesCollector collectFromPath(Path rootPath) {
		new FilesCollector().with { collector ->
			Files.walkFileTree(rootPath, collector)
			collector
		}
	}

}
