package com.anrisoftware.resources.binary

import javax.cache.Cache
import javax.cache.CacheManager
import javax.cache.event.CacheEntryCreatedListener
import javax.cache.event.NotificationScope

import com.anrisoftware.resources.binary.maps.BinaryResourcesCacheBinderModule
import com.anrisoftware.resources.binary.maps.BinaryResourcesCachedModule
import com.anrisoftware.resources.binary.maps.BinaryResourcesCacheBinderModule.CacheFactory

/**
 * Util class to create the caching modules.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class CachingUtil {

	private CacheManager manager

	private String cacheName = "zipfiles-cache"

	private Cache cache

	CachingUtil(CacheManager manager) {
		this.manager = manager
		this.cache = createCache()
	}

	def createCache() {
		def builder = manager.createCacheBuilder cacheName
		builder.setStoreByValue(true)
		//builder.setReadThrough(true)
		//builder.setWriteThrough(false)
		builder.setStatisticsEnabled(false)
		//builder.setTransactionEnabled(IsolationLevel.NONE, Mode.NONE)
		builder.registerCacheEntryListener([
					entryCreated: { event -> println "entry created $event" },
					entriesCreated: { event -> println "entry created $event" }
				]as CacheEntryCreatedListener, NotificationScope.LOCAL, false)
		builder.build()
	}

	def getMapModule() {
		def cacheFactory = { cache }as CacheFactory
		[
			new BinaryResourcesCachedModule(),
			new BinaryResourcesCacheBinderModule(cacheFactory)
		]
	}

}
