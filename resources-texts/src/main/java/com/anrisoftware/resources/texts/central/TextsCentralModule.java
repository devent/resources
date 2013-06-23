package com.anrisoftware.resources.texts.central;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the texts central factory.
 * 
 * @see TextsResources
 * @see TextsResourcesFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.6
 */
public class TextsCentralModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(TextsResources.class,
				TextsResources.class).build(TextsResourcesFactory.class));
	}

}
