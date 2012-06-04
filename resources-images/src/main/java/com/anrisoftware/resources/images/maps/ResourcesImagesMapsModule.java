package com.anrisoftware.resources.images.maps;

import com.anrisoftware.resources.images.api.BundlesMap;
import com.anrisoftware.resources.images.api.ImagesMap;
import com.anrisoftware.resources.images.api.ImagesMapFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * <p>
 * Binds the image resources images maps.
 * </p>
 * <p>
 * This implementation is using Java hash map.
 * </p>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public class ResourcesImagesMapsModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(BundlesMap.class).to(BundlesMapImpl.class);
		install(new FactoryModuleBuilder().implement(ImagesMap.class,
				ImagesMapImpl.class).build(ImagesMapFactory.class));
	}

}
