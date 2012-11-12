package com.anrisoftware.resources.texts.texts;

import com.anrisoftware.resources.texts.api.TextResource;
import com.anrisoftware.resources.texts.api.TextResourceFactory;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the text resources classes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class TextsResourcesModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(Texts.class,
				TextsImpl.class).build(TextsFactory.class));
		install(new FactoryModuleBuilder().implement(TextResource.class,
				TextResourceImpl.class).build(TextResourceFactory.class));
	}

}