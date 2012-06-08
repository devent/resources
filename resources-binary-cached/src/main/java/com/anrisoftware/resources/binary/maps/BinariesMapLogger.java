package com.anrisoftware.resources.binary.maps;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link BinariesMapImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.2
 */
class BinariesMapLogger extends AbstractLogger {

	/**
	 * Creates a logger for {@link BinariesMapImpl}.
	 */
	BinariesMapLogger() {
		super(BinariesMapImpl.class);
	}

	void textAlreadyInMap(String name) {
		log.warn("We contain already the text resource ``{}''.", name);
	}

}
