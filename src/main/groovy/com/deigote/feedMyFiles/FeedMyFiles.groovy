package com.deigote.feedMyFiles

import com.deigote.feedMyFiles.feed.AtomBuilder
import com.deigote.feedMyFiles.feed.FeedBuilder
import com.deigote.feedMyFiles.feed.RssBuilder
import com.deigote.feedMyFiles.file.FileEntry
import com.deigote.feedMyFiles.file.FilesCollector

import java.nio.charset.StandardCharsets
import java.nio.file.FileSystems
import java.nio.file.Path

class FeedMyFiles {

	public static void main(String[] args) {
		mainTask(args[0], args[1], args[2])
	}

	private static void mainTask(String rootPath, String rss2OutputPath, String atomOutputPath) {
		assert rootPath, "No root path provided! " + System.getProperty('usage.message')
		assert [rss2OutputPath, atomOutputPath].any { it }, "No output files provided! Please set at least one. " + System.getProperty('usage.message')
		buildFeeds(
			getFilePath(new File(rootPath)),
			getFileFromPossiblyEmptyPath(rss2OutputPath),
			getFileFromPossiblyEmptyPath(atomOutputPath)
		)
	}

	private static Optional<File> getFileFromPossiblyEmptyPath(String nullablePath) {
		nullablePath ? Optional.of(new File(nullablePath)) : Optional.empty()
	}

	private static getFilePath(File path) {
		FileSystems.getDefault().getPath(path.parentFile.path, path.name)
	}

	private static buildFeeds(Path rootPath, Optional<File> rss2Output, Optional<File> atomOutput) {
		List<FileEntry> files = FilesCollector.collectFromPath(rootPath).sortedByLastModifiedFiles
		[
			(RssBuilder.instance): rss2Output,
			(AtomBuilder.instance): atomOutput
		].findAll { it.value.present }.each { FeedBuilder builder, Optional<File> output ->
			output.get().withWriter { outputWriter -> builder.buildFeed(files, outputWriter) }
		}
	}

}
