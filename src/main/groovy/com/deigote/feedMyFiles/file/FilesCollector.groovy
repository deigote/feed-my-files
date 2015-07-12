package com.deigote.feedMyFiles.file

import groovy.transform.TupleConstructor
import groovy.util.logging.Log4j2

import java.nio.file.FileVisitResult
import java.nio.file.FileVisitor
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.attribute.BasicFileAttributes

@Log4j2
class FilesCollector implements FileVisitor<Path> {

	private final Set<FileEntry> collectedFiles = []
	private final Collection<String> fileExtensionsToInclude

	private FilesCollector(Collection<String> fileExtensionsToInclude) {
		this.fileExtensionsToInclude = fileExtensionsToInclude
		log.info "Collecting files with ".concat(fileExtensionsToInclude ?
			"extensions ${fileExtensionsToInclude.join(', ')}" : "any file extension"
		)
	}

	@Override
	FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		FileVisitResult.CONTINUE
	}

	@Override
	FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		if (!fileExtensionsToInclude || fileExtensionsToInclude.any { fileExtension ->
			file.toString().endsWith('.'.concat(fileExtension))
		}) {
			collectedFiles << new FileEntry(file, attrs)
		}
		FileVisitResult.CONTINUE
	}

	@Override
	FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		log.warn "failed to visit file ${file}", exc
		FileVisitResult.CONTINUE
	}

	@Override
	FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		FileVisitResult.CONTINUE
	}

	List<FileEntry> getSortedByLastModifiedFiles() {
		collectedFiles.sort { it.lastModified }
	}

	static FilesCollector collectFromPath(
		Path rootPath, Collection<String> fileExtensionsToInclude = null
	) {
		new FilesCollector(fileExtensionsToInclude).with { collector ->
			Files.walkFileTree(rootPath, collector)
			collector
		}
	}

}
