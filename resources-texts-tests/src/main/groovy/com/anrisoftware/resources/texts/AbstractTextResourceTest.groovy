package com.anrisoftware.resources.texts

import static com.anrisoftware.globalpom.utils.TestUtils.*

import com.google.inject.Injector

/**
 * Test for functionality of the texts resources.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractTextResourceTest extends AbstractTextResourceTestUtils {

	void "load plain text with defined locale"() {
		def baseName = "TextsWithDefaultCharset"
		def classLoader = getClass().classLoader
		def texts = factory.create baseName, classLoader

		Locale locale = Locale.GERMAN
		def text = texts.getResource "hello", locale
		assertStringContent text.text, "Hallo Welt - German"
		assert text.locale == locale
		assert text.URL.toString() =~ "com/anrisoftware/resources/texts/texts/de/hello.txt"

		locale = new Locale("ru")
		text = texts.getResource "hello", locale
		assertStringContent text.text, "привет мир - Russian"
		assert text.locale == locale
		assert text.URL.toString() =~ "com/anrisoftware/resources/texts/texts/ru/hello.txt"

		locale = Locale.ENGLISH
		text = texts.getResource "hello", Locale.ENGLISH
		assertStringContent text.text, "Hello World - English Default"
		assert text.locale.toString() == ""
		assert text.URL.toString() =~ "com/anrisoftware/resources/texts/texts/hello.txt"
	}
}
