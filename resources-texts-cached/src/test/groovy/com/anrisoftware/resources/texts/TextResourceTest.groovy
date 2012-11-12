package com.anrisoftware.resources.texts

import javax.cache.CacheManager
import javax.cache.Caching
import javax.cache.event.CacheEntryCreatedListener
import javax.cache.event.NotificationScope

import org.junit.Test

import com.anrisoftware.resources.texts.api.TextsFactory
import com.anrisoftware.resources.texts.cached.TextsResourcesCacheModule
import com.anrisoftware.resources.texts.cached.TextsResourcesCachedModule
import com.anrisoftware.resources.texts.cached.TextsResourcesCacheModule.BuilderFactory
import com.anrisoftware.resources.texts.texts.TextsResourcesCharsetModule
import com.anrisoftware.resources.texts.texts.TextsResourcesModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Creates the text resources and runs functionality tests.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TextResourceTest extends AbstractTextResourceTest {

	@Override
	@Test
	void "load plain text with defined locale"() {
		super."load plain text with defined locale"()
	}

	@Override
	Injector createInjector() {
		Guice.createInjector(textsModule,
						textsMapModule,
						characterSetModule,
						textsCacheModule,
						binariesModule,
						binariesMapModule)
	}

	@Override
	def createFactory() {
		injector.getInstance(TextsFactory)
	}

	@Override
	def getTextsModule() {
		new TextsResourcesModule()
	}

	@Override
	def getTextsMapModule() {
		new TextsResourcesCachedModule()
	}

	def getCharacterSetModule() {
		new TextsResourcesCharsetModule()
	}

	def getTextsCacheModule() {
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
		new TextsResourcesCacheModule(manager, builderFactory)
	}
}
