package com.anrisoftware.resources.binary

import org.junit.Test

import com.anrisoftware.resources.binary.maps.BinariesResourcesMapsModule

class BinaryResourcesTest extends AbstractBinaryResourcesTest {

	@Test
	void "load lorem zipfile with different locale"() {
		super."load lorem zipfile with different locale"()
	}

	def getResourcesModule() {
		new BinariesResourcesModule()
	}

	def getMapModule() {
		new BinariesResourcesMapsModule()
	}
}
