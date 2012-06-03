package com.anrisoftware.resources.images.resource;

import com.anrisoftware.resources.api.ImageResource;
import com.anrisoftware.resources.api.ImageResourceFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the image resource implementation.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public class ResourcesImagesResourceModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(ImageResource.class,
				ImageResourceImpl.class).build(ImageResourceFactory.class));
	}

}
