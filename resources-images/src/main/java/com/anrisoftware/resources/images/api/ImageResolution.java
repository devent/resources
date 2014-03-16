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

/**
 * The default resolutions for an image resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public enum ImageResolution {

	/**
	 * Low resolution.
	 */
	LOW("ldpi"),

	/**
	 * Medium resolution.
	 */
	MEDIUM("mdpi"),

	/**
	 * High resolution.
	 */
	HIGH("hdpi"),

	/**
	 * Very high resolution.
	 */
	EXTRA_HIGH("xhdpi");

	private final String name;

	private ImageResolution(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of this resolution.
	 * 
	 * @return the name.
	 */
	public String getName() {
		return name;
	}
}
