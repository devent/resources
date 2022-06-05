/*
 * Copyright 2012-2022 Erwin Müller <erwin.mueller@anrisoftware.com>
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
package com.anrisoftware.resources.binary.internal.resources;


import java.io.IOException;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.external.ResourcesException;

class BinaryResourceImplLogger extends AbstractLogger {

	private static final String LOADED_BINARY_BUFFER = "Loaded binary buffer for {}.";
	private static final String ERROR_READ_MESSAGE = "Error read {}.";
	private static final String ERROR_READ = "Error read resource";
	private static final String ERROR_OPEN_MESSAGE = "Error open {}.";
	private static final String ERROR_OPEN = "Error open resource";

	/**
	 * Creates the logger for {@link BinaryResourceImpl}.
	 */
	public BinaryResourceImplLogger() {
		super(BinaryResourceImpl.class);
	}

	ResourcesException errorOpenStream(IOException e, BinaryResourceImpl res) {
		return logException(
				new ResourcesException(e, ERROR_OPEN, null, res.getName()).addContext(
						"resource", res), ERROR_OPEN_MESSAGE, res.getName());
	}

	ResourcesException errorReadStreamToBuffer(IOException e,
			BinaryResourceImpl res) {
		return logException(
				new ResourcesException(e, ERROR_READ, null, res.getName()).addContext(
						"resource", res), ERROR_READ_MESSAGE, res.getName());
	}

	void loadedBuffer(BinaryResourceImpl res) {
		log.trace(LOADED_BINARY_BUFFER, res);
	}
}
