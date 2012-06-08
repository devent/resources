package com.anrisoftware.resources.binary.maps;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link BundlesMapImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.2
 */
class BundlesMapLogger extends AbstractLogger {

	/**
	 * Creates a logger for {@link BundlesMapImpl}.
	 */
	BundlesMapLogger() {
		super(BinariesMapImpl.class);
	}

	void buildNewCache(String name) {
		log.debug("Build a new cache ``{}''.", name);
	}

}
