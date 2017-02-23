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
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import com.anrisoftware.resources.api.external.Resource;
import com.anrisoftware.resources.api.external.ResourcesException;

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
     * Returns the image of the resource. It will lazy load the resource.
     *
     * @param observer
     *            the {@link ImageObserver} that is notified when notifications
     *            about loaded image information.
     *
     * @return the {@link Image}.
     *
     * @throws ResourcesException
     *             if the loading of the resource is failed.
     *
     * @since 1.16
     */
    Image getImage(ImageObserver observer) throws ResourcesException;

    /**
     * Returns the buffered image of the resource. It will lazy load the
     * resource.
     *
     * @param imageType
     *            the image {@link Integer} type.
     *
     * @return the {@link BufferedImage}.
     *
     * @throws ResourcesException
     *             if the loading of the resource is failed.
     *
     * @since 1.16
     * @see BufferedImage.TYPE_INT_ARGB
     */
    BufferedImage getBufferedImage(int imageType) throws ResourcesException;

    /**
     * Returns the buffered image of the resource. It will lazy load the
     * resource.
     *
     * @param imageType
     *            the image {@link Integer} type.
     *
     * @param observer
     *            the {@link ImageObserver} that is notified when notifications
     *            about loaded image information.
     *
     * @return the {@link BufferedImage}.
     *
     * @throws ResourcesException
     *             if the loading of the resource is failed.
     *
     * @since 1.16
     * @see BufferedImage.TYPE_INT_ARGB
     */
    BufferedImage getBufferedImage(int imageType, ImageObserver observer)
            throws ResourcesException;

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
     * Returns the width of the image. The specified observer is notified when
     * the width is available.
     *
     * @param observer
     *            the {@link ImageObserver}.
     *
     * @return the width of the image in pixels.
     *
     * @throws ResourcesException
     *             if the loading of the resource is failed.
     *
     * @since 1.16
     */
    int getWidthPx(ImageObserver observer) throws ResourcesException;

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
     * Returns the height of the image. The specified observer is notified when
     * the height is available.
     *
     * @param observer
     *            the {@link ImageObserver}.
     *
     * @return the height of the image in pixels.
     *
     * @throws ResourcesException
     *             if the loading of the resource is failed.
     *
     * @since 1.16
     */
    int getHeightPx(ImageObserver observer) throws ResourcesException;

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

    /**
     * Returns the width and height of the image. The method will block until
     * the height is available from the image.
     *
     * @param observer
     *            the {@link ImageObserver}.
     *
     * @return the {@link Dimension} containing the width and height of the
     *         image in pixels.
     *
     * @throws ResourcesException
     *             if the loading of the resource is failed.
     *
     * @since 1.16
     */
    Dimension getSizePx(ImageObserver observer) throws ResourcesException;

}
