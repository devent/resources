package com.anrisoftware.resources.images.images;

import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.images.api.ImagesFactory;
import com.anrisoftware.resources.images.resource.ResourcesImagesResourceModule;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the image resources factory.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class ResourcesImagesModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new ResourcesImagesResourceModule());
		install(new FactoryModuleBuilder().implement(Images.class,
				ImagesImpl.class).build(ImagesFactory.class));
		install(new FactoryModuleBuilder().implement(ImagesWorker.class,
				ImagesWorker.class).build(ImagesWorkerFactory.class));
	}

}
