package com.deigote.feedMyFiles
import com.deigote.feedMyFiles.feed.AtomBuilder
import com.deigote.feedMyFiles.feed.FeedAttrs
import com.deigote.feedMyFiles.feed.FeedBuilder
import com.deigote.feedMyFiles.feed.RssBuilder
import com.deigote.feedMyFiles.file.FileEntry
import com.deigote.feedMyFiles.file.FilesCollector
import groovy.util.logging.Log4j2

@Singleton
@Log4j2
class FeedMyFiles {

	public static void main(String[] args) {
		FeedMyFiles.instance.buildFeeds(Config.builder()
			.usageMessage(System.getProperty('usage.message'))
			.rootDirectoryPath(args[0])
			.rss2OutputPath(args[1])
			.atomOutputPath(args[2])
			.urlPrefix(args[3])
			.filePrefixToIgnoreInUrl(args[4])
			.feedAttrs(new FeedAttrs(
				args[5] ?: 'Feed My Files', args[6] ?: 'Feed generated with Feed My Files',
				args[7] ?:'https://github.com/deigote/feed-my-files'
			))
			.build()
		)
	}

	private buildFeeds(Config config) {
		log.info "Building feeds with config ${config}"
		List<FileEntry> files = FilesCollector.collectFromPath(config.rootPath).sortedByLastModifiedFiles
		[
			(RssBuilder.instance): config.rss2Output,
			(AtomBuilder.instance): config.atomOutput
		].findAll { it.value.present }.each { FeedBuilder builder, Optional<File> output ->
			output.get().withWriter { outputWriter ->
				builder.buildFeed(
					outputWriter, config.feedAttrs, files, config.urlPrefix,
					config.filePrefixToIgnoreInUrl
				)
			}
		}
	}

}
