package com.anrisoftware.resources.images

import static com.anrisoftware.resources.api.ImageResolution.*

import java.net.URL

import com.anrisoftware.resources.api.Images
import com.anrisoftware.resources.images.resource.ImageResourceTest;
import com.google.inject.Injector

/**
 * Test the property loaders module.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class PropertyLoaderModuleTest extends ImageResourceTest {

	def getResourcesImagesModule() {
		new ResourcesImagesPropertyLoaderModule() {
					URL getImagesPropertiesURL() {
						ImageResourceTest.imagePropertiesURL
					}
				}
	}

	def getImagesResourcesModule() {
		[]
	}
}
