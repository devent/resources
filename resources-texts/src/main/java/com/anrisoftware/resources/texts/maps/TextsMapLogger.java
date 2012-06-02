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

	void imageAlreadyInMap(TextsMapImpl map, TextResource text) {
		log.warn("The text {} is already in the map {}.", text, map);
	}

	void checkHaveText(TextsMapImpl map, TextResource text, String name) {
		if (text == null) {
			log.warn("No text with the name ``{}'' in the map found.", name);
		}
	}
}
