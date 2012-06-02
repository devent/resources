package com.anrisoftware.resources.texts

import javax.cache.Cache
import javax.cache.CacheManager
import javax.cache.Caching
import javax.cache.event.CacheEntryCreatedListener
import javax.cache.event.NotificationScope

import org.junit.Test

import com.google.common.collect.Maps

class EhcacheTest {

	@Test
	void "store and load items in cache"() {
		int max = 10
		def value
		long current
		long now
		def names = ["myCache1"]
		CacheManager cacheManager = Caching.getCacheManager()
		Cache cache = lazyGetCache cacheManager, names[0]
		cache.put "Test_A", "A"

		(0..10).each {
			Thread.sleep 1000
			current = System.currentTimeMillis()
			(0..max).each { value = cache.get "Test_A" }
			now = System.currentTimeMillis()

			max *= 2
			def s = cache.statistics
			printf "Lookup in cache: system time: %.3f; statistics: %.3f%n", (now-current) / max, s.averageGetMillis
		}
		println value
	}

	@Test
	void "store in a hash map"() {
		int max = 10
		long current
		long now
		def value
		def map = Maps.newHashMap()
		map.put "Test_A", "A"

		(0..10).each {
			current = System.currentTimeMillis()
			Thread.sleep 1000
			(0..max).each { value = map.get "Test_A" }
			now = System.currentTimeMillis()
			max *= 2
			printf "Lookup in hash map: %.3f%n", (now-current) / max
		}
		println value
	}

	@Test
	void "store in a concurrent map"() {
		int max = 10
		long current
		long now
		def value
		def map = Maps.newConcurrentMap()
		map.put "Test_A", "A"

		(0..10).each {
			current = System.currentTimeMillis()
			Thread.sleep 1000
			(0..max).each { value = map.get "Test_A" }
			now = System.currentTimeMillis()
			max *= 2
			printf "Lookup in concurrent map: %.3f%n", (now-current) / max
		}
		println value
	}

	def lazyGetCache(CacheManager cacheManager, String name) {
		Cache cache = cacheManager.getCache(name)
		if (cache == null) {
			def builder = cacheManager.createCacheBuilder name
			builder.setStoreByValue(true)
			//builder.setReadThrough(true)
			//builder.setWriteThrough(false)
			builder.setStatisticsEnabled(true)
			//builder.setTransactionEnabled(IsolationLevel.NONE, Mode.NONE)
			builder.registerCacheEntryListener([
						entryCreated: { event -> println "entry created $event" },
						entriesCreated: { event -> println "entry created $event" }
					]as CacheEntryCreatedListener, NotificationScope.LOCAL, false)
			cache = builder.build()
		}
		return cache
	}
}
