package com.anrisoftware.resources.binary

import static com.google.inject.name.Names.named
import static com.anrisoftware.resources.binary.maps.BinaryResourcesCacheBinderModule.*

import javax.cache.CacheManager
import javax.cache.Caching
import javax.cache.event.CacheEntryCreatedListener
import javax.cache.event.CacheEntryExpiredListener
import javax.cache.event.CacheEntryReadListener
import javax.cache.event.CacheEntryRemovedListener
import javax.cache.event.CacheEntryUpdatedListener
import javax.cache.event.NotificationScope

import net.sf.ehcache.store.MemoryStoreEvictionPolicy

import org.junit.Before
import org.junit.Test

import com.anrisoftware.resources.api.Binaries


class CachingTest extends AbstractBinaryResourcesTestUtil {

	static CacheManager cacheManager = Caching.getCacheManager()

	CachingUtil cachingUtil

	@Before
	public void before() {
		cachingUtil = new CachingUtil(cacheManager)
		cachingUtil.cache.configuration.cacheConfiguration.timeToIdleSeconds = 5
		cachingUtil.cache.configuration.cacheConfiguration.timeToLiveSeconds = 5
		cachingUtil.cache.configuration.cacheConfiguration.maxEntriesLocalHeap = 5
		cachingUtil.cache.configuration.cacheConfiguration.memoryStoreEvictionPolicyFromObject = MemoryStoreEvictionPolicy.FIFO

		def scope = NotificationScope.REMOTE
		def synchronous = false
		cachingUtil.cache.registerCacheEntryListener([entryCreated: { event -> println event }, entriesCreated: { events -> println events }]as CacheEntryCreatedListener, scope, synchronous)
		cachingUtil.cache.registerCacheEntryListener({ entry -> println entry }as CacheEntryExpiredListener, scope, synchronous)
		cachingUtil.cache.registerCacheEntryListener([entryRead: { event -> println event }, entriesRead: { events -> println events }]as CacheEntryReadListener, scope, synchronous)
		cachingUtil.cache.registerCacheEntryListener([entryRemoved: { event -> println event }, entriesRemoved: { events -> println events }]as CacheEntryRemovedListener, scope, synchronous)
		cachingUtil.cache.registerCacheEntryListener([entryUpdated: { event -> println event }, entriesUpdated: { events -> println events }]as CacheEntryUpdatedListener, scope, synchronous)
		super.before()
	}

	def getResourcesModule() {
		new BinariesResourcesModule()
	}

	def getMapModule() {
		cachingUtil.mapModule
	}

	@Test
	void "test time to idle"() {
		def binariesMap = [:]
		inputs.eachWithIndex { it, i ->
			createBinaries(binariesMap, it.baseName, it.resources)
		}
		inputs.eachWithIndex { it, i ->
			loadBinaryData binariesMap[it.baseName], it.resources, 1000
		}
	}

	def createBinaries(def binariesMap, def baseName, def resources) {
		def binaries = factory.create(baseName)
		loadBinaryData binaries, resources
		binariesMap[baseName] = binaries
	}

	private loadBinaryData(Binaries binaries, def resources, long waitTime = 0) {
		resources.eachWithIndex { it, i ->
			Thread.sleep waitTime
			def binary = binaries.binaryResource it.name, it.locale
			binary.getBinary()
		}
	}
}
