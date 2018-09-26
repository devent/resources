
package com.anrisoftware.resources.binary.internal.resources;

/*-
 * #%L
 * Resources :: Binary
 * %%
 * Copyright (C) 2012 - 2018 Advanced Natural Research Institute
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import static com.google.inject.Guice.createInjector;

import java.net.URL;
import java.util.Locale;

import javax.inject.Inject;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import com.anrisoftware.resources.binary.external.BinaryResource;
import com.anrisoftware.resources.binary.external.BinaryResourceFactory;
import com.anrisoftware.resources.binary.external.BinaryResourceService;
import com.google.inject.AbstractModule;

/**
 * Provides the binaries resources as a service.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 2.1
 */
@Component(service = BinaryResourceService.class)
public class BinaryResourceServiceImpl implements BinaryResourceService {

    @Inject
    private BinaryResourceFactory binariesFactory;

    @Override
    public BinaryResource create(String name, Locale locale, URL url) {
        return binariesFactory.create(name, locale, url);
    }

    @Activate
    protected void start() {
        createInjector(new BinaryResourceModule(), new AbstractModule() {

            @Override
            protected void configure() {
            }
        }).injectMembers(this);
    }

}
