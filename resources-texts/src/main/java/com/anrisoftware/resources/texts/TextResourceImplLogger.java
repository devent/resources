package com.anrisoftware.resources.texts;

import java.io.IOException;
import java.util.Locale;

import com.anrisoftware.globalpom.log.AbstractSerializedLogger;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Logging messages for {@link TextResourceImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TextResourceImplLogger extends AbstractSerializedLogger {

	/**
	 * Creates a logger for {@link TextResourceImpl}.
	 * 
	 * @deprecated public scope needed for serialization.
	 * @since 1.2
	 */
	@Deprecated
	public TextResourceImplLogger() {
		super(TextResourceImpl.class);
	}

	ResourcesException errorLoadText(String name, Locale locale, IOException e) {
		ResourcesException ex = new ResourcesException(
				"",
				name,
				"Error loading the text resource ``%s'' with the locale %s: %s",
				name, locale, e.getMessage());
		log.error(ex.getMessage());
		return ex;
	}
}
