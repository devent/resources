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
package com.anrisoftware.resources.images.internal.mapcachedresolutions;

import static java.lang.Math.abs;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.anrisoftware.resources.external.ResourcesException;
import com.anrisoftware.resources.images.external.ImageResolution;
import com.anrisoftware.resources.images.external.ImageResource;
import com.anrisoftware.resources.images.external.ImagesMap;

/**
 * Uses a Java hash map to store the image resources.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
class ImagesMapImpl implements ImagesMap {

    private final ImagesMapLogger log;

    /**
     * Saves the loaded image resources.
     * <p>
     * The image resources are stored for each name and for each resolution,
     * i.e.:
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
    public void putImage(ImageResource image) throws ResourcesException {
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
    public ImageResource getImage(String name, Dimension size,
            ImageResolution resolution) {
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
        return resolutions == null ? false : resolutions
                .containsKey(resolution);
    }

    @Override
    public boolean haveImage(String name, ImageResolution resolution,
            Dimension size) {
        return haveImage(name, resolution);
    }

    @Override
    public String toString() {
        return images.toString();
    }

}
