package com.anrisoftware.resources.images.images;

import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.images.api.ImagesFactory;
import com.anrisoftware.resources.images.resource.ImageResourceModule;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the image resources factory.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.3
 */
public class ImagesResourcesModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new ImageResourceModule());
		install(new FactoryModuleBuilder().implement(Images.class,
				ImagesImpl.class).build(ImagesFactory.class));
		install(new FactoryModuleBuilder().implement(ImagesWorker.class,
				ImagesWorker.class).build(ImagesWorkerFactory.class));
	}

}
