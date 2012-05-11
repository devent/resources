package com.anrisoftware.resources.texts;

import java.net.URL;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Logging messages for {@link TextsImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TextsImplLogger extends AbstractLogger {

	/**
	 * Creates a logger for {@link TextsImpl}.
	 */
	TextsImplLogger() {
		super(TextsImpl.class);
	}

	void checkTextLoaded(boolean haveText, String name)
			throws ResourcesException {
		if (!haveText) {
			ResourcesException ex = new ResourcesException(
					"No text resource loaded for ``%s''", name);
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
}
