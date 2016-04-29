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
