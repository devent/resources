package com.anrisoftware.resources.templates.api;

/**
 * Factory to create a new template resources map.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface TemplatesMapFactory {

	/**
	 * Creates a new {@link TemplatesMap}.
	 */
	TemplatesMap create();
}
