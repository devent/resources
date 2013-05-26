package com.anrisoftware.resources.templates.api;

import java.io.Serializable;

/**
 * Adds the attribute type.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.6
 */
public interface AttributeRenderer extends
		org.stringtemplate.v4.AttributeRenderer, Serializable {

	/**
	 * Returns the attribute type of the renderer.
	 * 
	 * @return the type {@link Class}.
	 */
	Class<?> getAttributeType();
}
