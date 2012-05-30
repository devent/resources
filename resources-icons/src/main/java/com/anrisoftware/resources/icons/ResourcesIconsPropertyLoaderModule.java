package com.anrisoftware.resources.icons;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.inject.Named;

import com.google.inject.Provides;

/**
 * Loads the icons resources properties from a URL and binds them.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public abstract class ResourcesIconsPropertyLoaderModule extends
		ResourcesIconsModule {

	@Provides
	@Named("icons-properties")
	Properties getIconsProperties() throws IOException {
		Properties properties = new Properties();
		properties.load(getIconsPropertiesURL().openStream());
		return properties;
	}

	/**
	 * Returns the URL of the icons properties file.
	 * 
	 * @return the {@link URL} of the icons properties file.
	 */
	protected abstract URL getIconsPropertiesURL();
}
