package com.anrisoftware.resources.binary.resources

import org.junit.Before

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.resources.api.BinaryResource
import com.anrisoftware.resources.api.BinaryResourceFactory
import com.google.common.io.Resources
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Creates the environment to test the serialization of a binary resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractSerializeBinaryResourceTestUtil extends TestUtils {

	def modules

	Injector injector

	BinaryResourceFactory factory

	@Before
	void before() {
		modules = lazyCreateModules()
		injector = injector == null ? Guice.createInjector(modules) : injector
		factory = injector.getInstance BinaryResourceFactory
	}

	def lazyCreateModules() {
		modules == null ?
				[resourcesModule,].flatten()
				: modules
	}

	/**
	 * Returns the binaries resources module.
	 */
	abstract def getResourcesModule()

	void "test serialize binary resource"() {
		def name = "lorem"
		def locale = Locale.ENGLISH
		def url = Resources.getResource("com/anrisoftware/resources/binary/zipfiles/Lorem ipsum.html.zip")
		def resource = factory.create name, locale, url
		assert resource.binary.size() == 71573

		BinaryResource resourceB = reserialize resource
		assert resourceB.binary.size() == 71573
	}
}
