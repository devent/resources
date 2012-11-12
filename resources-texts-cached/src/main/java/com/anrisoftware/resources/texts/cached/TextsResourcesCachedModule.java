package com.anrisoftware.resources.texts.cached;

import com.anrisoftware.resources.texts.api.BundlesMap;
import com.anrisoftware.resources.texts.api.TextsMap;
import com.anrisoftware.resources.texts.api.TextsMapFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the text resources texts maps using a JSR107 (JCache) cache.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class TextsResourcesCachedModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(BundlesMap.class).to(BundlesMapImpl.class);
		install(new FactoryModuleBuilder().implement(TextsMap.class,
				TextsMapImpl.class).build(TextsMapFactory.class));
	}

}
