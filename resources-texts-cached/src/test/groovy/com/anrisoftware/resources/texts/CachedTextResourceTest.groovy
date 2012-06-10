package com.anrisoftware.resources.texts

import javax.cache.CacheManager
import javax.cache.Caching
import javax.cache.event.CacheEntryCreatedListener
import javax.cache.event.NotificationScope

import org.junit.Test

import com.anrisoftware.resources.texts.cached.ResourcesTextsCachedModule
import com.anrisoftware.resources.texts.cached.TextsCachedModule
import com.anrisoftware.resources.texts.cached.TextsCachedModule.BuilderFactory

/**
 * Test the text resources that are hold in a cache.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.2
 */
class CachedTextResourceTest extends TextResourceTest {

	def getResourcesTextsModule() {
		CacheManager manager = Caching.getCacheManager()
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

		[
			new TextsResourcesModule(),
			new ResourcesTextsCachedModule(),
			new TextsCachedModule(manager, builderFactory),
		]
	}

	@Test
	void "load plain text with defined locale"() {
		super."load plain text with defined locale"()
	}

	@Test
	void "micro-benchmark get the same text resource for different languages"() {
		super."micro-benchmark get the same text resource for different languages"()
	}

}
