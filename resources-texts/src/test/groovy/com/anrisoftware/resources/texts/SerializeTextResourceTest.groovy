package com.anrisoftware.resources.texts

import org.junit.Test

import com.anrisoftware.resources.texts.maps.ResourcesTextsMapsModule

class SerializeTextResourceTest extends AbstractSerializationTest {

	@Test
	void "serialize text resource"() {
		super."serialize text resource"()
	}

	@Override
	Object getTextsModule() {
		new ResourcesTextsModule()
	}

	@Override
	Object getTextsMapModule() {
		new ResourcesTextsMapsModule()
	}
}
