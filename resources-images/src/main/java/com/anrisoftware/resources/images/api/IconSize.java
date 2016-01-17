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

/**
 * The default sizes for an image resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public enum IconSize {

	/**
	 * Size 16x16.
	 */
	SMALL(16),

	/**
	 * Size 22x22.
	 */
	MEDIUM(22),

	/**
	 * Size 32x32.
	 */
	LARGE(32),

	/**
	 * Size 48x48.
	 */
	HUGE(48);

	private final int sizePx;

	private IconSize(int sizePx) {
		this.sizePx = sizePx;
	}

	/**
	 * Returns the size in pixels.
	 * 
	 * @return the size in pixels.
	 * 
	 * @since 1.3
	 */
	public int getSizePx() {
		return sizePx;
	}
}
