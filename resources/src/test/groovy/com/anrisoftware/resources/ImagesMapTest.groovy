package com.anrisoftware.resources

import static com.anrisoftware.resources.api.ImageResolution.*

import org.junit.Before
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestUtils;
import com.anrisoftware.resources.api.ImageResolution
import com.anrisoftware.resources.api.ImageResource
import com.google.inject.Guice

class ImagesMapTest extends TestUtils {

	static injector = Guice.createInjector()

	ImagesMap map

	def inputsNoDublicates

	def inputsWithDublicates

	def outputsWithDublicates

	def inputsCustomSizes

	@Before
	void beforeTest() {
		map = injector.getInstance ImagesMap
		inputsNoDublicates = [
			[image: createImage("00", 1, 2), name: "imageA", resolution: LOW],
			[image: createImage("01", 1, 3), name: "imageA", resolution: LOW],
			[image: createImage("02", 1, 4), name: "imageA", resolution: LOW],
			[image: createImage("03", 1, 2), name: "imageA", resolution: MEDIUM],
			[image: createImage("04", 1, 2), name: "imageA", resolution: HIGH],
			[image: createImage("05", 1, 2), name: "imageB", resolution: LOW],
			[image: createImage("06", 1, 2), name: "imageB", resolution: MEDIUM],
			[image: createImage("07", 1, 2), name: "imageB", resolution: HIGH],
			[image: createImage("08", 1, 2), name: "imageC", resolution: LOW],
			[image: createImage("09", 1, 2), name: "imageC", resolution: MEDIUM],
			[image: createImage("10", 1, 2), name: "imageC", resolution: HIGH],
			[image: createImage("11", 1, 2), name: "imageD", resolution: LOW],
			[image: createImage("12", 1, 2), name: "imageD", resolution: MEDIUM],
			[image: createImage("13", 1, 2), name: "imageD", resolution: HIGH],
		]
		def images = [
			createImage("00", 1, 2),
			createImage("01", 1, 2),
			createImage("02", 1, 2),
			createImage("03", 1, 2),
			createImage("04", 1, 2),
		]
		inputsWithDublicates = [
			[image: images[0], name: "imageA", resolution: LOW],
			[image: images[1], name: "imageA", resolution: LOW],
			[image: images[2], name: "imageA", resolution: LOW],
			[image: images[3], name: "imageA", resolution: MEDIUM],
			[image: images[4], name: "imageA", resolution: HIGH],
		]
		outputsWithDublicates = [
			[image: images[0], name: "imageA", resolution: LOW],
			[image: images[3], name: "imageA", resolution: MEDIUM],
			[image: images[4], name: "imageA", resolution: HIGH],
		]

		images = [
			createImage("00", 1, 2),
			createImage("01", 1, 3),
			createImage("02", 1, 4),
		]
		inputsCustomSizes = [
			[
				[image: images[0], name: "imageA", resolution: LOW],
				[image: images[1], name: "imageA", resolution: MEDIUM],
				[image: images[2], name: "imageA", resolution: HIGH],
			],
			[
				[name: "imageA", width: 1, height: 1, image: images[0], resolution: LOW],
				[name: "imageA", width: 1, height: 2, image: images[0], resolution: LOW],
				[name: "imageA", width: 1, height: 3, image: images[1], resolution: MEDIUM],
				[name: "imageA", width: 1, height: 4, image: images[2], resolution: HIGH],
				[name: "imageA", width: 1, height: 5, image: images[2], resolution: HIGH],
			],
		]
	}

	@Test
	void "put images to map without dublicates"() {
		inputsNoDublicates.each {
			map.putImage it.image, it.name, it.image.width, it.image.height, it.resolution
		}
		inputsNoDublicates.each {
			def image = map.getImage it.name, it.image.width, it.image.height, it.resolution
			assert image == it.image
		}
	}

	@Test
	void "put images to map with dublicates"() {
		inputsWithDublicates.each {
			map.putImage it.image, it.name, it.image.width, it.image.height, it.resolution
		}
		outputsWithDublicates.each {
			def image = map.getImage it.name, it.image.width, it.image.height, it.resolution
			assert image == it.image
		}
	}

	@Test
	void "return custom sizes of images"() {
		inputsCustomSizes[0].each {
			map.putImage it.image, it.name, it.image.width, it.image.height, it.resolution
		}
		inputsCustomSizes[1].each {
			def image = map.getImage it.name, it.width, it.height, it.resolution
			assert image == it.image
		}
	}

	@Test
	void "return custom sizes of images with auto resolution"() {
		inputsCustomSizes[0].each {
			map.putImage it.image, it.name, it.image.width, it.image.height, it.resolution
		}
		inputsCustomSizes[1].each {
			def image = map.getImage it.name, it.width, it.height
			assert image == it.image
		}
	}

	ImageResource createImage(def image, int width, int height) {
		[
					getImage: { image },
					getURL: { },
					getWidth: { width },
					getHeight: { height },
					toString: { image },
				]as ImageResource
	}
}
