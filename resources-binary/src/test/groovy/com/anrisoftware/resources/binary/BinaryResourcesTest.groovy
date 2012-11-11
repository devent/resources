package com.anrisoftware.resources.binary

import org.junit.Test

import com.anrisoftware.resources.binary.api.BinariesFactory
import com.anrisoftware.resources.binary.binaries.BinariesResourcesModule
import com.anrisoftware.resources.binary.maps.BinariesDefaultMapsModule

/**
 * Test the binary resources.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class BinaryResourcesTest extends AbstractBinaryResourcesTest {

	@Test
	void "load lorem zipfile with different locale"() {
		super."load lorem zipfile with different locale"()
	}

	@Test
	void "test serialize binary resource"() {
		super."test serialize binary resource"()
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
