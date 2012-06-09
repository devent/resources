package com.anrisoftware.resources.texts

import java.nio.charset.Charset


import org.junit.Test

import com.anrisoftware.resources.api.TextsFactory
import com.anrisoftware.resources.texts.maps.ResourcesTextsMapsModule
import com.google.inject.Injector

/**
 * Creates the text resources and runs functionality tests.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TextResourceTest extends AbstractTextResourceTest {

	def getTextsModule() {
		new ResourcesTextsModule()
	}

	def getTextsMapModule() {
		new ResourcesTextsMapsModule()
	}

	@Test
	void "load plain text with defined locale"() {
		super."load plain text with defined locale"()
	}
}
