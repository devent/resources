package com.anrisoftware.resources.binary

import javax.cache.CacheManager
import javax.cache.Caching

import org.junit.Test

class MicroBenchmarkNoSerializationTest extends AbstractBinaryResourcesMicroBenchmarkTest {

	static CacheManager cacheManager = Caching.getCacheManager()

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
		boolean storeByValue = false
		new CachingUtil(cacheManager, storeByValue).mapModule
	}
}
