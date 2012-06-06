package com.anrisoftware.resources.binary.resources;

import com.anrisoftware.resources.api.BinaryResource;
import com.anrisoftware.resources.api.BinaryResourceFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the binary resource implementation.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class BinaryResourceModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(BinaryResource.class,
				BinaryResourceImpl.class).build(BinaryResourceFactory.class));
	}

}
