package com.anrisoftware.resources.binary;

import com.anrisoftware.resources.api.Binaries;
import com.anrisoftware.resources.api.BinariesFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the binary resources classes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class BinariesResourcesModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(Binaries.class,
				BinariesImpl.class).build(BinariesFactory.class));
		install(new FactoryModuleBuilder().implement(BinariesWorker.class,
				BinariesWorker.class).build(BinariesWorkerFactory.class));
	}

}
