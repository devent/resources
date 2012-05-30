package com.anrisoftware.resources.texts

import java.net.URL
import java.nio.charset.Charset

import com.anrisoftware.resources.api.Texts

/**
 * Test the property loaders module.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class PropertyLoaderModuleTest extends TextResourceTest {

	def getResourcesTextsModule() {
		new ResourcesTextsPropertyLoaderModule() {

					@Override
					URL getTextsPropertiesURL() {
						TextResourceTest.textsPropertiesURL
					}
				}
	}

	def getTextsResourcesModule() {
		[]
	}
}
