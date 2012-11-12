package com.anrisoftware.resources.images

import org.junit.Test

import com.anrisoftware.resources.images.api.ImageResolution
import com.anrisoftware.resources.images.api.ImageResource
import com.anrisoftware.resources.images.api.ImageResourceFactory
import com.anrisoftware.resources.images.api.ImagesFactory
import com.anrisoftware.resources.images.api.ImagesMapFactory
import com.anrisoftware.resources.images.images.ImagesResourcesModule
import com.anrisoftware.resources.images.maps.ResourcesImagesMapsModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule

/**
 * Test the image resources.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ImageResourcesTest extends AbstractImageResourcesTest {

	@Test
	void "load image with no resize and ldpi"() {
		super."load image with no resize and ldpi"()
	}

	@Test
	void "load image with resize and ldpi"() {
		super."load image with resize and ldpi"()
	}

	@Test
	void "load image with resize and xhdpi"() {
		super."load image with resize and xhdpi"()
	}

	@Test
	void "same image with different sizes"() {
		super."same image with different sizes"()
	}

	@Test
	void "same image with different sizes and auto-resolution"() {
		super."same image with different sizes and auto-resolution"()
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
		new ImagesResourcesModule()
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
