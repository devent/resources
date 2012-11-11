package com.anrisoftware.resources.templates.maps;

import com.anrisoftware.resources.templates.api.BundlesMap;
import com.anrisoftware.resources.templates.api.TemplatesMap;
import com.anrisoftware.resources.templates.api.TemplatesMapFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the Java hash map as the template resources maps.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class TemplatesDefaultMapsModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(BundlesMap.class).to(BundlesMapImpl.class);
		install(new FactoryModuleBuilder().implement(TemplatesMap.class,
				TemplatesMapImpl.class).build(TemplatesMapFactory.class));
	}

}
