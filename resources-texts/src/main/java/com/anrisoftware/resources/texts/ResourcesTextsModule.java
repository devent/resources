package com.anrisoftware.resources.texts;

import com.anrisoftware.resources.api.TextResource;
import com.anrisoftware.resources.api.TextResourceFactory;
import com.anrisoftware.resources.api.Texts;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the text resources classes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class ResourcesTextsModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Texts.class).to(TextsImpl.class);
		install(new FactoryModuleBuilder().implement(TextResource.class,
				TextResourceImpl.class).build(TextResourceFactory.class));
	}

}