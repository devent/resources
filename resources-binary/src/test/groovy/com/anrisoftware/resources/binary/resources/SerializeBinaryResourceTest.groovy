package com.anrisoftware.resources.binary.resources

import org.junit.Test

import com.anrisoftware.resources.api.BinaryResourceFactory
import com.google.inject.Injector

class SerializeBinaryResourceTest extends AbstractSerializeBinaryResourceTestUtil {

	def getResourcesModule() {
		new BinaryResourceModule()
	}

	@Test
	void "test serialize binary resource"() {
		super."test serialize binary resource"()
	}
}
