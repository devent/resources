package com.anrisoftware.resources;

import com.anrisoftware.resources.api.Icons;
import com.anrisoftware.resources.images.ResourcesImagesModule;
import com.google.inject.AbstractModule;

public class ResourcesModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Icons.class).to(IconsImpl.class);
		install(new ResourcesImagesModule());
	}

}
