package com.anrisoftware.resources.texts.maps;

import com.anrisoftware.resources.texts.api.BundlesMap;
import com.anrisoftware.resources.texts.api.TextsMap;
import com.anrisoftware.resources.texts.api.TextsMapFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the text resources that is using Java hash map for the texts maps.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public class TextsDefaultMapsModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(BundlesMap.class).to(BundlesMapImpl.class);
		install(new FactoryModuleBuilder().implement(TextsMap.class,
				TextsMapImpl.class).build(TextsMapFactory.class));
	}

}
