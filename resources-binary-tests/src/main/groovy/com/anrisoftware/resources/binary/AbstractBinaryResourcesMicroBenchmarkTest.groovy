

package com.anrisoftware.resources.binary

import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.junit.BeforeClass

import com.anrisoftware.resources.api.Binaries

abstract class AbstractBinaryResourcesMicroBenchmarkTest extends AbstractBinaryResourcesTestUtil {

	@BeforeClass
	static void beforeClass() {
		AbstractBinaryResourcesTestUtil.beforeClass()
		Logger.getLogger(Binaries).level = Level.INFO
	}

	void "access binary data of resource micro benchmark"() {
		def callback = { Binaries binaries, name, locale ->
			Thread.sleep 500
			long old = System.currentTimeMillis()
			def binary = binaries.binaryResource name, locale
			binary.getBinary()
			long now = System.currentTimeMillis()
			now - old
		}

		printf "access binary data of resource micro benchmark:%n"
		inputs.eachWithIndex { it, i ->
			createBinaries(it.baseName, it.resources, callback)
		}
	}

	void "open binary stream of resource micro benchmark"() {
		def callback = { Binaries binaries, name, locale ->
			Thread.sleep 500
			long old = System.currentTimeMillis()
			def binary = binaries.binaryResource name, locale
			binary.getStream()
			long now = System.currentTimeMillis()
			now - old
		}

		printf "open binary stream of resource micro benchmark:%n"
		inputs.eachWithIndex { it, i ->
			createBinaries(it.baseName, it.resources, callback)
		}
	}

	def createBinaries(def baseName, def resources, def callback) {
		def binaries = factory.create(baseName)
		long firstAccessTime = 0
		long secondAccessTime = 0
		int num = resources.size()

		resources.eachWithIndex { it, i ->
			firstAccessTime += callback binaries, it.name, it.locale
		}
		resources.eachWithIndex { it, i ->
			secondAccessTime += callback binaries, it.name, it.locale
		}

		printf "First access:%n"
		printf "System time (%d): (%.3f)%n", num, firstAccessTime/num
		printf "Second access:%n"
		printf "System time (%d): (%.3f)%n", num, secondAccessTime/num
	}
}
