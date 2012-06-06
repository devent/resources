package com.anrisoftware.resources.binary.maps;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.BinaryResource;

/**
 * Logger messages for the {@link DefaultBinariesMap}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class BinariesMapLogger extends AbstractLogger {

	BinariesMapLogger() {
		super(DefaultBinariesMap.class);
	}

	void imageAlreadyInMap(BinaryResource res) {
		log.warn("The binary resource {} is already in the map.", res);
	}

}
