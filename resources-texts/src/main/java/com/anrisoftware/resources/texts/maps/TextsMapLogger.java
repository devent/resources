package com.anrisoftware.resources.texts.maps;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.TextResource;

/**
 * Logging messages for {@link TextsMapImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TextsMapLogger extends AbstractLogger {

	/**
	 * Creates a logger for {@link TextsMapImpl}.
	 */
	TextsMapLogger() {
		super(TextsMapImpl.class);
	}

	void textAlreadyInMap(String name) {
		log.warn("We contain already the text ``{}''.", name);
	}

	void checkHaveText(TextResource text, String name) {
		if (text == null) {
			log.warn("No text with the name ``{}'' in the map found.", name);
		}
	}

}
