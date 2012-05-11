package com.anrisoftware.resources.images;

import com.anrisoftware.resources.api.ImageResource;
import com.anrisoftware.resources.api.ImageResourceFactory;
import com.anrisoftware.resources.api.Images;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the image resources classes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class ResourcesImagesModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Images.class).to(ImagesImpl.class);
		install(new FactoryModuleBuilder().implement(ImageResource.class,
				ImageResourceImpl.class).build(ImageResourceFactory.class));
	}

}
