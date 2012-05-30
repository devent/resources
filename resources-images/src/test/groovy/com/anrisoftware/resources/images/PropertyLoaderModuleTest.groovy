package com.anrisoftware.resources.images

import static com.anrisoftware.resources.api.ImageResolution.*

import java.net.URL

import com.anrisoftware.resources.api.Images
import com.google.inject.Injector

/**
 * Test the property loaders module.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class PropertyLoaderModuleTest extends ImageResourcesTest {

	def getResourcesImagesModule() {
		new ResourcesImagesPropertyLoaderModule() {
					URL getImagesPropertiesURL() {
						ImageResourcesTest.imagePropertiesURL
					}
				}
	}

	def getImagesResourcesModule() {
		[]
	}
}
