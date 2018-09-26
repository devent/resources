
package com.anrisoftware.resources.templates.external;

/*-
 * #%L
 * Resources :: Templates
 * %%
 * Copyright (C) 2012 - 2018 Advanced Natural Research Institute
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.anrisoftware.resources.templates.external.TemplateResource;

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
