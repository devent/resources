/*
 * Copyright 2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.anrisoftware.resources.images.external;

import java.awt.Dimension;

import com.anrisoftware.resources.api.external.ResourcesException;

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
