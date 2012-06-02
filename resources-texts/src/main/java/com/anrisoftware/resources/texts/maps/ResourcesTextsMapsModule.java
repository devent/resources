package com.anrisoftware.resources.texts.maps;

import com.anrisoftware.resources.texts.api.BundlesMap;
import com.anrisoftware.resources.texts.api.TextsMap;
import com.anrisoftware.resources.texts.api.TextsMapFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * <p>
 * Binds the text resources bundles and texts maps.
 * </p>
 * <p>
 * The implementations are using Java HashMap.
 * </p>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public class ResourcesTextsMapsModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(BundlesMap.class).to(BundlesMapImpl.class);
		install(new FactoryModuleBuilder().implement(TextsMap.class,
				TextsMapImpl.class).build(TextsMapFactory.class));
	}

}
