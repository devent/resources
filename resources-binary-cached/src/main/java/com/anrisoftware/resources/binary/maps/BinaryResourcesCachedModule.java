package com.anrisoftware.resources.binary.maps;

import com.anrisoftware.resources.binary.api.BinariesMap;
import com.anrisoftware.resources.binary.api.BinariesMapFactory;
import com.anrisoftware.resources.binary.api.BundlesMap;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * <p>
 * Binds the binary resources maps.
 * </p>
 * <p>
 * This implementation is using a JSR107 cache.
 * </p>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class BinaryResourcesCachedModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(BundlesMap.class).to(BundlesMapImpl.class);
		install(new FactoryModuleBuilder().implement(BinariesMap.class,
				BinariesMapImpl.class).build(BinariesMapFactory.class));
	}

}
