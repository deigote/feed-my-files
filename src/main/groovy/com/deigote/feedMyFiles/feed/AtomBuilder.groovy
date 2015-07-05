package com.deigote.feedMyFiles.feed

@Singleton
class AtomBuilder extends FeedBuilder {

	@Override
	protected String getTemplateName() {
		'atom.xml.tpl'
	}
}
