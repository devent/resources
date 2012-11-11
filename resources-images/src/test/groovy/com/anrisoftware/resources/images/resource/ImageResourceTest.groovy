package com.anrisoftware.resources.images.resource

import org.junit.Test

import com.anrisoftware.resources.images.images.ResourcesImagesModule;
import com.anrisoftware.resources.images.maps.ResourcesImagesMapsModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule

/**
 * Tests the image resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
class ImageResourceTest extends AbstractImageResourceTest {

	def getImagesModule() {
		new ResourcesImagesModule()
	}

	def getMapModule() {
		new ResourcesImagesMapsModule()
	}

	def getScalingWorkerModule() {
		new ResourcesSmoothScalingModule()
	}

	@Test
	void "load image with no resize and ldpi"() {
		super."load image with no resize and ldpi"()
	}
}
