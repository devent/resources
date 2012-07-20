package com.anrisoftware.resources.st.maps;

import com.anrisoftware.resources.st.api.BundlesMap;
import com.anrisoftware.resources.st.api.TemplatesMap;
import com.anrisoftware.resources.st.api.TemplatesMapFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * <p>
 * Binds the template resources maps.
 * </p>
 * <p>
 * This implementation is using Java hash map.
 * </p>
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
