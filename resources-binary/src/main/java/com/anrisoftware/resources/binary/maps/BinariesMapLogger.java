/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.binary.maps;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.binary.api.BinaryResource;

/**
 * Logger messages for the {@link DefaultBinariesMap}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class BinariesMapLogger extends AbstractLogger {

	private static final String ALREADY = "Resource {} in map.";

	BinariesMapLogger() {
		super(DefaultBinariesMap.class);
	}

	void imageAlreadyInMap(BinaryResource res) {
		log.warn(ALREADY, res);
	}

}
