/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
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

import com.anrisoftware.resources.api.ResourcesException;

/**
 * Puts image resources and retrieve them. The images are identified by their
 * name, resolution and size. Image resources with the name, resolution and size
 * that are already in the map are not added.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public interface ImagesMap {

    /**
     * Add new image to the map.
     * <p>
     * It will be only one image with the same name, size and resolution in the
     * map. That means that if an image resource with the same name, size and
     * resolution is already in the map, the image resource will not be added.
     *
     * @param image
     *            the {@link ImageResource} that should be added.
     */
    void putImage(ImageResource image) throws ResourcesException;

    /**
     * Returns the image from the map. The image with the best resolution and
     * nearest size will be returned.
     *
     * @param name
     *            the name of the image.
     *
     * @param sizePx
     *            the {@link Dimension} with the width and height of the image
     *            in pixels.
     *
     * @return the {@link ImageResource}, the image with the best resolution and
     *         the nearest size or <code>null</code> if no such image was found
     *         in the map.
     */
    ImageResource getImage(String name, Dimension sizePx);

    /**
     * Returns the image from the map.
     *
     * @param name
     *            the name of the image.
     *
     * @param sizePx
     *            the {@link Dimension} with the width and height of the image
     *            in pixels.
     *
     * @param resolution
     *            the {@link ImageResolution} of the image.
     *
     * @return the {@link ImageResource}, the nearest image to the size give or
     *         <code>null</code> if no such image was found in the map.
     */
    ImageResource getImage(String name, Dimension sizePx,
            ImageResolution resolution);

    /**
     * Check if the image with the given name.
     *
     * @param name
     *            the name of the image.
     *
     * @return <code>true</code> if the image is in the map.
     */
    boolean haveImage(String name);

    /**
     * Check if the image with the given name and resolution.
     *
     * @param name
     *            the name of the image.
     *
     * @param resolution
     *            the {@link ImageResolution} of the image.
     *
     * @return <code>true</code> if the image is in the map.
     */
    boolean haveImage(String name, ImageResolution resolution);

    /**
     * Check if the image with the given name, resolution and size is in the
     * map.
     *
     * @param name
     *            the name of the image.
     *
     * @param resolution
     *            the {@link ImageResolution} of the image.
     *
     * @param size
     *            the {@link Dimension} size of the image.
     *
     * @return <code>true</code> if the image is in the map.
     *
     * @since 1.17
     */
    boolean haveImage(String name, ImageResolution resolution, Dimension size);

}
