/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.images.internal.resource;

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
