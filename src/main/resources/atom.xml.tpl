xmlDeclaration()

feed(xmlns: "http://www.w3.org/2005/Atom") {
	title 'Seedbox'
	subtitle 'Lastest downloads'
	link(href: "https://sb.deigote.com/feed/", rel: "self")
	id 'https://sb.deigote.com/feed'
	updated "$lastBuildDate"
	author {
		name 'Seedbox'
		email 'seedbox@deigote.com'
	}
	entries.each { feedEntry ->
		entry {
			title "<![CDATA['${feedEntry.name}']]>"
			newLine()
			link feedEntry.link
			newLine()
			id "${feedEntry.link}"
			newLine()
			updated "${feedEntry.date}"
			newLine()
			content(type:"html") {
				yieldUnescaped """<![CDATA[
Directorio: <a href="${feedEntrydirLink}">${feedEntry.dirLink}</a><br />
Fichero: <a href="${feedEntry.link}">${feedEntry.link}</a>
]]>"""
			}
		}
	}
}
