rss(version:"2.0") {
	channel {
		title 'Seedbox lastest downloads'
		link 'https://sb.deigote.com/feed'
		lastBuildDate "$lastBuildDate"
		language 'es-ES'
		entries.each { entry ->
			item {
				title "<![CDATA['${entry.name}']]>"
				newLine()
				link entry.link
				newLine()
				guid "${entry.link}"
				newLine()
				pubDate "${entry.rssFullDate}"
				newLine()
				description """[CDATA[
Directorio: <a href="${entry.dirLink}">${entry.dirLink}</a><br />
Fichero: <a href="${entry.link}">${entry.link}</a>
]]"""
			}
			newLine()
		}
	}
}
