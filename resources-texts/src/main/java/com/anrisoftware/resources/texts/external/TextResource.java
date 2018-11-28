/**
 * Copyright © 2012 Erwin Müller (erwin.mueller@anrisoftware.com)
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


import java.nio.charset.Charset;
import java.util.Locale;

import com.anrisoftware.resources.api.external.Resource;
import com.anrisoftware.resources.api.external.ResourcesException;

public interface TextResource extends Resource {

	/**
	 * Returns the character set of this resource.
	 * 
	 * @return the {@link Charset}.
	 * 
	 * @since 1.1
	 */
	Charset getCharset();

	/**
	 * Returns the text {@link String} of the resource.
	 * 
	 * @throws ResourcesException
	 *             if there was an error loading the text.
	 */
	String getText() throws ResourcesException;

	/**
	 * Returns a formatted text of the resource.
	 * <p>
	 * The string is formatted as in
	 * {@link String#format(Locale, String, Object...)} with the current text as
	 * the format string and the current locale as the locale.
	 * 
	 * @param args
	 *            the arguments.
	 * 
	 * @throws ResourcesException
	 *             if there was an error loading the text.
	 * 
	 * @since 1.2
	 */
	String getFormattedText(Object... args) throws ResourcesException;

}
