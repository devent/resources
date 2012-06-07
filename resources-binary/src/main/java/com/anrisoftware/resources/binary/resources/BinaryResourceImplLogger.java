package com.anrisoftware.resources.binary.resources;

import java.io.IOException;

import com.anrisoftware.globalpom.log.AbstractSerializedLogger;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Logging messages for the {@link BinaryResourceImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class BinaryResourceImplLogger extends AbstractSerializedLogger {

	/**
	 * Creates the logger for {@link BinaryResourceImpl}.
	 */
	public BinaryResourceImplLogger() {
		super(BinaryResourceImpl.class);
	}

	ResourcesException errorOpenStream(IOException e, BinaryResourceImpl res) {
		ResourcesException ex = new ResourcesException(e, res.getName(),
				"Error opening the binary resource %s", res);
		log.error(e.getMessage());
		return ex;
	}

	ResourcesException errorReadStreamToBuffer(IOException e,
			BinaryResourceImpl res) {
		ResourcesException ex = new ResourcesException(e, res.getName(),
				"Error read the binary resource %s", res);
		log.error(e.getMessage());
		return ex;
	}

	void loadedBuffer(BinaryResourceImpl res) {
		log.debug("Loaded the binary buffer in resource {}.", res);
	}
}
