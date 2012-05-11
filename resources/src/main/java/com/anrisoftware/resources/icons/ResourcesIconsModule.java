package com.anrisoftware.resources.icons;

import com.anrisoftware.resources.api.Icons;
import com.google.inject.AbstractModule;

/**
 * Binds the icons resources classes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class ResourcesIconsModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Icons.class).to(IconsImpl.class);
	}

}
