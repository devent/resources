/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
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

import com.anrisoftware.resources.templates.api.TemplateResource;

/**
 * Puts template resources and retrieves them. The resources are identified by
 * their name and locale. Duplicate entries are discarded.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface TemplatesMap {

	/**
	 * Adds a new template resource to the map. Already added resources with the
	 * same name are discarded.
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @param text
	 *            the {@link TemplateResource} to add.
	 */
	void putTemplate(String name, TemplateResource text);

	/**
	 * Returns the template resource with the specified name.
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @return the {@link TemplateResource} with the specified name or
	 *         <code>null</code>.
	 */
	TemplateResource getTemplate(String name);

	/**
	 * Check if the map contains the template resource with the specified name.
	 * 
	 * @param name
	 *            the name of the template resource.
	 * 
	 * @return {@code true} if the map contains the resource or {@code false} if
	 *         not.
	 */
	boolean haveText(String name);

}
