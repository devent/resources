/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-binary.
 *
 * resources-binary is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-binary is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-binary. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.binary.resources;

import java.io.IOException;

import com.anrisoftware.globalpom.log.AbstractSerializedLogger;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Logging messages for the {@link BinaryResourceImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class BinaryResourceImplLogger extends AbstractSerializedLogger {

	/**
	 * Creates the logger for {@link BinaryResourceImpl}.
	 */
	public BinaryResourceImplLogger() {
		super(BinaryResourceImpl.class);
	}

	ResourcesException errorOpenStream(IOException e, BinaryResourceImpl res) {
		ResourcesException ex = new ResourcesException(e, res.getName(),
				"Error opening the binary resource %s", res);
		log.error(e.getMessage());
		return ex;
	}

	ResourcesException errorReadStreamToBuffer(IOException e,
			BinaryResourceImpl res) {
		ResourcesException ex = new ResourcesException(e, res.getName(),
				"Error read the binary resource %s", res);
		log.error(e.getMessage());
		return ex;
	}

	void loadedBuffer(BinaryResourceImpl res) {
		log.trace("Loaded the binary buffer in resource {}.", res);
	}
}
