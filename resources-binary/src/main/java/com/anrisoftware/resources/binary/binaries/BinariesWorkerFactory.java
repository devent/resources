/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.binary.binaries;

import java.util.Locale;

import com.anrisoftware.resources.GetBundle;
import com.anrisoftware.resources.binary.api.BundlesMap;

/**
 * Factory to create a new binary resources worker.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
interface BinariesWorkerFactory {

	/**
	 * Creates a new {@link BinariesWorker}.
	 * 
	 * @param name
	 *            the {@link String} name of the resource we want to get.
	 * 
	 * @param locale
	 *            the {@link Locale} of the resource we want to get.
	 * 
	 * @param getBundle
	 *            the {@link GetBundle} that returns the resource bundle for the
	 *            locale.
	 * 
	 * @param bundles
	 *            the map of bundles with their binary resources maps.
	 */
	BinariesWorker create(String name, Locale locale, GetBundle getBundle,
			BundlesMap bundles);
}
