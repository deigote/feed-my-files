package com.deigote.feedMyFiles

import com.deigote.feedMyFiles.feed.FeedAttrs
import groovy.transform.ToString
import groovy.transform.builder.Builder

import java.nio.file.FileSystems
import java.nio.file.Path

@Builder
@ToString(includePackage = false, includeNames = true)
class Config {

	String usageMessage,
		rootDirectoryPath,
		rss2OutputPath,
		atomOutputPath,
		urlPrefix,
		filePrefixToIgnoreInUrl
	FeedAttrs feedAttrs
	Collection<String> fileExtensions

	Optional<File> getRss2Output() {
		getFileFromPossiblyEmptyPath(rss2OutputPath)
	}

	Optional<File> getAtomOutput() {
		getFileFromPossiblyEmptyPath(atomOutputPath)
	}

	Path getRootPath() {
		new File(rootDirectoryPath).with { rootFile ->
			FileSystems.getDefault().getPath(rootFile.parentFile.path, rootFile.name)
		}
	}

	private static Optional<File> getFileFromPossiblyEmptyPath(String nullablePath) {
		nullablePath ? Optional.of(new File(nullablePath)) : Optional.empty()
	}

	Config validateOrFail() {
		assert rootPath, "No root path provided! " + usageMessage
		assert [rss2OutputPath, atomOutputPath].any { it }, "No output files provided! Please set at least one. " + usageMessage
		assert !filePrefixToIgnoreInUrl || urlPrefix , "Parameter filePrefixToIgnoreInUrl can only be used in combination with filePrefixToIgnoreInUrl. " + usageMessage
	}
}
