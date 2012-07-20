package com.anrisoftware.resources.st.api;

import com.anrisoftware.resources.api.TemplateResource;

/**
 * Puts template resources and retrieves them. The resources are identified by
 * their name and locale. Duplicate entries are discarded.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface TemplatesMap {

	/**
	 * Adds a new template resource to the map. Already added resources with the
	 * same name are discarded.
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @param text
	 *            the {@link TemplateResource} to add.
	 */
	void putTemplate(String name, TemplateResource text);

	/**
	 * Returns the template resource with the specified name.
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @return the {@link TemplateResource} with the specified name or
	 *         <code>null</code>.
	 */
	TemplateResource getTemplate(String name);

	/**
	 * Check if the map contains the template resource with the specified name.
	 * 
	 * @param name
	 *            the name of the template resource.
	 * 
	 * @return {@code true} if the map contains the resource or {@code false} if
	 *         not.
	 */
	boolean haveText(String name);

}
