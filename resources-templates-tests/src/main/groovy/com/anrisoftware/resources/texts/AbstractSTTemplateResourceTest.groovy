package com.anrisoftware.resources.texts

import com.anrisoftware.resources.api.TemplateResource
import com.anrisoftware.resources.api.Templates
import com.anrisoftware.resources.api.TextsFactory

/**
 * Test for functionality of the template resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractSTTemplateResourceTest extends AbstractTemplateResourceTestUtils {

	void "load plain text with defined locale"() {
		def args = ["one", "two"]
		def baseName = "StTemplates"
		def classLoader = getClass().classLoader
		Templates templates = factory.create baseName, classLoader

		TemplateResource template = templates.getResource "test"
		assertStringContent template.getText("arg1", args[0], "arg2", args[1])
		assert template.locale.toString() == ""
		assert template.URL.toString() =~ "com/anrisoftware/resources/st/testtemplate.stg"
	}
}
