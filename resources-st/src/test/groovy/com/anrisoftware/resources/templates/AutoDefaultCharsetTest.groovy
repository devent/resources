package com.anrisoftware.resources.templates

import com.anrisoftware.resources.st.worker.STTemplateDefaultPropertiesModule

/**
 * Test the template resources with the default ST template properties.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class AutoDefaultCharsetTest extends StResourceTest {

	def getDefaultPropertiesModule() {
		new STTemplateDefaultPropertiesModule()
	}
}
