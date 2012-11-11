package com.anrisoftware.resources.templates.api;

import java.net.URL;
import java.util.Properties;

/**
 * Factory to create a new template worker.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface TemplateWorkerFactory {

	/**
	 * Creates a new template worker.
	 * 
	 * @param templateUrl
	 *            the {@link URL} of the template file.
	 * 
	 * @param properties
	 *            the {@link Properties} for the template.
	 * 
	 * @return the {@link TemplateWorker}.
	 */
	TemplateWorker create(URL templateUrl, Properties properties);
}
