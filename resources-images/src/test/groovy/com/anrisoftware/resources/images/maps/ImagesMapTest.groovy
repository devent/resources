package com.anrisoftware.resources.images.maps

import static com.anrisoftware.resources.api.ImageResolution.*

import org.junit.Test

import com.anrisoftware.resources.images.api.ImagesMapFactory
import com.anrisoftware.resources.images.images.ResourcesImagesModule;
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule

/**
 * Unit tests for {@link ImagesMapImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ImagesMapTest extends AbstractImagesMapTest {

	def getImagesModule() {
		new ResourcesImagesModule()
	}

	def getMapModule() {
		new ResourcesImagesMapsModule()
	}

	def getScalingWorkerModule() {
		new ResourcesSmoothScalingModule()
	}

	def createImagesMapFactory() {
		injector.getInstance ImagesMapFactory
	}

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
}
