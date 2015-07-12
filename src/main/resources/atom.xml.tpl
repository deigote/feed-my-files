xmlDeclaration()

feed(xmlns: "http://www.w3.org/2005/Atom") {
	title "${title}"
	newLine()
	subtitle "${description}"
	newLine()
	link(href: "${link}", rel: "self")
	newLine()
	id 'https://sb.deigote.com/feed'
	newLine()
	updated "$lastBuildDate"
	newLine()
	author {
		name 'Feed My Files'
		newLine()
		email 'diego+feedMyFiles@toharia.com'
		newLine()
	}
	entries.each { feedEntry ->
		entry {
			title "<![CDATA[${feedEntry.name}]]>"
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
