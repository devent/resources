package com.anrisoftware.resources.binary

import org.junit.Before
import org.junit.BeforeClass

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.resources.api.BinariesFactory
import com.google.common.io.Resources
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Creates the environment to test the binary resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractBinaryResourcesTestUtil extends TestUtils {

	static inputs

	static outputs

	@BeforeClass
	static void beforeClass() {
		inputs = [
			[baseName: "Zipfiles", resources: [
					[name: "lorem", locale: Locale.GERMAN],
					[name: "lorem1", locale: Locale.GERMAN],
					[name: "lorem2", locale: Locale.GERMAN],
					[name: "lorem3", locale: Locale.GERMAN],
					[name: "lorem4", locale: Locale.GERMAN],
					[name: "lorem5", locale: Locale.GERMAN],
					[name: "lorem6", locale: Locale.GERMAN],
					[name: "lorem7", locale: Locale.GERMAN],
					[name: "lorem8", locale: Locale.GERMAN],
					[name: "lorem9", locale: Locale.GERMAN],
					[name: "lorem", locale: new Locale("ru")],
					[name: "lorem", locale: null]
				]],
		]
		outputs = [
			[baseName: "Zipfiles", resources: [
					[url: Resources.getResource("com/anrisoftware/resources/binary/zipfiles/de/Lorem ipsum.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
					[url: Resources.getResource("com/anrisoftware/resources/binary/zipfiles/de/Lorem ipsum-1.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
					[url: Resources.getResource("com/anrisoftware/resources/binary/zipfiles/de/Lorem ipsum-2.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
					[url: Resources.getResource("com/anrisoftware/resources/binary/zipfiles/de/Lorem ipsum-3.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
					[url: Resources.getResource("com/anrisoftware/resources/binary/zipfiles/de/Lorem ipsum-4.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
					[url: Resources.getResource("com/anrisoftware/resources/binary/zipfiles/de/Lorem ipsum-5.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
					[url: Resources.getResource("com/anrisoftware/resources/binary/zipfiles/de/Lorem ipsum-6.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
					[url: Resources.getResource("com/anrisoftware/resources/binary/zipfiles/de/Lorem ipsum-7.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
					[url: Resources.getResource("com/anrisoftware/resources/binary/zipfiles/de/Lorem ipsum-8.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
					[url: Resources.getResource("com/anrisoftware/resources/binary/zipfiles/de/Lorem ipsum-9.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
					[url: Resources.getResource("com/anrisoftware/resources/binary/zipfiles/Lorem ipsum.html.zip"), locale: Locale.getDefault(), availableBytes: 71573],
				]],
		]
	}

	def modules

	Injector injector

	BinariesFactory factory

	@Before
	void before() {
		modules = lazyCreateModules()
		injector = injector == null ? Guice.createInjector(modules) : injector
		factory = injector.getInstance BinariesFactory
	}

	def lazyCreateModules() {
		modules == null ?
				[
					resourcesModule,
					mapModule,
				].flatten()
				: modules
	}

	abstract def getResourcesModule()

	abstract def getMapModule()
}
