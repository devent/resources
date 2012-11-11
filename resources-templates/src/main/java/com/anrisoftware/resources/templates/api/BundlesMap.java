package com.anrisoftware.resources.templates.api;

import java.util.ResourceBundle;

/**
 * A map of template resources for each resource bundle.
 * <p>
 * Lazy create a new template resources map for a new resource bundle.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface BundlesMap {

	/**
	 * Returns the template resources for the specified resource bundle.
	 * <p>
	 * If no template resources are found for the specified resource bundle a
	 * new templates map is created.
	 * 
	 * @param bundle
	 *            the {@link ResourceBundle}.
	 * 
	 * @return the {@link TemplatesMap} for the resource bundle.
	 */
	TemplatesMap getTemplates(ResourceBundle bundle);

}
