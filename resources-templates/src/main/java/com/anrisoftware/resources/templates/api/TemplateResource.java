/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-templates.
 *
 * resources-templates is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-templates is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-templates. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.templates.api;

import java.io.Serializable;
import java.util.Map;
import java.util.Properties;

import com.anrisoftware.resources.api.Resource;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Template resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 * @see TemplateResourceFactory
 */
public interface TemplateResource extends Resource {

	/**
	 * Process the template and returns the text.
	 * <p>
	 * The processed text is cached and is returned for the same data.
	 * 
	 * @param data
	 *            the data for the template.
	 * 
	 * @throws ResourcesException
	 *             if there was an error loading the text.
	 */
	String getText(Object... data) throws ResourcesException;

	/**
	 * Process the template and returns the text.
	 * <p>
	 * The processed text is cached and is returned for the same data.
	 * 
	 * @param invalidate
	 *            set to {@code true} to invalidate the cache before processing
	 *            the text.
	 * 
	 * @param data
	 *            the data for the template.
	 * 
	 * @throws ResourcesException
	 *             if there was an error loading the text.
	 * 
	 * @since 1.1
	 */
	String getText(boolean invalidate, Object... data)
			throws ResourcesException;

	/**
	 * Invalidate the template resource. Delete the cached resource so a
	 * subsequent call of {@link #getText(Object...)} will process the template.
	 * <p>
	 * Use this if the data for the template have changed.
	 */
	void invalidate();

	/**
	 * Returns the properties of the template resource.
	 * 
	 * @return the {@link Properties}.
	 */
	Properties getProperties();

	/**
	 * Returns the attributes for the template engine.
	 * 
	 * @return the attributes {@link Map}.
	 * 
	 * @since 1.4
	 */
	Map<Serializable, Serializable> getAttributes();
}
