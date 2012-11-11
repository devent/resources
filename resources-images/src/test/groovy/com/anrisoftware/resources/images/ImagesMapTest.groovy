package com.anrisoftware.resources.images

import org.junit.Test

import com.anrisoftware.resources.images.api.ImageResolution
import com.anrisoftware.resources.images.api.ImageResource
import com.anrisoftware.resources.images.api.ImageResourceFactory
import com.anrisoftware.resources.images.api.ImagesFactory
import com.anrisoftware.resources.images.api.ImagesMapFactory
import com.anrisoftware.resources.images.images.ResourcesImagesModule
import com.anrisoftware.resources.images.maps.ResourcesImagesMapsModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule

/**
 * Test the images map.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ImagesMapTest extends AbstractImagesMapTest {

	@Test
	void "put images to map without dublicates"() {
		super."put images to map without dublicates"()
	}

	@Test
	void "put images to map with dublicates"() {
		super."put images to map with dublicates"()
	}

	@Test
	void "return custom sizes of images"() {
		super."return custom sizes of images"()
	}

	@Test
	void "return custom sizes of images with auto resolution"() {
		super."return custom sizes of images with auto resolution"()
	}


	@Override
	def createFactory() {
		injector.getInstance(ImagesFactory)
	}

	@Override
	def createImagesMapFactory() {
		injector.getInstance(ImagesMapFactory)
	}

	@Override
	def createImageResourceFactory() {
		injector.getInstance(ImageResourceFactory)
	}

	@Override
	def getImagesModule() {
		new ResourcesImagesModule()
	}

	@Override
	def getMapModule() {
		new ResourcesImagesMapsModule()
	}

	@Override
	def getScalingWorkerModule() {
		new ResourcesSmoothScalingModule()
	}

	@Override
	def getLow() {
		ImageResolution.LOW
	}

	@Override
	def getMedium() {
		ImageResolution.MEDIUM
	}

	@Override
	def getHigh() {
		ImageResolution.HIGH
	}

	@Override
	def getExtraHigh() {
		ImageResolution.EXTRA_HIGH
	}

	@Override
	def toImageResource(def map) {
		map as ImageResource
	}
}
