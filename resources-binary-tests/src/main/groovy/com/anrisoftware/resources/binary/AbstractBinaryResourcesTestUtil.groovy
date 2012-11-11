package com.anrisoftware.resources.binary

import static com.anrisoftware.globalpom.utils.TestUtils.*

import org.junit.Before
import org.junit.BeforeClass

import com.google.common.io.Resources
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Creates the environment to test the binary resources.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractBinaryResourcesTestUtil {

	static inputs

	static outputs

	@BeforeClass
	static void setupToStringStyle() {
		toStringStyle
	}

	@BeforeClass
	static void createTestIO() {
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
					[url: Resources.getResource("com/anrisoftware/resources/binary/zipfiles/ru/Lorem ipsum.html.zip"), locale: new Locale("ru"), availableBytes: 71906],
					[url: Resources.getResource("com/anrisoftware/resources/binary/zipfiles/Lorem ipsum.html.zip"), locale: Locale.getDefault(), availableBytes: 71573],
				]],
		]
	}

	Injector injector

	def factory

	@Before
	void createFactories() {
		injector = createInjector()
		factory = createFactory()
	}

	Injector createInjector() {
		if (injector != null) {
			injector
		}
		Guice.createInjector(resourcesModule, mapModule)
	}

	/**
	 * Create the binary resources factory.
	 */
	abstract createFactory()

	/**
	 * Returns the binary resources module.
	 */
	abstract getResourcesModule()

	/**
	 * Returns the binary resources map module.
	 */
	abstract getMapModule()
}
