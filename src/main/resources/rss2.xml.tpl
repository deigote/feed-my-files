rss(version:"2.0") {
	channel {
		title "Feed from files"
		newLine()
		description "Generated with FeedMyFiles"
		newLine()
		link 'https://sb.deigote.com/feed'
		newLine()
		lastBuildDate { yieldUnescaped("${dateFormatter(lastBuildDate)}") }
		newLine()
		language 'es-ES'
		newLine()
		entries.each { entry ->
			item {
				title "<![CDATA['${entry.name}']]>"
				newLine()
				link entry.link
				newLine()
				guid "${entry.link}"
				newLine()
				pubDate { yieldUnescaped("${dateFormatter(entry.date)}") }
			}
		}
	}
}
