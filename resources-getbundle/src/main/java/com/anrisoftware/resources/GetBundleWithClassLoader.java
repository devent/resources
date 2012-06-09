package com.anrisoftware.resources;

import static java.util.ResourceBundle.getBundle;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Returns the resource bundle with the specified base name and class loader.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class GetBundleWithClassLoader extends GetBundle {

	private final ClassLoader classLoader;

	/**
	 * Sets the resource bundle the base name and class loader.
	 * 
	 * @param baseName
	 *            the bundle base name {@link String}.
	 * 
	 * @param classLoader
	 *            the bundle {@link ClassLoader}.
	 */
	public GetBundleWithClassLoader(String baseName, ClassLoader classLoader) {
		super(baseName);
		this.classLoader = classLoader;
	}

	@Override
	public ResourceBundle bundleFor(Locale locale) {
		return getBundle(getBaseName(), locale, classLoader);
	}

	@Override
	public ClassLoader getClassLoader() {
		return classLoader;
	}
}
