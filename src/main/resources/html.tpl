html {
	head {
		title "${title}"
	}
	body {
		h1 "${title}"
		newLine()
		h2 "${description}"
		newLine()
		hr()
		ul {
			li {
				a(href: link) { yieldUnescaped("${link}") }
			}
			li {
				yieldUnescaped("${dateFormatter(lastBuildDate)}")
			}
		}
		newLine()
		hr()
		ul {
			entries.each { entry ->
				li {
					p(entry.name)
					ul {
						newLine()
						li {
							a(href: entry.link) { yieldUnescaped("${entry.link}") }
						}
						newLine()
						li {
							yield(dateFormatter(entry.date))
						}
					}
				}
			}
		}
	}
}
