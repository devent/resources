package com.anrisoftware.resources.templates

import org.junit.Test

import com.anrisoftware.resources.templates.api.TemplateResourceFactory
import com.anrisoftware.resources.templates.api.TemplatesFactory
import com.anrisoftware.resources.templates.maps.TemplatesDefaultMapsModule
import com.anrisoftware.resources.templates.templates.TemplatesResourcesModule
import com.anrisoftware.resources.templates.worker.STDefaultPropertiesModule
import com.anrisoftware.resources.templates.worker.STWorkerModule

/**
 * Creates the ST template resources and runs functionality tests.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class StResourceTest extends AbstractTemplateResourceTest {

	@Test
	void "load ST template"() {
		super."load template"()
	}

	@Test
	void "serialize resource"() {
		super."serialize resource"()
	}

	@Test
	void "load ST template with same data"() {
		super."load template with same data"()
	}

	def getTemplatesModule() {
		new TemplatesResourcesModule()
	}

	def getTemplateWorkerModule() {
		new STWorkerModule()
	}

	def getTemplatesMapModule() {
		new TemplatesDefaultMapsModule()
	}

	@Override
	def getPropertiesModule() {
		new STDefaultPropertiesModule()
	}

	@Override
	def createFactory() {
		injector.getInstance TemplatesFactory
	}

	@Override
	def createResourceFactory() {
		injector.getInstance TemplateResourceFactory
	}

	@Override
	def getTemplateUrl() {
		StResourceTest.class.getResource("testtemplate.stg")
	}

	@Override
	def getTemplateProperties() {
		propertiesModule.getSTDefaultProperties()
	}
}
