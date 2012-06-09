package com.anrisoftware.resources.binary.maps;

import com.anrisoftware.resources.binary.api.BinariesMap;
import com.anrisoftware.resources.binary.api.BinariesMapFactory;
import com.anrisoftware.resources.binary.api.BundlesMap;
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
 * @since 1.0
 */
public class BinariesDefaultMapsModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(BundlesMap.class).to(DefaultBundlesMap.class);
		install(new FactoryModuleBuilder().implement(BinariesMap.class,
				DefaultBinariesMap.class).build(BinariesMapFactory.class));
	}

}
