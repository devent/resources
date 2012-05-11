package com.anrisoftware.resources.texts;

import java.io.IOException;

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

	ResourcesException errorLoadText(TextResourceImpl text, IOException e) {
		ResourcesException ex = new ResourcesException(
				"Error loading the text %s", text);
		log.error(ex.getMessage());
		return ex;
	}
}
