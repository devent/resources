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
 * Factory to create a new {@link ImageScalingWorker}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface ImageScalingWorkerFactory {

	/**
	 * Creates a new {@link ImageScalingWorker}. The worker
	 * 
	 * @param image
	 *            the {@link Image} to scale.
	 * 
	 * @param size
	 *            the new {@link Dimension} of the image.
	 */
	ImageScalingWorker create(Image image, Dimension size);
}
