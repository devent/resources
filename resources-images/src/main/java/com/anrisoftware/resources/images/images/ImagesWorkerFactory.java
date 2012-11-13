/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-images.
 *
 * resources-images is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-images is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-images. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.images.images;

import java.awt.Dimension;
import java.util.Locale;

import com.anrisoftware.resources.GetBundle;
import com.anrisoftware.resources.images.api.BundlesMap;

/**
 * Factory to create a new images worker.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
interface ImagesWorkerFactory {

	/**
	 * Creates a new {@link ImagesWorker}.
	 * 
	 * @param name
	 *            the {@link String} name of the image resource we want to get.
	 * 
	 * @param locale
	 *            the {@link Locale} of the image resource we want to get.
	 * 
	 * @param size
	 *            the {@link Dimension} width and height of the image resource
	 *            we want to get.
	 * 
	 * @param getBundle
	 *            the {@link GetBundle} that returns the resource bundle for the
	 *            locale.
	 * 
	 * @param bundles
	 *            the map of bundles with their images maps.
	 */
	ImagesWorker create(String name, Locale locale, Dimension size,
			GetBundle getBundle, BundlesMap bundles);
}
