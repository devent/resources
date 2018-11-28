/**
 * Copyright © 2012 Erwin Müller (erwin.mueller@anrisoftware.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

	boolean storeByValue

	boolean statisticsEnabled

	CachingUtil(CacheManager manager,
	boolean storeByValue = true,
	boolean statisticsEnabled = false) {
		this.manager = manager
		this.cache = createCache()
		this.storeByValue = storeByValue
		this.statisticsEnabled = statisticsEnabled
	}

	private createCache() {
		def builder = manager.createCacheBuilder cacheName
		builder.setStoreByValue storeByValue
		//builder.setReadThrough(true)
		//builder.setWriteThrough(false)
		builder.setStatisticsEnabled statisticsEnabled
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

	Cache getCache() {
		cache
	}

}
