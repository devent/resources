package com.anrisoftware.resources.texts

import java.nio.charset.Charset


import org.junit.Test

import com.anrisoftware.resources.texts.api.TextsFactory;
import com.anrisoftware.resources.texts.maps.TextsDefaultMapsModule
import com.anrisoftware.resources.texts.texts.TextsResourcesModule;
import com.google.inject.Injector

/**
 * Creates the text resources and runs functionality tests.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TextResourceTest extends AbstractTextResourceTest {

	def getTextsModule() {
		new TextsResourcesModule()
	}

	def getTextsMapModule() {
		new TextsDefaultMapsModule()
	}

	@Test
	void "load plain text with defined locale"() {
		super."load plain text with defined locale"()
	}
}
