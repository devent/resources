package com.anrisoftware.resources.texts

import org.junit.Test

import com.anrisoftware.resources.texts.maps.TextsDefaultMapsModule
import com.anrisoftware.resources.texts.texts.TextsResourcesModule;

class SerializeTextResourceTest extends AbstractSerializationTest {

	@Test
	void "serialize text resource"() {
		super."serialize text resource"()
	}

	@Override
	Object getTextsModule() {
		new TextsResourcesModule()
	}

	@Override
	Object getTextsMapModule() {
		new TextsDefaultMapsModule()
	}
}
