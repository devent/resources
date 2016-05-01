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
package com.anrisoftware.resources.templates.internal.worker;

import static com.google.inject.Guice.createInjector;

import java.io.IOException;
import java.util.Properties;

import javax.inject.Inject;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import com.anrisoftware.resources.templates.external.TemplatesPropertiesService;
import com.google.inject.AbstractModule;

/**
 * Provides the default ST4 properties as a service.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 2.1
 */
@Component
@Service(TemplatesPropertiesService.class)
public class STDefaultPropertiesServiceImpl implements
        TemplatesPropertiesService {

    @Inject
    private STDefaultPropertiesFactory propertiesFactory;

    @Override
    public Properties create() throws IOException {
        return propertiesFactory.create();
    }

    @Activate
    protected void start() {
        createInjector(new AbstractModule() {

            @Override
            protected void configure() {
            }
        }).injectMembers(this);
    }

}
