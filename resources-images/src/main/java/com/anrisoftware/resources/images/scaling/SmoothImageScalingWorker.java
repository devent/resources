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
package com.anrisoftware.resources.images.scaling;

import static java.awt.Image.SCALE_SMOOTH;

import java.awt.Dimension;
import java.awt.Image;

import javax.inject.Inject;

import com.anrisoftware.resources.images.api.ImageScalingWorker;
import com.google.inject.assistedinject.Assisted;

/**
 * Scales an image to a new size using the {@link Image#SCALE_SMOOTH} method.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class SmoothImageScalingWorker implements ImageScalingWorker {

	private final Dimension size;

	private final Image image;

	@Inject
	SmoothImageScalingWorker(@Assisted Image image, @Assisted Dimension size) {
		this.image = image;
		this.size = size;
	}

	@Override
	public Image call() throws Exception {
		int width = size.width;
		int height = size.height;
		return image.getScaledInstance(width, height, SCALE_SMOOTH);
	}
}
