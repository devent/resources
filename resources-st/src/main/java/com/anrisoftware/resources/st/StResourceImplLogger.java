package com.anrisoftware.resources.st;

import java.io.IOException;

import com.anrisoftware.globalpom.log.AbstractSerializedLogger;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Logging messages for {@link StResourceImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class StResourceImplLogger extends AbstractSerializedLogger {

	/**
	 * Creates a logger for {@link StResourceImpl}.
	 * 
	 * @deprecated public scope needed for serialization.
	 * @since 1.2
	 */
	@Deprecated
	public StResourceImplLogger() {
		super(StResourceImpl.class);
	}

	ResourcesException errorLoadText(StResourceImpl res, IOException e) {
		ResourcesException ex = new ResourcesException(
				"",
				res.getName(),
				"Error loading the template resource ``%s'' with the locale %s: %s",
				res.getName(), res.getLocale(), e.getMessage());
		log.error(ex.getMessage());
		return ex;
	}

	void dataIsSame(StResourceImpl res, boolean same) {
		log.debug("The data is the same {} in the resource {}.", same, res);
	}

}
