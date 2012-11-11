package com.anrisoftware.resources.templates.maps;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link TemplatesMapImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TemplatesMapLogger extends AbstractLogger {

	/**
	 * Creates a logger for {@link TemplatesMapImpl}.
	 */
	TemplatesMapLogger() {
		super(TemplatesMapImpl.class);
	}

	void textAlreadyInMap(String name) {
		log.warn("We contain already the template resource ``{}''.", name);
	}

}
