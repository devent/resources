package com.anrisoftware.resources.texts

import java.nio.charset.Charset

import javax.inject.Named

import org.junit.Before
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestUtils;
import com.anrisoftware.resources.api.TextResource
import com.anrisoftware.resources.api.Texts;
import com.google.common.base.Charsets
import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Provides
import com.google.inject.name.Names

class TextResourcesTest extends TestUtils {

	static imageresourcesProperties = resourceURL(TextResourcesTest, "texts.properties")

	static injector = Guice.createInjector(
	new ResourcesTextsModule(),
	new AbstractModule() {
		@Override
		protected void configure() {
			bind(Charset).annotatedWith(Names.named("texts-default-charset")) toInstance Charsets.UTF_8
		}

		@Provides
		@Named("texts-properties")
		Properties getTextsProperties() {
			def properties = new Properties()
			properties.load imageresourcesProperties.openStream()
			properties
		}
	})

	Texts resources

	@Before
	void before() {
		resources = injector.getInstance(Texts).loadResources()
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
}
