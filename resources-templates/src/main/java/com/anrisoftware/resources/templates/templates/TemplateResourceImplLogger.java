package com.anrisoftware.resources.templates.templates;

import com.anrisoftware.globalpom.log.AbstractSerializedLogger;

/**
 * Logging messages for {@link TemplateResourceImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TemplateResourceImplLogger extends AbstractSerializedLogger {

	/**
	 * Creates a logger for {@link TemplateResourceImpl}.
	 * 
	 * @deprecated public scope needed for serialization.
	 */
	@Deprecated
	public TemplateResourceImplLogger() {
		super(TemplateResourceImpl.class);
	}

	void processTemplate(TemplateResourceImpl resource) {
		log.trace("Process the template for {}.", resource);
	}

	void resourceInvalidated(TemplateResourceImpl resource) {
		log.trace("Invalidate resource {}.", resource);
	}

}
