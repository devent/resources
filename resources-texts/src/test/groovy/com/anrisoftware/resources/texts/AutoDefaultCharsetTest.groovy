package com.anrisoftware.resources.texts

import java.nio.charset.Charset
import java.util.Properties

import javax.inject.Named
import com.anrisoftware.resources.api.Texts
import com.google.inject.AbstractModule
import com.google.inject.Provides

/**
 * Test the text resources with an automatically set default charset.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class AutoDefaultCharsetTest extends AbstractTextResourceTest {

	def lazyCreateModules() {
		modules == null ?
				[
					new ResourcesTextsModule(),
					new AbstractModule() {
						@Override
						protected void configure() {
						}

						@Provides
						@Named("texts-properties")
						Properties getTextsProperties() {
							def properties = new Properties()
							properties.load textsPropertiesURL.openStream()
							properties
						}
					}
				]
				: modules
	}
}
