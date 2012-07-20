package com.anrisoftware.resources.texts

import javax.inject.Named

import org.junit.Before

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.propertiesutils.ContextPropertiesFactory
import com.anrisoftware.resources.api.TextsFactory
import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Provides

/**
 * Creates the environment to unit test the template resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractTemplateResourceTestUtils extends TestUtils {

	def modules

	Injector injector

	TextsFactory factory

	@Before
	void before() {
		modules = lazyCreateModules()
		injector = injector == null ? Guice.createInjector(modules) : injector
		factory = injector.getInstance(TextsFactory)
	}

	/**
	 * Creates the needed modules if they are not already have been created.
	 */
	def lazyCreateModules() {
		modules == null ?
				[
					templatesModule,
					templatesMapModule,
					defaultPropertiesModule,
				].flatten()
				: modules
	}

	/**
	 * Returns the module that binds the template resources.
	 */
	abstract getTemplatesModule()

	/**
	 * Returns the module that binds the template resources maps. 
	 */
	abstract getTemplatesMapModule()

	/**
	 * Returns the module that binds the default template properties.
	 */
	def getDefaultPropertiesModule() {
		new AbstractModule() {
					@Override
					protected void configure() {
					}
					
					@Provides
					@Named("st-default-properties")
					Properties getSTDefaultProperties() throws IOException {
						new ContextPropertiesFactory(AbstractTemplateResourceTestUtils).fromResource("stringtemplate.properties")
					}
				}
	}

}
