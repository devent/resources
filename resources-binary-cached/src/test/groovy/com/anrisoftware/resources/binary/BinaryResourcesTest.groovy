package com.anrisoftware.resources.binary

import javax.cache.Caching

import org.junit.Test


class BinaryResourcesTest extends AbstractBinaryResourcesTest {

	@Test
	void "load lorem zipfile with different locale"() {
		super."load lorem zipfile with different locale"()
	}

	def getResourcesModule() {
		new BinariesResourcesModule()
	}

	def getMapModule() {
		new CachingUtil(Caching.getCacheManager()).mapModule
	}
}
