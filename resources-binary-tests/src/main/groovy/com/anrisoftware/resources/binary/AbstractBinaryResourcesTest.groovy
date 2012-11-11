package com.anrisoftware.resources.binary

import static com.anrisoftware.globalpom.utils.TestUtils.*

/**
 * Test the binary resources.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractBinaryResourcesTest extends AbstractBinaryResourcesTestUtil {

	void "load lorem zipfile with different locale"() {
		inputs.eachWithIndex { it, i ->
			testBinaries(it.baseName, it.resources, outputs[i].resources)
		}
	}

	void "test serialize binary resource"() {
		def input = inputs[0]
		def resource = input.resources[0]
		def output = outputs[0].resources[0]
		def binaries = factory.create(input.baseName)
		def binary = binaries.getResource resource.name, resource.locale
		def binaryB = reserialize(binary)
		testBinaryArgs(binaryB, [name: resource.name, locale: output.locale, url: output.url, size: output.availableBytes])
	}

	def testBinaries(def baseName, def resources, def output) {
		def binaries = factory.create(baseName)
		resources.eachWithIndex { it, i ->
			testBinary(output[i], binaries, it.name, it.locale)
		}
	}

	def testBinary(def output, def binaries, def name, def locale) {
		def binary = binaries.getResource name, locale
		testBinaryArgs(binary, [name: name, locale: output.locale, url: output.url, size: output.availableBytes])
	}

	def testBinaryArgs(def binary, def args) {
		assert binary.name == args.name
		assert binary.locale == args.locale
		assert binary.URL == args.url
		assert binary.binary.size() == args.size
		assert binary.stream.available() == args.size
	}
}
