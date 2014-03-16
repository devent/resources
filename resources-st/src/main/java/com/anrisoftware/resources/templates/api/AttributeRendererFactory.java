/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-st.
 *
 * resources-st is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-st is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-st. If not, see <http://www.gnu.org/licenses/>.
 */
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
