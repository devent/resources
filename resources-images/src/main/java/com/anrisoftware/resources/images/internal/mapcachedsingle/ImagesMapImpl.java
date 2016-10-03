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
package com.anrisoftware.resources.images.internal.mapcachedsingle;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import com.anrisoftware.resources.external.ResourcesException;
import com.anrisoftware.resources.images.external.ImageResolution;
import com.anrisoftware.resources.images.external.ImageResource;
import com.anrisoftware.resources.images.external.ImagesMap;

/**
 * Uses a Java hash map to store the image resources, removes the oldest entries
 * if it's over maximum cache size.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.18
 */
class ImagesMapImpl implements ImagesMap {

    /**
     * Saves the loaded image resources.
     * <p>
     * The image resources are stored for each name, for each resolution, i.e.:
     *
     * <pre>
     * [&lt;name:{@link String}&gt; = [&lt;resolution:{@link ImageResolution}&gt; = {@link ImageResource}]]
     * </pre>
     */
    private final Map<String, Map<ImageResolution, ImageResource>> keyImages;

    private final Map<String, Map<Dimension, ImageResource>> imagesCache;

    private final int maxEntries;

    /**
     * Creates the images map.
     */
    @Inject
    ImagesMapImpl() {
        this.keyImages = new HashMap<String, Map<ImageResolution, ImageResource>>();
        this.imagesCache = new HashMap<String, Map<Dimension, ImageResource>>();
        this.maxEntries = 32;
    }

    @Override
    public void putImage(ImageResource image) throws ResourcesException {
        String name = image.getName();
        ImageResolution resolution = image.getResolution();
        Map<ImageResolution, ImageResource> resolutions = resolutionsMap(name);
        if (!resolutions.containsKey(resolution)) {
            resolutions.put(resolution, image);
        }
        Map<Dimension, ImageResource> entry = dimensionEntry(name, image);
        imagesCache.put(name, entry);
    }

    @SuppressWarnings("serial")
    private Map<Dimension, ImageResource> dimensionEntry(String name,
            ImageResource image) {
        Map<Dimension, ImageResource> entry = imagesCache.get(name);
        if (entry == null) {
            entry = new LinkedHashMap<Dimension, ImageResource>() {
                @Override
                protected boolean removeEldestEntry(
                        Map.Entry<Dimension, ImageResource> eldest) {
                    return size() > maxEntries;
                }
            };
        }
        entry.put(image.getSizePx(), image);
        return entry;
    }

    private Map<ImageResolution, ImageResource> resolutionsMap(String name) {
        Map<ImageResolution, ImageResource> resolutions;
        resolutions = keyImages.get(name);
        if (resolutions == null) {
            resolutions = new HashMap<ImageResolution, ImageResource>();
            keyImages.put(name, resolutions);
        }
        return resolutions;
    }

    @Override
    public ImageResource getImage(String name, Dimension size) {
        ImageResource foundImage = getCachedImage(name, size);
        if (foundImage == null) {
            foundImage = findNearestImage(name, size);
        }
        return foundImage;
    }

    @Override
    public ImageResource getImage(String name, Dimension size,
            ImageResolution resolution) {
        Map<ImageResolution, ImageResource> resolutions = resolutionsMap(name);
        ImageResource image = resolutions.get(resolution);
        if (image == null) {
            resolution = findNearestResolution(name, size);
            image = resolutions.get(resolution);
        }
        return image;
    }

    @Override
    public boolean haveImage(String name) {
        return keyImages.containsKey(name);
    }

    @Override
    public boolean haveImage(String name, ImageResolution resolution) {
        Map<ImageResolution, ImageResource> resolutions;
        resolutions = keyImages.get(name);
        return resolutions == null ? false : resolutions
                .containsKey(resolution);
    }

    @Override
    public boolean haveImage(String name, ImageResolution resolution,
            Dimension size) {
        Map<Dimension, ImageResource> images;
        images = imagesCache.get(name);
        if (images == null) {
            return false;
        }
        return images == null ? false : images.containsKey(size);
    }

    @Override
    public String toString() {
        return keyImages.toString();
    }

    private ImageResource getCachedImage(String name, Dimension size) {
        Map<Dimension, ImageResource> entry = imagesCache.get(name);
        if (entry == null) {
            return null;
        }
        return entry.get(size);
    }

    /**
     * Returns the nearest resolution to the specified size from the key-images
     * map.
     */
    private ImageResolution findNearestResolution(String name, Dimension size) {
        ImageResolution maxResolution = null;
        ImageResolution minResolution = null;
        ImageResolution foundResolution = null;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int diff;
        Map<ImageResolution, ImageResource> resolutions = keyImages.get(name);
        for (Entry<ImageResolution, ImageResource> entry : resolutions
                .entrySet()) {
            diff = entry.getValue().getWidthPx() - size.width;
            if (diff > -1 && min > diff) {
                min = diff;
                maxResolution = entry.getKey();
            }
            if (diff < 1 && max < diff) {
                max = diff;
                minResolution = entry.getKey();
            }
        }
        foundResolution = maxResolution;
        if (foundResolution == null) {
            foundResolution = minResolution;
        }
        return foundResolution;
    }

    /**
     * Finds the nearest image to the specified size from the key-images map.
     */
    private ImageResource findNearestImage(String name, Dimension size) {
        Map<ImageResolution, ImageResource> resolutions = resolutionsMap(name);
        ImageResolution resolution = findNearestResolution(name, size);
        return resolutions.get(resolution);
    }

}
