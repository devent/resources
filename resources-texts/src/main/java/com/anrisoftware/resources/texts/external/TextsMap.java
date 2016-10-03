/*
 * Copyright 2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.anrisoftware.resources.texts.external;

/**
 * Puts text resources and retrieves them. The text resources are identified by
 * their name and locale. Duplicate entries are discarded.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public interface TextsMap {

	/**
	 * Adds a new text resource to the map. Already added text resource with the
	 * name are discarded.
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @param text
	 *            the {@link TextResource} to add.
	 */
	void putText(String name, TextResource text);

	/**
	 * Returns the text resource with the name.
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @return the {@link TextResource} with the name and language, the resource
	 *         with the default language, or <code>null</code>.
	 */
	TextResource getText(String name);

	/**
	 * Check if the map contains a text with the name.
	 * 
	 * @param name
	 *            the name of the text.
	 * 
	 * @return <code>true</code> if the map contains the text or
	 *         <code>false</code> if not.
	 */
	boolean haveText(String name);

}
