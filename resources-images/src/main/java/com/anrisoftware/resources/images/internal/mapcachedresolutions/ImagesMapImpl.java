/*
 * Copyright 2012-2023 Erwin Müller <erwin.mueller@anrisoftware.com>
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
package com.anrisoftware.resources.images.internal.mapcachedresolutions;

import static java.lang.Math.abs;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import jakarta.inject.Inject;

import com.anrisoftware.resources.images.external.ImageResolution;
import com.anrisoftware.resources.images.external.ImageResource;
import com.anrisoftware.resources.images.external.ImagesMap;

class ImagesMapImpl implements ImagesMap {

    private final ImagesMapLogger log;

    /**
     * Saves the loaded image resources.
     * <p>
     * The image resources are stored for each name and for each resolution, i.e.:
     *
     * <pre>
     * [&lt;name:{@link String}&gt; := [&lt;resolution:{@link ImageResolution}&gt; := {@link ImageResource}]]
     * </pre>
     */
    private final Map<String, Map<ImageResolution, ImageResource>> images;

    /**
     * Creates the images map.
     */
    @Inject
    ImagesMapImpl(ImagesMapLogger logger) {
        this.log = logger;
        this.images = new HashMap<String, Map<ImageResolution, ImageResource>>();
    }

    @Override
    public void putImage(ImageResource image) {
        String name = image.getName();
        ImageResolution resolution = image.getResolution();
        Map<ImageResolution, ImageResource> resolutions = resolutionsMap(name);
        if (!resolutions.containsKey(resolution)) {
            resolutions.put(resolution, image);
        } else {
            log.imageAlreadyInMap(this, image);
        }
    }

    private Map<ImageResolution, ImageResource> resolutionsMap(String name) {
        Map<ImageResolution, ImageResource> resolutions = images.get(name);
        if (resolutions == null) {
            resolutions = new HashMap<ImageResolution, ImageResource>();
            images.put(name, resolutions);
        }
        return resolutions;
    }

    @Override
    public ImageResource getImage(String name, Dimension size) {
        Map<ImageResolution, ImageResource> resolutions = resolutionsMap(name);
        int diff = Integer.MAX_VALUE;
        int newdiff;
        int sizeArea = size.width;
        int imageArea;
        ImageResource foundImage = null;
        for (ImageResource image : resolutions.values()) {
            imageArea = image.getWidthPx();
            newdiff = sizeArea - imageArea;
            if (newdiff == 0) {
                foundImage = image;
                break;
            }
            if (newdiff < 0 && abs(newdiff) < abs(diff)) {
                foundImage = image;
                diff = newdiff;
            }
            if (newdiff > 0 && newdiff < diff) {
                foundImage = image;
                diff = newdiff;
            }
        }
        return foundImage;
    }

    @Override
    public ImageResource getImage(String name, Dimension size, ImageResolution resolution) {
        Map<ImageResolution, ImageResource> resolutions;
        resolutions = resolutionsMap(name);
        ImageResource image = resolutions.get(resolution);
        if (image == null) {
            log.noImageReturningNearest(this, name);
            return getImage(name, size);
        }
        return image;
    }

    @Override
    public boolean haveImage(String name) {
        return images.containsKey(name);
    }

    @Override
    public boolean haveImage(String name, ImageResolution resolution) {
        Map<ImageResolution, ImageResource> resolutions;
        resolutions = images.get(name);
        return resolutions == null ? false : resolutions.containsKey(resolution);
    }

    @Override
    public boolean haveImage(String name, ImageResolution resolution, Dimension size) {
        return haveImage(name, resolution);
    }

    @Override
    public String toString() {
        return images.toString();
    }

}
