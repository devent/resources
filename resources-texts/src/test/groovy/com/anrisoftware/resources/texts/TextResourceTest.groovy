package com.anrisoftware.resources.texts

import java.nio.charset.Charset
import java.util.Properties

import javax.inject.Named

import org.junit.Before
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.resources.api.TextResource
import com.anrisoftware.resources.api.Texts
import com.google.common.base.Charsets
import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Provides
import com.google.inject.name.Names

/**
 * Creates the text resources and runs functionality tests.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TextResourceTest extends TestUtils {

	public static textsPropertiesURL = resourceURL(AutoDefaultCharsetTest, "texts_with_default_charset.properties")

	def modules

	Injector injector

	Texts resources

	@Before
	void before() {
		modules = lazyCreateModules()
		injector = lazyCreateInjector()
		resources = injector.getInstance(Texts).loadResources()
	}

	def lazyCreateModules() {
		modules == null ?
				[
					resourcesTextsModule,
					textsResourcesModule,
					characterSetModule,
				].flatten()
				: modules
	}

	def getResourcesTextsModule() {
		new ResourcesTextsModule()
	}

	def getTextsResourcesModule() {
		new AbstractModule() {
					@Override
					protected void configure() {
					}

					@Provides
					@Named("texts-properties")
					Properties getTextsProperties() {
						def properties = new Properties()
						properties.load textsPropertiesURL.openStream()
						properties
					}
				}
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
	void "load plain text"() {
		TextResource text = resources.textResource "hello", Locale.GERMAN
		assertStringContent text.text, "Hallo Welt"
		assert text.language.language == "de"
		assert text.URL.toString() =~ "com/anrisoftware/resources/texts/de/hello.txt"

		text = resources.textResource "hello", Locale.ENGLISH
		assertStringContent text.text, "Hello World"
		assert text.language.language == "en"
		assert text.URL.toString() =~ "com/anrisoftware/resources/texts/en/hello.txt"

		text = resources.textResource "defaultlang", Locale.ENGLISH
		assertStringContent text.text, "Only English sorry."
		assert text.language == null
		assert text.URL.toString() =~ "com/anrisoftware/resources/texts/defaultlang.txt"
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
