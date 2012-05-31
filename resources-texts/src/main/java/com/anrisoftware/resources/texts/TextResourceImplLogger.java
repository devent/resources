package com.anrisoftware.resources.texts;

import java.io.IOException;
import java.util.ResourceBundle;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Logging messages for {@link TextResourceImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TextResourceImplLogger extends AbstractLogger {

	/**
	 * Creates a logger for {@link TextResourceImpl}.
	 */
	TextResourceImplLogger() {
		super(TextResourceImpl.class);
	}

	ResourcesException errorLoadText(ResourceBundle bundle, String name,
			IOException e) {
		ResourcesException ex = new ResourcesException(bundle.getClass()
				.getName(), name, "Error loading the text %s: %s", name,
				e.getMessage());
		log.error(ex.getMessage());
		return ex;
	}
}
