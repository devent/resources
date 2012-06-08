package com.anrisoftware.resources.binary

import javax.cache.CacheManager
import javax.cache.Caching
import javax.cache.event.CacheEntryCreatedListener
import javax.cache.event.NotificationScope

import org.junit.Test

import com.anrisoftware.resources.binary.maps.BinaryResourcesCacheBinderModule
import com.anrisoftware.resources.binary.maps.BinaryResourcesCachedModule
import com.anrisoftware.resources.binary.maps.BinaryResourcesCacheBinderModule.BuilderFactory

class BinaryResourcesMicroBenchmarkTest extends AbstractBinaryResourcesMicroBenchmarkTest {

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
		CacheManager manager = Caching.getCacheManager()
		def builderFactory = createBuilderFactory(manager)
		[
			new BinaryResourcesCachedModule(),
			new BinaryResourcesCacheBinderModule(manager, builderFactory)
		]
	}

	private createBuilderFactory(CacheManager manager) {
		def builderFactory = {
			m, name ->
			def builder = manager.createCacheBuilder name
			builder.setStoreByValue(true)
			//builder.setReadThrough(true)
			//builder.setWriteThrough(false)
			builder.setStatisticsEnabled(false)
			//builder.setTransactionEnabled(IsolationLevel.NONE, Mode.NONE)
			builder.registerCacheEntryListener([
						entryCreated: { event -> println "entry created $event" },
						entriesCreated: { event -> println "entry created $event" }
					]as CacheEntryCreatedListener, NotificationScope.LOCAL, false)


		}as BuilderFactory
	}


}
