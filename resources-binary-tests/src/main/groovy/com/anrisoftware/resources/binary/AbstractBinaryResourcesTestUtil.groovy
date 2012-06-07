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
					[name: "lorem", locale: new Locale("ru")],
					[name: "lorem", locale: null]
				]],
		]
		outputs = [
			[baseName: "Zipfiles", resources: [
					[url: Resources.getResource("com/anrisoftware/resources/binary/zipfiles/de/Lorem ipsum.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
					[url: Resources.getResource("com/anrisoftware/resources/binary/zipfiles/ru/Lorem ipsum.html.zip"), locale: new Locale("ru"), availableBytes: 71906],
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
