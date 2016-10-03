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
package com.anrisoftware.resources.images.internal.images;

import java.awt.Dimension;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import com.anrisoftware.resources.external.GetBundle;
import com.anrisoftware.resources.external.GetBundleWithClassLoader;
import com.anrisoftware.resources.external.GetBundleWithClassLoaderAndControl;
import com.anrisoftware.resources.external.GetBundleWithControl;
import com.anrisoftware.resources.external.ResourcesException;
import com.anrisoftware.resources.images.external.ImagesBundlesMapFactory;
import com.anrisoftware.resources.images.external.IconSize;
import com.anrisoftware.resources.images.external.ImageResolution;
import com.anrisoftware.resources.images.external.ImageResource;
import com.anrisoftware.resources.images.external.Images;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Manages the bundles of the image resources.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ImagesImpl implements Images {

    private final GetBundle getBundle;

    private final ImagesWorker worker;

    @AssistedInject
    ImagesImpl(ImagesWorkerFactory workerFactory, ImagesBundlesMapFactory bundles,
            @Assisted String baseName) {
        this(workerFactory, bundles, new GetBundle(baseName));
    }

    @AssistedInject
    ImagesImpl(ImagesWorkerFactory workerFactory, ImagesBundlesMapFactory bundles,
            @Assisted String baseName, @Assisted ClassLoader classLoader) {
        this(workerFactory, bundles, new GetBundleWithClassLoader(baseName,
                classLoader));
    }

    @AssistedInject
    ImagesImpl(ImagesWorkerFactory workerFactory, ImagesBundlesMapFactory bundles,
            @Assisted String baseName, @Assisted ResourceBundle.Control control) {
        this(workerFactory, bundles,
                new GetBundleWithControl(baseName, control));
    }

    @AssistedInject
    ImagesImpl(ImagesWorkerFactory workerFactory, ImagesBundlesMapFactory bundles,
            @Assisted String baseName, @Assisted ClassLoader classLoader,
            @Assisted ResourceBundle.Control control) {
        this(workerFactory, bundles, new GetBundleWithClassLoaderAndControl(
                baseName, classLoader, control));
    }

    private ImagesImpl(ImagesWorkerFactory workerFactory,
            ImagesBundlesMapFactory bundles, GetBundle getBundle) {
        this.worker = workerFactory.create(getBundle, bundles.create());
        this.getBundle = getBundle;
    }

    @Override
    public ClassLoader getClassLoader() {
        return getBundle.getClassLoader();
    }

    @Override
    public String getBaseName() {
        return getBundle.getBaseName();
    }

    @Override
    public Control getControl() {
        return getBundle.getControl();
    }

    @Override
    public ImageResource getResource(String name, Locale locale, int width,
            int height) throws ResourcesException {
        return getResource(name, locale, new Dimension(width, height));
    }

    @Override
    public ImageResource getResource(String name, Locale locale, IconSize size)
            throws ResourcesException {
        return getResource(name, locale,
                new Dimension(size.getSizePx(), size.getSizePx()));
    }

    @Override
    public ImageResource getResource(String name, Locale locale, Dimension size)
            throws ResourcesException {
        return worker.imageResource(name, locale, size);
    }

    @Override
    public ImageResource getResource(String name, Locale locale, int width,
            int height, ImageResolution resolution) throws ResourcesException {
        return getResource(name, locale, new Dimension(width, height),
                resolution);
    }

    @Override
    public ImageResource getResource(String name, Locale locale,
            Dimension size, ImageResolution resolution)
            throws ResourcesException {
        return worker.imageResource(name, locale, size, resolution);
    }
}
