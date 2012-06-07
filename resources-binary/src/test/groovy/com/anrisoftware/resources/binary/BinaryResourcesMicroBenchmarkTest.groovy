package com.anrisoftware.resources.binary

import org.junit.Test

import com.anrisoftware.resources.binary.maps.BinariesResourcesMapsModule

class BinaryResourcesMicroBenchmarkTest extends AbstractBinaryResourcesMicroBenchmarkTest {

	@Test
	void "access binary data of resource micro benchmark"() {
		super."access binary data of resource micro benchmark"()
	}

	@Test
	void "open binary stream of resource micro benchmark"() {
		super."open binary stream of resource micro benchmark"()
	}

	def getResourcesModule() {
		new BinariesResourcesModule()
	}

	def getMapModule() {
		new BinariesResourcesMapsModule()
	}
}
