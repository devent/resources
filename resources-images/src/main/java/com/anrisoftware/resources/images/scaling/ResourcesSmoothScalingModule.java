package com.anrisoftware.resources.images.scaling;

import com.anrisoftware.resources.api.ImageScalingWorker;
import com.anrisoftware.resources.api.ImageScalingWorkerFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the smooth scaling implementation.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public class ResourcesSmoothScalingModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(ImageScalingWorker.class,
				SmoothImageScalingWorker.class).build(
				ImageScalingWorkerFactory.class));
	}

}
