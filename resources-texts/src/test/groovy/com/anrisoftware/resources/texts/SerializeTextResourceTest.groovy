package com.anrisoftware.resources.texts

import org.junit.Test

import com.anrisoftware.resources.api.TextResourceFactory
import com.google.common.base.Charsets

class SerializeTextResourceTest extends TextResourceTest {

	@Test
	void "serialize text resource"() {
		TextResourceFactory factory = injector.getInstance TextResourceFactory
		def name = "Text Resource A"
		def locale = Locale.ENGLISH
		def url = new URL("file://")
		def charset = Charsets.UTF_8

		def resource = factory.create name, locale, url, charset
		def resourceB = reserialize resource

		assert resourceB.name == name
		assert resourceB.locale == locale
		assert resourceB.url == url
		assert resourceB.charset == charset
	}
}
