package com.anrisoftware.resources.images.maps

import static com.anrisoftware.resources.api.ImageResolution.*

import java.awt.Dimension

import org.junit.Before
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.resources.api.ImageResolution
import com.anrisoftware.resources.api.ImageResource
import com.anrisoftware.resources.images.api.ImagesMap
import com.anrisoftware.resources.images.api.ImagesMapFactory
import com.google.inject.Guice

/**
 * Unit tests for {@link ImagesMapImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ImagesMapTest extends TestUtils {

	static injector = Guice.createInjector(new ResourcesImagesMapsModule())

	static ImagesMapFactory factory = injector.getInstance ImagesMapFactory

	ImagesMap map

	def inputsNoDublicates

	def inputsWithDublicates

	def outputsWithDublicates

	def inputsCustomSizes

	@Before
	void beforeTest() {
		map = factory.create()
		def en = Locale.ENGLISH

		inputsNoDublicates = [
			createImage([name: "imageA", locale: en, resolution: LOW,    image: "00", width: 1, height: 2]),
			createImage([name: "imageA", locale: en, resolution: LOW,    image: "01", width: 1, height: 3]),
			createImage([name: "imageA", locale: en, resolution: LOW,    image: "02", width: 1, height: 4]),
			createImage([name: "imageA", locale: en, resolution: MEDIUM, image: "03", width: 1, height: 2]),
			createImage([name: "imageA", locale: en, resolution: HIGH,   image: "04", width: 1, height: 2]),
			createImage([name: "imageB", locale: en, resolution: LOW,    image: "05", width: 1, height: 2]),
			createImage([name: "imageB", locale: en, resolution: MEDIUM, image: "06", width: 1, height: 2]),
			createImage([name: "imageB", locale: en, resolution: HIGH,   image: "07", width: 1, height: 2]),
			createImage([name: "imageC", locale: en, resolution: LOW,    image: "08", width: 1, height: 2]),
			createImage([name: "imageC", locale: en, resolution: MEDIUM, image: "09", width: 1, height: 2]),
			createImage([name: "imageC", locale: en, resolution: HIGH,   image: "10", width: 1, height: 2]),
			createImage([name: "imageD", locale: en, resolution: LOW,    image: "11", width: 1, height: 2]),
			createImage([name: "imageD", locale: en, resolution: MEDIUM, image: "12", width: 1, height: 2]),
			createImage([name: "imageD", locale: en, resolution: HIGH,   image: "13", width: 1, height: 2]),
		]
		def images = [
			createImage([name: "imageA", locale: en, resolution: LOW,    image: "00", width: 1, height: 2]),
			createImage([name: "imageA", locale: en, resolution: LOW,    image: "01", width: 1, height: 2]),
			createImage([name: "imageA", locale: en, resolution: LOW,    image: "02", width: 1, height: 2]),
			createImage([name: "imageA", locale: en, resolution: MEDIUM, image: "03", width: 1, height: 2]),
			createImage([name: "imageA", locale: en, resolution: HIGH,   image: "04", width: 1, height: 2]),
		]
		inputsWithDublicates = [
			images[0],
			images[1],
			images[2],
			images[3],
			images[4],
		]
		outputsWithDublicates = [
			images[0],
			images[3],
			images[4],
		]

		images = [
			createImage([name: "imageA", locale: en, resolution: LOW,    image: "00", width: 1, height: 2]),
			createImage([name: "imageA", locale: en, resolution: MEDIUM, image: "01", width: 1, height: 3]),
			createImage([name: "imageA", locale: en, resolution: HIGH,   image: "02", width: 1, height: 4]),
		]
		inputsCustomSizes = [
					put: [
						images[0],
						images[1],
						images[2],
					],
					get: [
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
		inputsNoDublicates.each { map.putImage it }
		inputsNoDublicates.each {
			def size = new Dimension(it.width, it.height)
			def image = map.getImage it.name, size, it.resolution
			assert image == it
		}
	}

	@Test
	void "put images to map with dublicates"() {
		inputsWithDublicates.each { map.putImage it }
		outputsWithDublicates.each {
			def size = new Dimension(it.width, it.height)
			def image = map.getImage it.name, size, it.resolution
			assert image == it
		}
	}

	@Test
	void "return custom sizes of images"() {
		inputsCustomSizes.put.each { map.putImage it }
		inputsCustomSizes.get.each {
			def size = new Dimension(it.width, it.height)
			def image = map.getImage it.name, size, it.resolution
			assert image == it.image
		}
	}

	@Test
	void "return custom sizes of images with auto resolution"() {
		inputsCustomSizes.put.each { map.putImage it }
		inputsCustomSizes.get.each {
			def size = new Dimension(it.width, it.height)
			def image = map.getImage it.name, size
			assert image == it.image
		}
	}

	ImageResource createImage(def image) {
		[
					getName: { image.name },
					getLocale: { image.locale },
					getURL: { image.url },
					getResolution: { image.resolution },
					getImage: { image.image },
					getWidth: { image.width },
					getHeight: { image.height },
					getSize: { new Dimension(image.width, image.height) },
					toString: { "${image.name}: ${image.image}".toString() },
				]as ImageResource
	}
}
