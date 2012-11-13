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
package com.anrisoftware.resources.images.api;

import java.awt.Dimension;
import java.awt.Image;

import com.anrisoftware.resources.api.Resource;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Image resource with lazy loading.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface ImageResource extends Resource {

	/**
	 * Returns the resolution of the image resource.
	 * 
	 * @return the {@link ImageResolution}.
	 */
	ImageResolution getResolution();

	/**
	 * Returns the image of the resource. It will lazy load the resource.
	 * 
	 * @return the {@link Image}.
	 * 
	 * @throws ResourcesException
	 *             if the loading of the resource is failed.
	 */
	Image getImage() throws ResourcesException;

	/**
	 * Returns the width of the image. The method will block until the width is
	 * available from the image.
	 * 
	 * @return the width of the image in pixels.
	 * 
	 * @throws ResourcesException
	 *             if the loading of the resource is failed.
	 * 
	 * @since 1.3
	 */
	int getWidthPx() throws ResourcesException;

	/**
	 * Returns the height of the image. The method will block until the height
	 * is available from the image.
	 * 
	 * @return the height of the image in pixels.
	 * 
	 * @throws ResourcesException
	 *             if the loading of the resource is failed.
	 * 
	 * @since 1.3
	 */
	int getHeightPx() throws ResourcesException;

	/**
	 * Returns the width and height of the image. The method will block until
	 * the height is available from the image.
	 * 
	 * @return the {@link Dimension} containing the width and height of the
	 *         image in pixels.
	 * 
	 * @throws ResourcesException
	 *             if the loading of the resource is failed.
	 * 
	 * @since 1.3
	 */
	Dimension getSizePx() throws ResourcesException;

}
