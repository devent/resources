/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of resources-api.
 * 
 * resources-api is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * resources-api is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-api. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.api;

import java.awt.Image;
import java.net.URL;

/**
 * Factory to create a new {@link ImageResource}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface ImageResourceFactory {

	/**
	 * Creates a new {@link ImageResource} that will load an image from the
	 * resource URL.
	 * 
	 * @param url
	 *            the resource {@link URL} of the image.
	 * 
	 * @param resolution
	 *            the {@link ImageResolution} of the image.
	 */
	ImageResource create(URL url, ImageResolution resolution);

	/**
	 * Creates a new {@link ImageResource} that have an already loaded image.
	 * 
	 * @param image
	 *            the {@link Image} of the resource.
	 * 
	 * @param resolution
	 *            the {@link ImageResolution} of the image.
	 */
	ImageResource create(Image image, ImageResolution resolution);
}
