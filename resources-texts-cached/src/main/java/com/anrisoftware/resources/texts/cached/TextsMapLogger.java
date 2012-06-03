package com.anrisoftware.resources.texts.cached;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link TextsMapImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.2
 */
class TextsMapLogger extends AbstractLogger {

	/**
	 * Creates a logger for {@link TextsMapImpl}.
	 */
	TextsMapLogger() {
		super(TextsMapImpl.class);
	}

	void textAlreadyInMap(String name) {
		log.warn("We contain already the text resource ``{}''.", name);
	}

}
