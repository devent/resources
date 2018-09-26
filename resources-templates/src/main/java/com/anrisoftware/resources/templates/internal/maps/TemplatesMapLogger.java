
package com.anrisoftware.resources.templates.internal.maps;

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

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link TemplatesMapImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TemplatesMapLogger extends AbstractLogger {

	private static final String CONTAINS_RESOURCE = "Map contains template resource '{}'.";

	/**
	 * Creates a logger for {@link TemplatesMapImpl}.
	 */
	TemplatesMapLogger() {
		super(TemplatesMapImpl.class);
	}

	void textAlreadyInMap(String name) {
		log.warn(CONTAINS_RESOURCE, name);
	}

}