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

		printf "Lookup in cache:%n"
		(0..10).each {
			Thread.sleep 1000
			current = System.currentTimeMillis()
			(0..max).each { value = cache.get "Test_A" }
			now = System.currentTimeMillis()

			def s = cache.statistics
			printf "system time (%d): %.3f; statistics: %.3f%n", max, (now-current) / max, s.averageGetMillis
			max *= 2
			s.clearStatistics()
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

		printf "Lookup in hash map:%n"
		(0..10).each {
			current = System.currentTimeMillis()
			Thread.sleep 1000
			(0..max).each { value = map.get "Test_A" }
			now = System.currentTimeMillis()
			printf "system time (%d): %.3f%n", max, (now-current) / max
			max *= 2
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

		printf "Lookup in concurrent map:%n"
		(0..10).each {
			current = System.currentTimeMillis()
			Thread.sleep 1000
			(0..max).each { value = map.get "Test_A" }
			now = System.currentTimeMillis()
			printf "system time (%d): %.3f%n", max, (now-current) / max
			max *= 2
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