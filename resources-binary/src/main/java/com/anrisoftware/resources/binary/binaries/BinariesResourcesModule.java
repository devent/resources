package com.anrisoftware.resources.binary.binaries;

import com.anrisoftware.resources.binary.api.Binaries;
import com.anrisoftware.resources.binary.api.BinariesFactory;
import com.anrisoftware.resources.binary.resources.BinaryResourceModule;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the binary resources factories.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class BinariesResourcesModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new BinaryResourceModule());
		install(new FactoryModuleBuilder().implement(Binaries.class,
				BinariesImpl.class).build(BinariesFactory.class));
		install(new FactoryModuleBuilder().implement(BinariesWorker.class,
				BinariesWorker.class).build(BinariesWorkerFactory.class));
	}

}
