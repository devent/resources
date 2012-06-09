package com.anrisoftware.resources;

import static java.util.ResourceBundle.getBundle;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Returns the resource bundle with the specified base name, class loader and
 * control.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class GetBundleWithClassLoaderAndControl extends
		GetBundleWithClassLoader {

	private final ResourceBundle.Control control;

	/**
	 * Sets the resource bundle the base name and class loader.
	 * 
	 * @param baseName
	 *            the bundle base name {@link String}.
	 * 
	 * @param classLoader
	 *            the bundle {@link ClassLoader}.
	 * 
	 * @param control
	 *            the bundle {@link ResourceBundle.Control}.
	 */
	public GetBundleWithClassLoaderAndControl(String baseName,
			ClassLoader classLoader, ResourceBundle.Control control) {
		super(baseName, classLoader);
		this.control = control;
	}

	@Override
	public ResourceBundle bundleFor(Locale locale) {
		return getBundle(getBaseName(), locale, getClassLoader(), control);
	}

	@Override
	public ResourceBundle.Control getControl() {
		return control;
	}

}
