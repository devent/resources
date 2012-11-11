package com.anrisoftware.resources.templates;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.templates.api.TemplateResource;

/**
 * Logging messages for {@link StImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class StImplLogger extends AbstractLogger {

	/**
	 * Creates a logger for {@link StImpl}.
	 */
	StImplLogger() {
		super(StImpl.class);
	}

	void checkTemplateLoaded(boolean haveTemplate, ResourceBundle bundle,
			String name) throws ResourcesException {
		if (!haveTemplate) {
			ResourcesException ex = new ResourcesException(bundle.getClass()
					.getName(), name, "No template resource loaded for ``%s''",
					name);
			log.error(ex.getMessage());
			throw ex;
		}
	}

	public URL checkResourceURL(URL url, String urlString) {
		if (url == null) {
			log.warn("The resource URL ``{}'' could not be found.", urlString);
		}
		return url;
	}

	void checkHaveResource(TemplateResource res, ResourceBundle bundle,
			String name, Locale locale) throws ResourcesException {
		if (res == null) {
			ResourcesException ex = new ResourcesException(bundle.getClass()
					.getName(), name,
					"No template resource ``%s'' available for the locale %s",
					name, locale);
			log.error(ex.getMessage());
			throw ex;
		}
	}

	void loadedResourceBundle(String name, ResourceBundle bundle) {
		if (log.isDebugEnabled()) {
			log.debug(
					"Loaded the resource bundle {} for the text resource ``{}''.",
					bundleToString(bundle), name);
		}
	}

	private String bundleToString(ResourceBundle bundle) {
		return new ToStringBuilder(bundle).append("locale", bundle.getLocale())
				.toString();
	}

}
