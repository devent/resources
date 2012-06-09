package com.anrisoftware.resources.texts.maps;

import com.anrisoftware.resources.texts.api.BundlesMap;
import com.anrisoftware.resources.texts.api.TextsMap;
import com.anrisoftware.resources.texts.api.TextsMapFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * <p>
 * Binds the text resources texts maps.
 * </p>
 * <p>
 * This implementation is using Java hash map.
 * </p>
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
