package com.anrisoftware.resources;

import static java.util.ResourceBundle.getBundle;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Returns the resource bundle with the specified base name and control.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class GetBundleWithControl extends GetBundle {

	private final ResourceBundle.Control control;

	/**
	 * Sets the resource bundle the base name and class loader.
	 * 
	 * @param baseName
	 *            the bundle base name {@link String}.
	 * 
	 * @param control
	 *            the bundle {@link ResourceBundle.Control}.
	 */
	public GetBundleWithControl(String baseName, ResourceBundle.Control control) {
		super(baseName);
		this.control = control;
	}

	@Override
	public ResourceBundle bundleFor(Locale locale) {
		return getBundle(getBaseName(), locale, control);
	}

}
