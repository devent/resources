package com.anrisoftware.resources.texts

import java.nio.charset.Charset


import org.junit.Before
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.resources.api.TextResource
import com.anrisoftware.resources.api.Texts
import com.anrisoftware.resources.api.TextsFactory
import com.google.common.base.Charsets
import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.name.Names

/**
 * Creates the text resources and runs functionality tests.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TextResourceTest extends TestUtils {

	def modules

	Injector injector

	TextsFactory factory

	@Before
	void before() {
		modules = lazyCreateModules()
		injector = lazyCreateInjector()
		factory = injector.getInstance(TextsFactory)
	}

	def lazyCreateModules() {
		modules == null ?
				[
					resourcesTextsModule,
					characterSetModule,
				].flatten()
				: modules
	}

	def getResourcesTextsModule() {
		new ResourcesTextsModule()
	}

	def getCharacterSetModule() {
		new AbstractModule() {
					@Override
					protected void configure() {
						bind(Charset).annotatedWith(Names.named("texts-default-charset")) toInstance Charsets.UTF_8
					}
				}
	}

	def lazyCreateInjector() {
		injector == null ? Guice.createInjector(modules) : injector
	}

	@Test
	void "load plain text with defined locale"() {
		Texts texts = factory.create "TextsWithDefaultCharset"

		Locale locale = Locale.GERMAN
		TextResource text = texts.textResource "hello", locale
		assertStringContent text.text, "Hallo Welt - German"
		assert text.locale == locale
		assert text.URL.toString() =~ "com/anrisoftware/resources/texts/de/hello.txt"

		locale = new Locale("ru")
		text = texts.textResource "hello", locale
		assertStringContent text.text, "привет мир - Russian"
		assert text.locale == locale
		assert text.URL.toString() =~ "com/anrisoftware/resources/texts/ru/hello.txt"

		locale = Locale.ENGLISH
		text = texts.textResource "hello", Locale.ENGLISH
		assertStringContent text.text, "Hello World - English Default"
		assert text.locale == locale
		assert text.URL.toString() =~ "com/anrisoftware/resources/texts/hello.txt"
	}

	@Test
	void "load plain text default locale"() {
		def defaultLocale = Locale.getDefault()
		TextResource text = resources.textResource "hello"
		assert text.text != null
		assert text.language.language == defaultLocale.language
		assert text.URL.toString() =~ "com/anrisoftware/resources/texts/${defaultLocale.language}/hello.txt"
	}

	@Test
	void "load formatted text with place holders"() {
		String str = "aaa"
		int num = 5
		float dec = 1.5

		TextResource text = resources.textResource "withplaceholders", Locale.GERMAN
		assertStringContent text.formatText(str, num, dec), "Ein Text mit Placeholder: aaa 5 1,500"
		assert text.language.language == "de"
		assert text.URL.toString() =~ "com/anrisoftware/resources/texts/de/withplaceholders.txt"

		text = resources.textResource "withplaceholders", Locale.ENGLISH
		assertStringContent text.formatText(str, num, dec), "A text with place holders: aaa 5 1.500"
		assert text.language.language == "en"
		assert text.URL.toString() =~ "com/anrisoftware/resources/texts/en/withplaceholders.txt"
	}
}
