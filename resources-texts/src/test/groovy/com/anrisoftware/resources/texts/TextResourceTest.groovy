package com.anrisoftware.resources.texts

import java.nio.charset.Charset


import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.junit.Before
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.resources.api.TextResource
import com.anrisoftware.resources.api.Texts
import com.anrisoftware.resources.api.TextsFactory
import com.anrisoftware.resources.texts.maps.ResourcesTextsMapsModule
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
		[
			new ResourcesTextsModule(),
			new ResourcesTextsMapsModule()
		]
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
		def baseName = "TextsWithDefaultCharset"
		def classLoader = getClass().classLoader
		Texts texts = factory.create baseName, classLoader

		Locale locale = Locale.GERMAN
		TextResource text = texts.textResource "hello", locale
		assertStringContent text.text, "Hallo Welt - German"
		assert text.locale == locale
		assert text.URL.toString() =~ "com/anrisoftware/resources/texts/texts/de/hello.txt"

		locale = new Locale("ru")
		text = texts.textResource "hello", locale
		assertStringContent text.text, "привет мир - Russian"
		assert text.locale == locale
		assert text.URL.toString() =~ "com/anrisoftware/resources/texts/texts/ru/hello.txt"

		locale = Locale.ENGLISH
		text = texts.textResource "hello", Locale.ENGLISH
		assertStringContent text.text, "Hello World - English Default"
		assert text.locale.toString() == ""
		assert text.URL.toString() =~ "com/anrisoftware/resources/texts/texts/hello.txt"
	}

	@Test
	void "micro-benchmark get the same text resource for different languages"() {
		Logger.getLogger(TextsImpl).setLevel(Level.INFO)
		Locale german = Locale.GERMAN
		Locale russian = new Locale("ru")
		Locale english = Locale.ENGLISH
		def baseName = "TextsWithDefaultCharset"
		def classLoader = getClass().classLoader
		Texts texts = factory.create baseName, classLoader

		TextResource text
		long current
		long now
		int max = 1

		printf "Lookup first time, difference languages:%n"
		Thread.sleep 1000
		current = System.currentTimeMillis()
		text = texts.textResource "hello", german
		text = texts.textResource "hello", russian
		text = texts.textResource "hello", english
		now = System.currentTimeMillis()
		printf "system time : %.3f%n", (now-current) / 3

		printf "Lookup second time, difference languages:%n"
		(0..10).each {
			Thread.sleep 1000
			current = System.currentTimeMillis()
			(0..max).each {
				text = texts.textResource "hello", german
				text = texts.textResource "hello", russian
				text = texts.textResource "hello", english
			}
			now = System.currentTimeMillis()
			printf "system time (%d): %.3f%n", max, (now-current) / max / 3
			max *= 2
		}
	}
}
