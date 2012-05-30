package com.anrisoftware.resources.icons

import static com.anrisoftware.resources.api.ImageResolution.*

import java.net.URL

import com.google.inject.Injector

/**
 * Test the property loaders module.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class PropertyLoaderModuleTest extends IconResourcesTest {

	def getResourcesIconsModule() {
		new ResourcesIconsPropertyLoaderModule() {
					URL getIconsPropertiesURL() {
						IconResourcesTest.iconsPropertiesURL
					}
				}
	}

	def getIconsResourcesModule() {
		[]
	}
}
