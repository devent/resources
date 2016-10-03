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
package com.anrisoftware.resources.images.internal.mapcached;

import static java.lang.Math.abs;

import java.awt.Dimension;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
     * The image resources are stored for each name, for each resolution and for
     * each image size, i.e.:
     *
     * <pre>
     * [&lt;name:{@link String}&gt; = [&lt;resolution:{@link ImageResolution}&gt; = [&lt;size:{@link Dimension}&gt; = {@link ImageResource}]]]
     * </pre>
     */
    private final Map<String, Map<ImageResolution, Map<Dimension, ImageResource>>> images;

    /**
     * Creates the images map.
     */
    @Inject
    ImagesMapImpl(ImagesMapLogger logger) {
        this.log = logger;
        this.images = new HashMap<String, Map<ImageResolution, Map<Dimension, ImageResource>>>();
    }

    @Override
    public void putImage(ImageResource image) throws ResourcesException {
        String name = image.getName();
        ImageResolution resolution = image.getResolution();
        Map<ImageResolution, Map<Dimension, ImageResource>> resolutions = resolutionsMap(name);
        Map<Dimension, ImageResource> resources = resourcesMap(resolutions,
                resolution);
        Dimension dimension = image.getSizePx();
        if (!resources.containsKey(dimension)) {
            resources.put(dimension, image);
        } else {
            log.imageAlreadyInMap(this, image);
        }
    }

    private Map<Dimension, ImageResource> resourcesMap(
            Map<ImageResolution, Map<Dimension, ImageResource>> resolutions,
            ImageResolution resolution) {
        Map<Dimension, ImageResource> resources = resolutions.get(resolution);
        if (resources == null) {
            resources = new TreeMap<Dimension, ImageResource>(
                    new Comparator<Dimension>() {

                        @Override
                        public int compare(Dimension o1, Dimension o2) {
                            return o1.height < o2.height ? -1
                                    : o1.height > o2.height ? 1 : 0;
                        }

                    });
        }
        resolutions.put(resolution, resources);
        return resources;
    }

    private Map<ImageResolution, Map<Dimension, ImageResource>> resolutionsMap(
            String name) {
        Map<ImageResolution, Map<Dimension, ImageResource>> resolutions;
        resolutions = images.get(name);
        if (resolutions == null) {
            resolutions = new HashMap<ImageResolution, Map<Dimension, ImageResource>>();
            images.put(name, resolutions);
        }
        return resolutions;
    }

    @Override
    public ImageResource getImage(String name, Dimension size) {
        Map<ImageResolution, Map<Dimension, ImageResource>> resolutions = resolutionsMap(name);
        FindNearest findNearest = new FindNearest(size);

        int diff = Integer.MAX_VALUE;
        ImageResource foundImage = null;
        for (Map<Dimension, ImageResource> sizesmap : resolutions.values()) {
            ImageResource image = sizesmap.get(size);
            if (image != null) {
                return image;
            }
            findNearest.findNearest(sizesmap);
            int newdiff = findNearest.getDifference();
            if (newdiff < 0 && abs(newdiff) < abs(diff)) {
                foundImage = findNearest.getImage();
                diff = newdiff;
            }
            if (newdiff > 0 && newdiff < diff) {
                foundImage = findNearest.getImage();
                diff = newdiff;
            }
        }
        return foundImage;
    }

    @Override
    public ImageResource getImage(String name, Dimension size,
            ImageResolution resolution) {
        Map<ImageResolution, Map<Dimension, ImageResource>> resolutions;
        Map<Dimension, ImageResource> resources;
        resolutions = resolutionsMap(name);
        resources = resourcesMap(resolutions, resolution);
        ImageResource image = resources.get(size);
        if (image == null) {
            log.noImageReturningNearest(this, name);
            return new FindNearest(size).findNearest(resources);
        }
        return image;
    }

    /**
     * Find the nearest image resource.
     *
     * @author Erwin Mueller, erwin.mueller@deventm.org
     * @since 1.0
     */
    private static class FindNearest {

        private final int searchArea;

        private int difference;

        private ImageResource image;

        /**
         * Set the size what we search for.
         *
         * @param searchSize
         *            the {@link Dimension} that is the size.
         */
        public FindNearest(Dimension searchSize) {
            this.searchArea = searchSize.width * searchSize.height;
            this.difference = Integer.MAX_VALUE;
        }

        /**
         * Find the next nearest image to the give size.
         *
         * @param resources
         *            the {@link Map} of the {@link ImageResource}s with the
         *            {@link Dimension} size that are in the map.
         *
         * @return the nearest {@link ImageResource} that is found. If the size
         *         is smaller then the smallest image in the map, we return the
         *         smallest image. If the size is larger then the biggest image
         *         in the map, we return the biggest image.
         */
        public ImageResource findNearest(Map<Dimension, ImageResource> resources) {
            for (Map.Entry<Dimension, ImageResource> entry : resources
                    .entrySet()) {
                int newarea = entry.getKey().width * entry.getKey().height;
                int newdiff = searchArea - newarea;
                if (newdiff < 0 && abs(newdiff) < abs(difference)) {
                    image = entry.getValue();
                    difference = newdiff;
                }
                if (newdiff > 0 && newdiff < difference) {
                    image = entry.getValue();
                    difference = newdiff;
                }
            }
            return image;
        }

        /**
         * Return the found nearest {@link ImageResource} or <code>null</code>
         * if no image was found.
         */
        public ImageResource getImage() {
            return image;
        }

        /**
         * Returns the difference of the area of the found image and the
         * searched size.
         */
        public int getDifference() {
            return difference;
        }

    }

    @Override
    public boolean haveImage(String name) {
        return images.containsKey(name);
    }

    @Override
    public boolean haveImage(String name, ImageResolution resolution) {
        Map<ImageResolution, Map<Dimension, ImageResource>> resolutions;
        resolutions = images.get(name);
        return resolutions == null ? false : resolutions
                .containsKey(resolution);
    }

    @Override
    public boolean haveImage(String name, ImageResolution resolution,
            Dimension size) {
        Map<ImageResolution, Map<Dimension, ImageResource>> resolutions;
        resolutions = images.get(name);
        if (resolutions == null) {
            return false;
        }
        Map<Dimension, ImageResource> sizes = resolutions.get(size);
        return sizes == null ? false : sizes.containsKey(size);
    }

    @Override
    public String toString() {
        return images.toString();
    }

}
