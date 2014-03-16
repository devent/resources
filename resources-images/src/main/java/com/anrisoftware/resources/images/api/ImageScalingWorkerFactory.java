/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
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

/**
 * Factory to create a new image scaling worker.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface ImageScalingWorkerFactory {

	/**
	 * Creates a new image scaling worker.
	 * 
	 * @param image
	 *            the {@link Image} to scale.
	 * 
	 * @param sizePx
	 *            the new {@link Dimension} width and height of the image in
	 *            pixels.
	 * 
	 * @return the {@link ImageScalingWorker}.
	 */
	ImageScalingWorker create(Image image, Dimension sizePx);
}
