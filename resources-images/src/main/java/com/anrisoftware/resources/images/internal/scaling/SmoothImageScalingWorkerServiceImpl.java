/*
 * Copyright 2017 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.images.internal.scaling;

import static com.google.inject.Guice.createInjector;

import java.awt.Dimension;
import java.awt.Image;

import javax.inject.Inject;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import com.anrisoftware.resources.images.external.ImageScalingWorker;
import com.anrisoftware.resources.images.external.ImageScalingWorkerFactory;
import com.anrisoftware.resources.images.external.ImageScalingWorkerService;
import com.google.inject.AbstractModule;

/**
 * Image scaling worker service.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
@Component
@Service(ImageScalingWorkerService.class)
public class SmoothImageScalingWorkerServiceImpl implements
        ImageScalingWorkerService {

    @Inject
    private ImageScalingWorkerFactory factory;

    @Override
    public ImageScalingWorker create(Image image, Dimension sizePx) {
        return factory.create(image, sizePx);
    }

    @Activate
    protected void start() {
        createInjector(new ResourcesSmoothScalingModule(),
                new AbstractModule() {

                    @Override
                    protected void configure() {
                    }
                }).injectMembers(this);
    }

}
