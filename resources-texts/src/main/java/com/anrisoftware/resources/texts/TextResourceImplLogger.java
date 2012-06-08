package com.anrisoftware.resources.texts;

import java.io.IOException;

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

	ResourcesException errorLoadText(TextResourceImpl res, IOException e) {
		ResourcesException ex = new ResourcesException(
				"",
				res.getName(),
				"Error loading the text resource ``%s'' with the locale %s: %s",
				res.getName(), res.getLocale(), e.getMessage());
		log.error(ex.getMessage());
		return ex;
	}

}
