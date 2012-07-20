package com.anrisoftware.resources.st.worker;

import com.anrisoftware.resources.api.ResourcesException;

/**
 * Process the calculated data and create a string for output.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface TemplateWorker {

	/**
	 * Process the calculated data.
	 * 
	 * @param name
	 *            the name of the data.
	 * 
	 * @param data
	 *            the data.
	 * 
	 * @return the processed data as a string.
	 * 
	 * @throws ResourcesException
	 *             if an error has occurred while.
	 */
	String process(String name, Object... data) throws ResourcesException;

}
