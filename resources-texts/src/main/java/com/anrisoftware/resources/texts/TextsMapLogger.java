package com.anrisoftware.resources.texts;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.TextResource;

/**
 * Logging messages for {@link TextsMap}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TextsMapLogger extends AbstractLogger {

	/**
	 * Creates a logger for {@link TextsMap}.
	 */
	TextsMapLogger() {
		super(TextsMap.class);
	}

	void imageAlreadyInMap(TextsMap map, TextResource text) {
		log.warn("The text {} is already in the map {}.", text, map);
	}

	void checkHaveText(TextsMap map, TextResource text, String name) {
		if (text == null) {
			log.warn("No text with the name ``{}'' in the map found.", name);
		}
	}
}
