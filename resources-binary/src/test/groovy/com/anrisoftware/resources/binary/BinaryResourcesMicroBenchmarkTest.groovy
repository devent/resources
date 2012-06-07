package com.anrisoftware.resources.binary

import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.junit.Test

import com.anrisoftware.resources.api.Binaries

class BinaryResourcesMicroBenchmarkTest extends BinaryResourcesTestUtil {

	@Test
	void "access binary data of resource micro benchmark"() {
		Logger.getLogger(BinariesImpl).level = Level.INFO

		def callback = { Binaries binaries, name, locale ->
			Thread.sleep 500
			long old = System.currentTimeMillis()
			def binary = binaries.binaryResource name, locale
			binary.getBinary()
			//assert binary.stream.available() == output.availableBytes
			long now = System.currentTimeMillis()
			now - old
		}
		inputs.eachWithIndex { it, i ->
			createBinaries(it.baseName, it.resources, outputs[i].resources, callback)
		}
	}

	private createBinaries(def baseName, def resources, def output, def callback) {
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
