package com.anrisoftware.resources.binary

import com.anrisoftware.resources.api.Binaries

abstract class AbstractBinaryResourcesTest extends AbstractBinaryResourcesTestUtil {

	void "load lorem zipfile with different locale"() {
		inputs.eachWithIndex { it, i ->
			testBinaries(it.baseName, it.resources, outputs[i].resources)
		}
	}

	def testBinaries(def baseName, def resources, def output) {
		def binaries = factory.create(baseName)
		resources.eachWithIndex { it, i ->
			testBinary(output[i], binaries, it.name, it.locale)
		}
	}

	def testBinary(def output, Binaries binaries, def name, def locale) {
		def binary = binaries.getResource name, locale
		assert binary.name == name
		assert binary.locale == output.locale
		assert binary.URL == output.url
		assert binary.binary.size() == output.availableBytes
		assert binary.stream.available() == output.availableBytes
	}
}
