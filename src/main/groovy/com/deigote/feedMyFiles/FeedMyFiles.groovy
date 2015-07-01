package com.deigote.feedMyFiles

import com.deigote.feedMyFiles.feed.RssBuilder
import com.deigote.feedMyFiles.file.FileEntry
import com.deigote.feedMyFiles.file.FilesCollector

import java.nio.file.FileSystems
import java.nio.file.Path

class FeedMyFiles {

	public static void main(String[] args) {
		buildFeeds(getFilePath(new File(args[0])))
	}

	private static getFilePath(File path) {
		FileSystems.getDefault().getPath(path.parentFile.path, path.name)
	}

	private static buildFeeds(Path rootPath) {
		List<FileEntry> files = FilesCollector.collectFromPath(rootPath).sortedByLastModifiedFiles
		println RssBuilder.instance.buildFeed(files)
	}

}
