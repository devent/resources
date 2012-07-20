package com.anrisoftware.resources.st

import org.junit.Test

import com.anrisoftware.resources.st.maps.TemplatesDefaultMapsModule
import com.anrisoftware.resources.templates.AbstractSerializationTest

class SerializeTextResourceTest extends AbstractSerializationTest {

	def getTemplatesModule() {
		new StResourcesModule()
	}

	def getTemplatesMapModule() {
		new TemplatesDefaultMapsModule()
	}

	@Test
	void "serialize resource"() {
		super."serialize resource"()
	}
}
