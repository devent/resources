package com.anrisoftware.resources.binary

import org.junit.Test

import com.anrisoftware.resources.binary.api.BinariesFactory
import com.anrisoftware.resources.binary.maps.BinariesDefaultMapsModule

class BinaryResourcesTest extends AbstractBinaryResourcesTest {

	@Test
	void "load lorem zipfile with different locale"() {
		super."load lorem zipfile with different locale"()
	}

	@Override
	def createFactory() {
		injector.getInstance(BinariesFactory)
	}

	@Override
	def getResourcesModule() {
		new BinariesResourcesModule()
	}

	@Override
	def getMapModule() {
		new BinariesDefaultMapsModule()
	}
}
