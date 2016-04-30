/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-st.
 *
 * resources-st is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-st is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-st. If not, see <http://www.gnu.org/licenses/>.
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
