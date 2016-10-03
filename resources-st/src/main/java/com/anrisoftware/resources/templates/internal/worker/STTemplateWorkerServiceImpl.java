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
package com.anrisoftware.resources.templates.internal.worker;

import static com.google.inject.Guice.createInjector;

import java.io.Serializable;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import com.anrisoftware.resources.templates.external.TemplateWorker;
import com.anrisoftware.resources.templates.external.TemplateWorkerFactory;
import com.anrisoftware.resources.templates.external.TemplateWorkerService;
import com.google.inject.AbstractModule;

/**
 * Provides the template resources as a service.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 2.1
 */
@Component
@Service(TemplateWorkerService.class)
public class STTemplateWorkerServiceImpl implements TemplateWorkerService {

    @Inject
    private TemplateWorkerFactory templateWorkerFactory;

    @Override
    public TemplateWorker create(URL templateUrl, Properties properties,
            Map<Serializable, Serializable> attributes) {
        return templateWorkerFactory
                .create(templateUrl, properties, attributes);
    }

    @Activate
    protected void start() {
        createInjector(new STWorkerModule(), new AbstractModule() {

            @Override
            protected void configure() {
            }
        }).injectMembers(this);
    }

}
