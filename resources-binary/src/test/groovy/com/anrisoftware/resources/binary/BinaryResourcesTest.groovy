package com.anrisoftware.resources.binary

import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.resources.api.Binaries
import com.anrisoftware.resources.api.BinariesFactory
import com.anrisoftware.resources.binary.maps.BinariesResourcesMapsModule
import com.google.common.io.Resources
import com.google.inject.Guice
import com.google.inject.Injector

class BinaryResourcesTest extends TestUtils {

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

	def getResourcesModule() {
		new BinariesResourcesModule()
	}

	def getMapModule() {
		new BinariesResourcesMapsModule()
	}

	@Test
	void "load lorem zipfile with different locale"() {
		inputs.eachWithIndex { it, i ->
			testBinaries(it.baseName, it.resources, outputs[i].resources)
		}
	}

	private testBinaries(def baseName, def resources, def output) {
		def binaries = factory.create(baseName)
		resources.eachWithIndex { it, i ->
			testBinary(output[i], binaries, it.name, it.locale)
		}
	}

	private testBinary(def output, Binaries binaries, def name, def locale) {
		def binary = binaries.binaryResource name, locale
		assert binary.name == name
		assert binary.locale == output.locale
		assert binary.URL == output.url
		assert binary.binary.size() == output.availableBytes
		assert binary.stream.available() == output.availableBytes
	}
}
