package com.anrisoftware.resources.templates.api;

import java.util.Locale;

/**
 * Factory to create a new attribute renderer.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.6
 */
public class AttributeRendererFactory {

	/**
	 * @see #wrap(Class, org.stringtemplate.v4.AttributeRenderer)
	 */
	static AttributeRenderer wrapAttributeRenderer(final Class<?> type,
			final org.stringtemplate.v4.AttributeRenderer renderer) {
		return wrap(type, renderer);
	}

	/**
	 * Wraps the ST attribute renderer with the attribute type.
	 * 
	 * @param type
	 *            the attribute type {@link Class}.
	 * 
	 * @param renderer
	 *            the {@link org.stringtemplate.v4.AttributeRenderer}.
	 * 
	 * @return the {@link AttributeRenderer}.
	 */
	@SuppressWarnings("serial")
	static AttributeRenderer wrap(final Class<?> type,
			final org.stringtemplate.v4.AttributeRenderer renderer) {
		return new AttributeRenderer() {

			@Override
			public String toString(Object o, String formatString, Locale locale) {
				return renderer.toString(o, formatString, locale);
			}

			@Override
			public Class<?> getAttributeType() {
				return type;
			}

		};
	}

}
