package com.anrisoftware.resources;

import com.anrisoftware.resources.icons.ResourcesIconsModule;
import com.anrisoftware.resources.images.ResourcesImagesModule;
import com.anrisoftware.resources.texts.ResourcesTextsModule;
import com.google.inject.AbstractModule;

public class ResourcesModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new ResourcesImagesModule());
		install(new ResourcesIconsModule());
		install(new ResourcesTextsModule());
	}

}
