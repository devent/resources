package com.anrisoftware.resources.images.maps

import static com.anrisoftware.resources.api.ImageResolution.*

import java.awt.Dimension

import org.junit.Before

import com.anrisoftware.resources.api.ImageResolution
import com.anrisoftware.resources.api.ImageResource
import com.anrisoftware.resources.images.AbstractImageResourcesTestUtils

/**
 * Unit tests for {@link ImagesMapImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractImagesMapTest extends AbstractImageResourcesTestUtils {

	static en = Locale.ENGLISH

	def factory

	def map

	def noDublicates

	def withDublicates

	def outputsWithDublicates

	def customSizes

	@Before
	void beforeTest() {
		factory = createImagesMapFactory()
		map = factory.create()
	}

	private "create data with custom height and width"() {
		def images = [
			createImage([name: "imageA", locale: en, resolution: LOW,    image: "00", width: 1, height: 2]),
			createImage([name: "imageA", locale: en, resolution: MEDIUM, image: "01", width: 1, height: 3]),
			createImage([name: "imageA", locale: en, resolution: HIGH,   image: "02", width: 1, height: 4]),
		]
		customSizes = [
					inputs: [
						images[0],
						images[1],
						images[2],
					],
					outputs: [
						[name: "imageA", width: 1, height: 1, image: images[0], resolution: LOW],
						[name: "imageA", width: 1, height: 2, image: images[0], resolution: LOW],
						[name: "imageA", width: 1, height: 3, image: images[1], resolution: MEDIUM],
						[name: "imageA", width: 1, height: 4, image: images[2], resolution: HIGH],
						[name: "imageA", width: 1, height: 5, image: images[2], resolution: HIGH],
					],
				]
	}

	private "create data containing duplicates"() {
		def images = [
			createImage([name: "imageA", locale: en, resolution: LOW,    image: "00", width: 1, height: 2]),
			createImage([name: "imageA", locale: en, resolution: LOW,    image: "01", width: 1, height: 2]),
			createImage([name: "imageA", locale: en, resolution: LOW,    image: "02", width: 1, height: 2]),
			createImage([name: "imageA", locale: en, resolution: MEDIUM, image: "03", width: 1, height: 2]),
			createImage([name: "imageA", locale: en, resolution: HIGH,   image: "04", width: 1, height: 2]),
		]
		withDublicates = [
					inputs: [
						images[0],
						images[1],
						images[2],
						images[3],
						images[4],
					],
					outputs: [
						images[0],
						images[3],
						images[4],
					]]
	}

	private "create data containing no duplicates"() {
		noDublicates = [
					inputs_and_outputs: [
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
					]]
	}

	/**
	 * Create the images map factory.
	 */
	abstract createImagesMapFactory()

	void "put images to map without dublicates"() {
		"create data containing no duplicates"()
		noDublicates.inputs_and_outputs.each { map.putImage it }
		noDublicates.inputs_and_outputs.each {
			def size = new Dimension(it.width, it.height)
			def image = map.getImage it.name, size, it.resolution
			assert image == it
		}
	}

	void "put images to map with dublicates"() {
		"create data containing duplicates"()
		withDublicates.inputs.each { map.putImage it }
		withDublicates.outputs.each {
			def size = new Dimension(it.width, it.height)
			def image = map.getImage it.name, size, it.resolution
			assert image == it
		}
	}

	void "return custom sizes of images"() {
		"create data with custom height and width"()
		customSizes.inputs.each { map.putImage it }
		customSizes.outputs.each {
			def size = new Dimension(it.width, it.height)
			def image = map.getImage it.name, size, it.resolution
			assert image == it.image
		}
	}

	void "return custom sizes of images with auto resolution"() {
		"create data with custom height and width"()
		customSizes.inputs.each { map.putImage it }
		customSizes.outputs.each {
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
