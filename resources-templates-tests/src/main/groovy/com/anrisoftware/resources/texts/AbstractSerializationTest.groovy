package com.anrisoftware.resources.texts

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

	void "serialize text resource"() {
		TemplateResourceFactory factory = injector.getInstance TemplateResourceFactory
		def name = "Template Resource A"
		def locale = Locale.ENGLISH
		def url = Resources.getResource "com/anrisoftware/resources/st/testtemplate.stg"

		def resource = factory.create name, locale, url, properties
		TemplateResource resourceB = reserialize resource

		assert resourceB.name == name
		assert resourceB.locale == locale
		assert resourceB.URL == url
		assert resourceB.properties == charset
	}
}
