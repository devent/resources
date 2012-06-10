package com.anrisoftware.resources.images

import org.junit.Before

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.resources.api.ImagesFactory
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Create the images factory to test the image resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractImageResourcesTestUtils extends TestUtils {

	def modules

	Injector injector

	ImagesFactory factory

	@Before
	void before() {
		modules = lazyCreateModules()
		injector = injector == null ? Guice.createInjector(modules) : injector
		factory = injector.getInstance(ImagesFactory)
	}

	def lazyCreateModules() {
		modules == null ?
				[
					imagesModule,
					mapModule,
					scalingWorkerModule,
				].flatten()
				: modules
	}

	/**
	 * Returns the images module.
	 */
	abstract getImagesModule()

	/**
	 * Returns the images maps module.
	 */
	abstract getMapModule()

	/**
	 * Returns the image scaling worker module.
	 */
	abstract getScalingWorkerModule()
}
