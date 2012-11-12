package com.anrisoftware.resources.texts

import static com.anrisoftware.globalpom.utils.TestUtils.*

import org.junit.Before

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

	def textFactory

	void "serialize text resource"() {
		def name = "Text Resource A"
		def locale = Locale.ENGLISH
		def url = Resources.getResource "com/anrisoftware/resources/texts/texts/hello.txt"
		def charset = Charsets.UTF_8

		def resource = textFactory.create name, locale, url, charset
		def resourceB = reserialize resource

		assert resourceB.name == name
		assert resourceB.locale == locale
		assert resourceB.URL == url
		assert resourceB.charset == charset
	}

	@Before
	void createFactories() {
		super.createFactories()
		textFactory = createTextFactory()
	}

	/**
	 * Create text resource factory.
	 *
	 * @since 1.2
	 */
	abstract createTextFactory()
}
