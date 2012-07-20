package com.anrisoftware.resources.templates

import com.anrisoftware.propertiesutils.ContextPropertiesFactory
import com.anrisoftware.resources.api.TemplateResource
import com.anrisoftware.resources.api.TemplateResourceFactory
import com.google.common.io.Resources
import com.google.inject.Injector

/**
 * Test for serialization of the template resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractSerializationTest extends AbstractTemplateResourceTestUtils {

	void "serialize resource"() {
		TemplateResourceFactory factory = injector.getInstance TemplateResourceFactory
		String[] args = ["arg1", "one", "arg2", "two"]
		def name = "test"
		def locale = Locale.ENGLISH
		def url = Resources.getResource "com/anrisoftware/resources/st/testtemplate.stg"

		def resource = factory.create name, locale, url, defaultProperties
		TemplateResource resourceB = reserialize resource

		assertStringContent resourceB.getText(args), "${args[1]}:${args[3]}"
		assert resourceB.name == name
		assert resourceB.locale == locale
		assert resourceB.URL == url
		assert resourceB.properties == defaultProperties
	}

	Properties getDefaultProperties() {
		String context = "com.anrisoftware.resources.st.worker"
		new ContextPropertiesFactory(context).fromResource(ST_PROPERTIES_URL)
	}
}
