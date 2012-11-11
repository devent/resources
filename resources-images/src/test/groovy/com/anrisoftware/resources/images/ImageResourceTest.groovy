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
 * Tests the image resource.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
class ImageResourceTest extends AbstractImageResourceTest {

	@Test
	void "load image with no resize and ldpi"() {
		super."load image with no resize and ldpi"()
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
