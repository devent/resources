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

import java.awt.Dimension;
import java.awt.Image;

/**
 * Image resource with lazy loading.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface ImageResource extends BinaryResource {

	/**
	 * Returns the {@link ImageResolution}.
	 */
	ImageResolution getResolution();

	/**
	 * Returns the {@link Image} of the resource. It will lazy load the
	 * resource.
	 * 
	 * @throws ResourcesException
	 *             if the loading of the resource is failed.
	 */
	Image getImage() throws ResourcesException;

	/**
	 * Returns the width of the image. The method will block until the width is
	 * available from the image.
	 * 
	 * @throws ResourcesException
	 *             if the loading of the resource is failed.
	 */
	int getWidth() throws ResourcesException;

	/**
	 * Returns the height of the image. The method will block until the height
	 * is available from the image.
	 * 
	 * @throws ResourcesException
	 *             if the loading of the resource is failed.
	 */
	int getHeight() throws ResourcesException;

	/**
	 * Returns the width and height of the image. The method will block until
	 * the height is available from the image.
	 * 
	 * @return the {@link Dimension} containing the width and height.
	 * 
	 * @throws ResourcesException
	 *             if the loading of the resource is failed.
	 * 
	 * @since 1.1
	 */
	Dimension getSize() throws ResourcesException;

}
