package com.anrisoftware.resources.templates;

import com.anrisoftware.globalpom.log.AbstractSerializedLogger;

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

	void processTemplate(StResourceImpl resource) {
		log.trace("Process the template for {}.", resource);
	}

	void resourceInvalidated(StResourceImpl resource) {
		log.trace("Invalidate resource {}.", resource);
	}

}
