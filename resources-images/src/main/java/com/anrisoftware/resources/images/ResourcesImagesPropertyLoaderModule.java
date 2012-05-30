package com.anrisoftware.resources.images;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.inject.Named;

import com.google.inject.Provides;

/**
 * Loads the images resources properties from a URL and binds them.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public abstract class ResourcesImagesPropertyLoaderModule extends
		ResourcesImagesModule {

	@Provides
	@Named("images-properties")
	Properties getTextsProperties() throws IOException {
		Properties properties = new Properties();
		properties.load(getImagesPropertiesURL().openStream());
		return properties;
	}

	protected abstract URL getImagesPropertiesURL();
}
