package com.anrisoftware.resources.texts

import org.junit.Test

import com.anrisoftware.resources.texts.api.TextsFactory
import com.anrisoftware.resources.texts.maps.TextsDefaultMapsModule
import com.anrisoftware.resources.texts.texts.TextsResourcesCharsetModule
import com.anrisoftware.resources.texts.texts.TextsResourcesModule
import com.google.inject.Injector

/**
 * Creates the text resources and runs functionality tests.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TextResourceTest extends AbstractTextResourceTest {

	@Test
	void "load plain text with defined locale"() {
		super."load plain text with defined locale"()
	}

	@Override
	def getTextsModule() {
		new TextsResourcesModule()
	}

	@Override
	def getTextsMapModule() {
		new TextsDefaultMapsModule()
	}

	@Override
	def createFactory() {
		injector.getInstance(TextsFactory)
	}

	@Override
	def getCharacterSetModule() {
		new TextsResourcesCharsetModule()
	}
}
