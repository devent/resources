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
package com.anrisoftware.resources.images.resource;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

final class ImageGetterWorker {

    public ImageIcon getImage(ImageResourceImpl resource,
            ImageResourceImplLogger log, Image image, ImageObserver observer) {
        ImageIcon imageLoader = new ImageIcon(image);
        imageLoader.setImageObserver(observer);
        if (observer == null) {
            imageLoader = waitUntilImageIsLoaded(resource, log, image,
                    imageLoader);
        }
        return imageLoader;
    }

    private ImageIcon waitUntilImageIsLoaded(ImageResourceImpl resource,
            ImageResourceImplLogger log, Image image, ImageIcon imageLoader) {
        log.imageIsLoaded(resource, isImageLoaded(imageLoader));
        while (!isImageLoaded(imageLoader)) {
            Thread.yield();
        }
        return imageLoader;
    }

    private boolean isImageLoaded(ImageIcon imageLoader) {
        return imageLoader.getImageLoadStatus() != MediaTracker.LOADING
                || imageLoader.getImageLoadStatus() != MediaTracker.COMPLETE;
    }

}
