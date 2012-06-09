package com.anrisoftware.resources;

import static java.util.ResourceBundle.getBundle;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Returns a resource bundle for the given base name and the caller's class
 * loader.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class GetBundle {

	private final String baseName;

	/**
	 * Sets the resource bundle base name.
	 * 
	 * @param baseName
	 *            the base name {@link String}.
	 */
	public GetBundle(String baseName) {
		this.baseName = baseName;
	}

	/**
	 * Returns the resource bundle for the specified locale.
	 * 
	 * @param locale
	 *            the {@link Locale}.
	 * 
	 * @return the {@link ResourceBundle}.
	 */
	public ResourceBundle bundleFor(Locale locale) {
		return getBundle(baseName, locale);
	}

	/**
	 * Returns the resource bundle base name.
	 * 
	 * @return the bundle base name {@link String}.
	 */
	public String getBaseName() {
		return baseName;
	}

	/**
	 * Returns the resource bundle class loaders.
	 * 
	 * @return the bundle {@link ClassLoader}, or <code>null</code> if no class
	 *         loader was set.
	 */
	public ClassLoader getClassLoader() {
		return null;
	}

	/**
	 * Returns the resource bundle control.
	 * 
	 * @return the bundle {@link ResourceBundle.Control}, or <code>null</code>
	 *         if no control was set.
	 */
	public ResourceBundle.Control getControl() {
		return null;
	}

}
