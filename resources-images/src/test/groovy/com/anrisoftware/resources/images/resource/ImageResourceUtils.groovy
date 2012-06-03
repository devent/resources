package com.anrisoftware.resources.images.resource

import org.junit.Before

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.resources.api.ImageResourceFactory
import com.anrisoftware.resources.api.Images
import com.anrisoftware.resources.images.ResourcesImagesModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule
import com.google.common.io.Resources
import com.google.inject.Guice
import com.google.inject.Injector


/**
 * Creates the image resource factory.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
class ImageResourceUtils extends TestUtils {

	public static imageLowURL = Resources.getResource("com/anrisoftware/resources/images/logos/xx/ldpi/x-mail-distribution-list.png")

	static final String IMAGE_NAME = "x-mail-distribution-list"

	def modules

	Injector injector

	Images resources

	ImageResourceFactory factory

	@Before
	void before() {
		modules = lazyCreateModules()
		injector = lazyCreateInjector()
		factory = injector.getInstance(ImageResourceFactory)
	}

	def lazyCreateModules() {
		modules == null ?
				[
					resourcesImagesModule,
					imageScalingWorkerModule,
				].flatten()
				: modules
	}

	def getResourcesImagesModule() {
		new ResourcesImagesModule()
	}

	def getImageScalingWorkerModule() {
		new ResourcesSmoothScalingModule()
	}

	def lazyCreateInjector() {
		injector == null ? Guice.createInjector(modules) : injector
	}
}
