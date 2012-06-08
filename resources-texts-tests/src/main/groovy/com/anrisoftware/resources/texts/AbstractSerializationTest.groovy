package com.anrisoftware.resources.texts

import java.util.Locale

import com.anrisoftware.resources.api.TextResource
import com.anrisoftware.resources.api.TextResourceFactory
import com.google.common.base.Charsets
import com.google.common.io.Resources
import com.google.inject.Injector

/**
 * Test for serialization of the texts resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractSerializationTest extends AbstractTextResourceTestUtils {

	void "serialize text resource"() {
		TextResourceFactory factory = injector.getInstance TextResourceFactory
		def name = "Text Resource A"
		def locale = Locale.ENGLISH
		def url = Resources.getResource "com/anrisoftware/resources/texts/texts/hello.txt"
		def charset = Charsets.UTF_8

		def resource = factory.create name, locale, url, charset
		TextResource resourceB = reserialize resource

		assert resourceB.name == name
		assert resourceB.locale == locale
		assert resourceB.URL == url
		assert resourceB.charset == charset
	}
}
