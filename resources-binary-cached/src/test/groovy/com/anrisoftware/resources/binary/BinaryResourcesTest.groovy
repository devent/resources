package com.anrisoftware.resources.binary

import javax.cache.Caching

import org.junit.Test

import com.anrisoftware.resources.binary.api.BinariesFactory
import com.anrisoftware.resources.binary.binaries.BinariesResourcesModule
import com.google.inject.Guice
import com.google.inject.Injector


class BinaryResourcesTest extends AbstractBinaryResourcesTest {

	@Test
	void "load lorem zipfile with different locale"() {
		super."load lorem zipfile with different locale"()
	}

	@Override
	Injector createInjector() {
		if (injector != null) {
			injector
		}
		Guice.createInjector([resourcesModule, mapModule].flatten())
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
		new CachingUtil(Caching.getCacheManager()).mapModule
	}
}
