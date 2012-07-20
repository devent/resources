package com.anrisoftware.resources.st.worker;

import static com.google.common.base.Preconditions.checkNotNull;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.compiler.STException;
import org.stringtemplate.v4.misc.STMessage;

import com.anrisoftware.globalpom.log.AbstractSerializedLogger;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Logging messages for {@link STTemplateWorker}.
 * 
 * @author Erwin Müller, erwin.mueller@deventm.org
 * @since 1.0
 */
class STTemplateWorkerLogger extends AbstractSerializedLogger {

	/**
	 * Creates a logger for {@link STTemplateWorker}.
	 */
	public STTemplateWorkerLogger() {
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
		log.debug("Processed the template ``{}''.", name);
	}

	void checkTemplateCreated(ST template, String name) {
		checkNotNull(template, "Could not load the template ``%s''.", name);
	}

}
