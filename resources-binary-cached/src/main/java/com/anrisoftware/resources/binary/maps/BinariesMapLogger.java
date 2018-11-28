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
package com.anrisoftware.resources.binary.maps;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link BinariesMapImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.2
 */
class BinariesMapLogger extends AbstractLogger {

	/**
	 * Creates a logger for {@link BinariesMapImpl}.
	 */
	BinariesMapLogger() {
		super(BinariesMapImpl.class);
	}

	void textAlreadyInMap(String name) {
		log.warn("We contain already the text resource ``{}''.", name);
	}

}
