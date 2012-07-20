package com.anrisoftware.resources.st.api;

import java.util.ResourceBundle;

/**
 * <p>
 * A map of template resources for each resource bundle.
 * </p>
 * <p>
 * Lazy create a new template resources map for a new resource bundle.
 * </p>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface BundlesMap {

	/**
	 * <p>
	 * Returns the template resources for the specified resource bundle.
	 * </p>
	 * <p>
	 * If no template resources are found for the specified resource bundle a
	 * new {@link TemplatesMap} is created.
	 * </p>
	 * 
	 * @param bundle
	 *            the {@link ResourceBundle}.
	 * 
	 * @return the {@link TemplatesMap} for the resource bundle.
	 */
	TemplatesMap getTemplates(ResourceBundle bundle);

}
