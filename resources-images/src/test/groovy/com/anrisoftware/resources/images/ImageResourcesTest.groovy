package com.anrisoftware.resources.images

import static com.anrisoftware.resources.api.ImageResolution.*

import org.junit.Test

import com.anrisoftware.resources.images.api.ImageResource;
import com.anrisoftware.resources.images.api.ImagesFactory;
import com.anrisoftware.resources.images.images.ResourcesImagesModule;
import com.anrisoftware.resources.images.maps.ResourcesImagesMapsModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule
import com.google.inject.Injector

class ImageResourcesTest extends AbstractImageResourcesTest {

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
}
