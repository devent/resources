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
package com.anrisoftware.resources.images.internal.mapcachedresolutions;

import static com.google.inject.Guice.createInjector;

import javax.inject.Inject;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import com.anrisoftware.resources.images.external.ImagesBundlesMap;
import com.anrisoftware.resources.images.external.ImagesBundlesMapFactory;
import com.anrisoftware.resources.images.external.ImagesBundlesMapService;
import com.google.inject.AbstractModule;

/**
 * Binaries resources bundles map service.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 2.1
 */
@Component
@Service(ImagesBundlesMapService.class)
public class ImagesBundlesMapCachedResolutionsServiceImpl implements ImagesBundlesMapService {

    @Inject
    private ImagesBundlesMapFactory bundlesMapFactory;

    @Override
    public ImagesBundlesMap create() {
        return bundlesMapFactory.create();
    }

    @Activate
    protected void start() {
        createInjector(new ResourcesImagesCachedResolutionsMapModule(),
                new AbstractModule() {

                    @Override
                    protected void configure() {
                    }
                }).injectMembers(this);
    }

}
