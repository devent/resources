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
package com.anrisoftware.resources.images.internal.images;

import static com.google.inject.Guice.createInjector;
import static com.google.inject.util.Providers.of;

import java.util.ResourceBundle.Control;

import javax.inject.Inject;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;

import com.anrisoftware.resources.images.external.BundlesMapFactory;
import com.anrisoftware.resources.images.external.BundlesMapService;
import com.anrisoftware.resources.images.external.ImageScalingWorkerFactory;
import com.anrisoftware.resources.images.external.ImageScalingWorkerService;
import com.anrisoftware.resources.images.external.Images;
import com.anrisoftware.resources.images.external.ImagesFactory;
import com.anrisoftware.resources.images.external.ImagesMapFactory;
import com.anrisoftware.resources.images.external.ImagesMapService;
import com.anrisoftware.resources.images.external.ImagesService;
import com.google.inject.AbstractModule;

/**
 * Provides the images resources as a service.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 2.1
 */
@Component
@Service(ImagesService.class)
public class ImagesServiceImpl implements ImagesService {

    @Reference
    private BundlesMapService bundlesMapService;

    @Reference
    private ImagesMapService imagesMapService;

    @Reference
    private ImageScalingWorkerService imageScalingWorkerService;

    @Inject
    private ImagesFactory imagesFactory;

    @Override
    public Images create(String baseName) {
        return imagesFactory.create(baseName);
    }

    @Override
    public Images create(String baseName, ClassLoader classLoader) {
        return imagesFactory.create(baseName, classLoader);
    }

    @Override
    public Images create(String baseName, Control control) {
        return imagesFactory.create(baseName, control);
    }

    @Override
    public Images create(String baseName, ClassLoader classLoader,
            Control control) {
        return imagesFactory.create(baseName, classLoader, control);
    }

    @Activate
    protected void start() {
        createInjector(new ImagesResourcesModule(), new AbstractModule() {

            @Override
            protected void configure() {
                bind(BundlesMapFactory.class).toProvider(of(bundlesMapService));
                bind(ImagesMapFactory.class).toProvider(of(imagesMapService));
                bind(ImageScalingWorkerFactory.class).toProvider(
                        of(imageScalingWorkerService));
            }
        }).injectMembers(this);
    }

}
