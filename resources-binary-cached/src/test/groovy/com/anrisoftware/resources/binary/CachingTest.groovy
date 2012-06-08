package com.anrisoftware.resources.binary

import static com.google.inject.name.Names.named
import static com.anrisoftware.resources.binary.maps.BinaryResourcesCacheBinderModule.*

import javax.cache.CacheManager
import javax.cache.Caching

import com.anrisoftware.resources.binary.maps.BinaryResourcesCachedModule
import com.anrisoftware.resources.binary.maps.BinaryResourcesCacheBinderModule.CacheFactory
import com.google.inject.AbstractModule

class CachingTest extends AbstractBinaryResourcesTestUtil {

	static CacheManager cacheManager = Caching.getCacheManager()

	def getResourcesModule() {
		new BinariesResourcesModule()
	}

	def getMapModule() {
		def builderFactory = {
			manager, name ->
			def cache = cacheManager.getCache(name)
			cache = cache == null ? createCache(name) : cache
		}as CacheFactory
		[
			new BinaryResourcesCachedModule(),
			new AbstractModule() {
				@Override
				protected void configure() {
					bind(CacheManager.class).annotatedWith(
							named(BINARIES_MAP_CACHE_MANAGER)).toInstance(cacheManager)
					bind(CacheFactory.class).annotatedWith(
							named(BINARIES_CACHE_FACTORY)).toInstance(builderFactory)
				}
			}
		]
	}

	void "test time to idle"() {
		inputs.eachWithIndex { it, i ->
			createBinaries(it.baseName, it.resources, callback)
		}
	}

	def createBinaries(def baseName, def resources, def callback) {
		def binaries = factory.create(baseName)
		long firstAccessTime = 0
		long secondAccessTime = 0
		int num = resources.size()

		resources.eachWithIndex { it, i ->
			firstAccessTime += callback binaries, it.name, it.locale
		}
		resources.eachWithIndex { it, i ->
			secondAccessTime += callback binaries, it.name, it.locale
		}

		printf "First access:%n"
		printf "System time (%d): (%.3f)%n", num, firstAccessTime/num
		printf "Second access:%n"
		printf "System time (%d): (%.3f)%n", num, secondAccessTime/num
	}
}
