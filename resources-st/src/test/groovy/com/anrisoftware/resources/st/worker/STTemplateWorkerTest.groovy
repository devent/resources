package com.anrisoftware.resources.st.worker

import org.junit.Test

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.propertiesutils.ContextPropertiesFactory
import com.google.inject.Guice
import com.google.inject.Injector

class STTemplateWorkerTest extends TestUtils {

	static Injector injector = Guice.createInjector(new STWorkerModule())

	static STTemplateWorkerFactory factory = injector.getInstance STTemplateWorkerFactory

	def templateName = "test"
	
	def template = resourceURLFromObject "testtemplate.stg"
	
	def templateProperties = TestUtils.resourceURL "com/anrisoftware/resources/st/worker/stringtemplate.properties"

	def properties = new ContextPropertiesFactory(this).fromResource templateProperties

	@Test
	void "process template"() {
		def args = ["one", "two"]
		def worker = factory.create template, properties
		def process = worker.process templateName, "arg1", args[0], "arg2", args[1]
		assert process == "${args[0]}:${args[1]}"
	}
}
