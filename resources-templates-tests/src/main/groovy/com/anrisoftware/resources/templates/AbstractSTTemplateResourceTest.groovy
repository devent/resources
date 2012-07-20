package com.anrisoftware.resources.templates

import org.perf4j.LoggingStopWatch
import org.perf4j.StopWatch

import com.anrisoftware.resources.api.TemplateResource
import com.anrisoftware.resources.api.Templates

/**
 * Test for functionality of the template resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractSTTemplateResourceTest extends AbstractTemplateResourceTestUtils {

	void "load ST template"() {
		def args = ["one", "two"]
		def baseName = "StTemplates"
		def classLoader = getClass().classLoader
		Templates templates = factory.create baseName, classLoader

		TemplateResource template = templates.getResource "test"
		assertStringContent template.getText("arg1", args[0], "arg2", args[1]), "${args[0]}:${args[1]}"
		assert template.locale.toString() == ""
		assert template.URL.toString() =~ "com/anrisoftware/resources/st/testtemplate.stg"
	}

	void "load ST template with same data"() {
		String[] args = ["arg1", "one", "arg2", "two"]
		def baseName = "StTemplates"
		def classLoader = getClass().classLoader
		Templates templates = factory.create baseName, classLoader
		TemplateResource template
		
		template = templates.getResource "test"
		
		StopWatch stopWatch = new LoggingStopWatch("get text from template")
		stopWatch.lap("Load template")
		assertStringContent template.getText(args), "${args[1]}:${args[3]}"
		stopWatch.lap("First access")
		assertStringContent template.getText(args), "${args[1]}:${args[3]}"
		assertStringContent template.getText(args), "${args[1]}:${args[3]}"
		assertStringContent template.getText(args), "${args[1]}:${args[3]}"
		stopWatch.stop("Additional access with same data")
	}
}
