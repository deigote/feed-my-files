rss(version:"2.0") {
	channel {
		title "${title}"
		newLine()
		description "${description}"
		newLine()
		link "${link}"
		newLine()
		lastBuildDate { yieldUnescaped("${dateFormatter(lastBuildDate)}") }
		newLine()
		language 'es-ES'
		newLine()
		entries.each { entry ->
			item {
				title "<![CDATA[${entry.name}]]>"
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
