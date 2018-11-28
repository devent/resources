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

package com.anrisoftware.resources.texts.internal.texts;


import java.io.IOException;
import java.util.Locale;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.external.ResourcesException;

class TextResourceImplLogger extends AbstractLogger {

	private static final String LOCALE = "locale";
	private static final String NAME = "name";
	private static final String ERROR_LOAD_MESSAGE = "Error load text resource '{}' ({})";
	private static final String ERROR_LOAD = "Error load text resource";

	/**
	 * Creates a logger for {@link TextResourceImpl}.
	 * 
	 * @since 1.2
	 */
	public TextResourceImplLogger() {
		super(TextResourceImpl.class);
	}

	ResourcesException errorLoadText(TextResourceImpl res, IOException e) {
		String name = res.getName();
		Locale locale = res.getLocale();
		return logException(new ResourcesException(e, ERROR_LOAD, null, name)
				.addContext(NAME, name).addContext(LOCALE, locale),
				ERROR_LOAD_MESSAGE, name, locale);
	}
}
