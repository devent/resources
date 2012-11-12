package com.anrisoftware.resources.texts

import org.junit.Test

import com.anrisoftware.resources.texts.api.TextResourceFactory
import com.anrisoftware.resources.texts.api.TextsFactory
import com.anrisoftware.resources.texts.maps.TextsDefaultMapsModule
import com.anrisoftware.resources.texts.texts.TextsResourcesCharsetModule
import com.anrisoftware.resources.texts.texts.TextsResourcesModule

/**
 * Test the serializable text resource.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class SerializeTextResourceTest extends AbstractSerializationTest {

	@Test
	void "serialize text resource"() {
		super."serialize text resource"()
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

	@Override
	def createTextFactory() {
		injector.getInstance(TextResourceFactory)
	}
}
