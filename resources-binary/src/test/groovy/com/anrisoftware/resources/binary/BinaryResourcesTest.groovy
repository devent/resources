package com.anrisoftware.resources.binary

import org.junit.Test

import com.anrisoftware.resources.api.Binaries

class BinaryResourcesTest extends BinaryResourcesTestUtil {

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
