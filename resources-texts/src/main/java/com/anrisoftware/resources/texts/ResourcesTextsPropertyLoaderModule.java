package com.anrisoftware.resources.texts;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.inject.Named;

import com.google.inject.Provides;

/**
 * Loads the texts resources properties from a URL and binds them.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public abstract class ResourcesTextsPropertyLoaderModule extends
		ResourcesTextsModule {

	@Provides
	@Named("texts-properties")
	Properties getTextsProperties() throws IOException {
		Properties properties = new Properties();
		properties.load(getTextsPropertiesURL().openStream());
		return properties;
	}

	protected abstract URL getTextsPropertiesURL();
}
