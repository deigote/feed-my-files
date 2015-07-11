xmlDeclaration()

feed(xmlns: "http://www.w3.org/2005/Atom") {
	title 'Seedbox'
	newLine()
	subtitle 'Lastest downloads'
	newLine()
	link(href: "https://sb.deigote.com/feed/", rel: "self")
	newLine()
	id 'https://sb.deigote.com/feed'
	newLine()
	updated "$lastBuildDate"
	newLine()
	author {
		name 'Seedbox'
		newLine()
		email 'seedbox@deigote.com'
		newLine()
	}
	entries.each { feedEntry ->
		entry {
			title "<![CDATA['${feedEntry.name}']]>"
			newLine()
			link(href: feedEntry.link, rel: "self")
			newLine()
			id "${feedEntry.link}"
			newLine()
			updated "${feedEntry.date}"
			content(type:"html") {
				yieldUnescaped """<![CDATA[
Directorio: <a href="${feedEntry.dirLink}">${feedEntry.dirLink}</a><br />
Fichero: <a href="${feedEntry.link}">${feedEntry.link}</a>
]]>"""
			}
		}
	}
}
