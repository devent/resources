/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-templates.
 *
 * resources-templates is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-templates is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-templates. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.templates.internal.templates;

import static com.google.inject.Guice.createInjector;
import static com.google.inject.util.Providers.of;

import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle.Control;

import javax.inject.Inject;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;

import com.anrisoftware.resources.templates.external.BundlesMapFactory;
import com.anrisoftware.resources.templates.external.BundlesMapService;
import com.anrisoftware.resources.templates.external.TemplateWorkerFactory;
import com.anrisoftware.resources.templates.external.TemplateWorkerService;
import com.anrisoftware.resources.templates.external.Templates;
import com.anrisoftware.resources.templates.external.TemplatesFactory;
import com.anrisoftware.resources.templates.external.TemplatesMapFactory;
import com.anrisoftware.resources.templates.external.TemplatesMapService;
import com.anrisoftware.resources.templates.external.TemplatesPropertiesFactory;
import com.anrisoftware.resources.templates.external.TemplatesPropertiesService;
import com.anrisoftware.resources.templates.external.TemplatesService;
import com.google.inject.AbstractModule;

/**
 * Provides the template resources as a service.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 2.1
 */
@Component
@Service(TemplatesService.class)
public class TemplatesServiceImpl implements TemplatesService {

    @Reference
    private BundlesMapService bundlesMapService;

    @Reference
    private TemplatesMapService templatesMapService;

    @Reference
    private TemplateWorkerService templateWorkerService;

    @Reference
    private TemplatesPropertiesService templatesPropertiesService;

    @Inject
    private TemplatesFactory templatesFactory;

    @Override
    public Templates create(String baseName,
            Map<Serializable, Serializable> attributes) {
        return templatesFactory.create(baseName, attributes);
    }

    @Override
    public Templates create(String baseName) {
        return templatesFactory.create(baseName);
    }

    @Override
    public Templates create(String baseName, ClassLoader classLoader) {
        return templatesFactory.create(baseName, classLoader);
    }

    @Override
    public Templates create(String baseName,
            Map<Serializable, Serializable> attributes, ClassLoader classLoader) {
        return templatesFactory.create(baseName, attributes, classLoader);
    }

    @Override
    public Templates create(String baseName, Control control) {
        return templatesFactory.create(baseName, control);
    }

    @Override
    public Templates create(String baseName,
            Map<Serializable, Serializable> attributes, Control control) {
        return templatesFactory.create(baseName, attributes, control);
    }

    @Override
    public Templates create(String baseName, ClassLoader classLoader,
            Control control) {
        return templatesFactory.create(baseName, classLoader, control);
    }

    @Override
    public Templates create(String baseName,
            Map<Serializable, Serializable> attributes,
            ClassLoader classLoader, Control control) {
        return templatesFactory.create(baseName, attributes, classLoader,
                control);
    }

    @Activate
    protected void start() {
        createInjector(new TemplatesResourcesModule(), new AbstractModule() {

            @Override
            protected void configure() {
                bind(BundlesMapFactory.class).toProvider(of(bundlesMapService));
                bind(TemplatesMapFactory.class).toProvider(
                        of(templatesMapService));
                bind(TemplateWorkerFactory.class).toProvider(
                        of(templateWorkerService));
                bind(TemplatesPropertiesFactory.class).toProvider(
                        of(templatesPropertiesService));
            }
        }).injectMembers(this);
    }

}
