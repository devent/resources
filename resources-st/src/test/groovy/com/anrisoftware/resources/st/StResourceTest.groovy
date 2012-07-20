package com.anrisoftware.resources.st

import org.junit.Test

import com.anrisoftware.resources.st.maps.TemplatesDefaultMapsModule
import com.anrisoftware.resources.templates.AbstractSTTemplateResourceTest

/**
 * Creates the ST template resources and runs functionality tests.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class StResourceTest extends AbstractSTTemplateResourceTest {

	def getTemplatesModule() {
		new StResourcesModule()
	}

	def getTemplatesMapModule() {
		new TemplatesDefaultMapsModule()
	}

	@Test
	void "load ST template"() {
		super."load ST template"()
	}

	@Test
	void "load ST template with same data"() {
		super."load ST template with same data"()
	}
}
