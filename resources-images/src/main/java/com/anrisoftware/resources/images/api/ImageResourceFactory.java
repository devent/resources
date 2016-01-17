/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.images.api;

import java.awt.Image;
import java.net.URL;
import java.util.Locale;

/**
 * Factory to create a new {@link ImageResource}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface ImageResourceFactory {

	/**
	 * Creates a new image resource that will load an image from the specified
	 * URL.
	 * 
	 * @param name
	 *            the name {@link String} of this resource.
	 * 
	 * @param locale
	 *            the {@link Locale} this text resource.
	 * 
	 * @param resolution
	 *            the {@link ImageResolution} of the image.
	 * 
	 * @param url
	 *            the resource {@link URL} of the image.
	 * 
	 * @since 1.1
	 */
	ImageResource create(String name, Locale locale,
			ImageResolution resolution, URL url);

	/**
	 * Creates a new image resource that have an already loaded image.
	 * 
	 * @param name
	 *            the name {@link String} of this resource.
	 * 
	 * @param locale
	 *            the {@link Locale} this text resource.
	 * 
	 * @param resolution
	 *            the {@link ImageResolution} of the image.
	 * 
	 * @param image
	 *            the {@link Image} of the resource.
	 * 
	 * @since 1.1
	 */
	ImageResource create(String name, Locale locale,
			ImageResolution resolution, Image image);
}
