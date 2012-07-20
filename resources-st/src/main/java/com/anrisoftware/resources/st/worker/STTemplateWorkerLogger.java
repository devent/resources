package com.anrisoftware.resources.st.worker;

import org.stringtemplate.v4.compiler.STException;
import org.stringtemplate.v4.misc.STMessage;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Logging messages for {@link STTemplateWorker}.
 * 
 * @author Erwin Müller, erwin.mueller@deventm.org
 * @since 1.0
 */
class STTemplateWorkerLogger extends AbstractLogger {

	/**
	 * Creates a logger for {@link STTemplateWorker}.
	 */
	STTemplateWorkerLogger() {
		super(STTemplateWorker.class);
	}

	ResourcesException errorOpenGroupFile(STException e) {
		ResourcesException ex = new ResourcesException(e, "", "",
				"Error open the string template group file");
		log.error(ex.getMessage());
		return ex;
	}

	ResourcesException errorProcessTemplate(STMessage errorMessage) {
		ResourcesException ex = new ResourcesException("", "",
				"Error process the template: %s", errorMessage);
		log.error(ex.getMessage());
		return ex;
	}

	void templateProcessed(String name) {
		log.debug("Processed the template {name}.");
	}

}
