package com.anrisoftware.resources.images;

import static java.util.ResourceBundle.getBundle;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Returns a resource bundle for the given base name and the caller's class
 * loader.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
class GetBundle {

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

/**
 * Returns the resource bundle with the specified base name and class loader.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class GetBundleWithClassLoader extends GetBundle {

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

/**
 * Returns the resource bundle with the specified base name and control.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class GetBundleWithControl extends GetBundle {

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

/**
 * Returns the resource bundle with the specified base name, class loader and
 * control.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class GetBundleWithClassLoaderAndControl extends GetBundleWithClassLoader {

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
