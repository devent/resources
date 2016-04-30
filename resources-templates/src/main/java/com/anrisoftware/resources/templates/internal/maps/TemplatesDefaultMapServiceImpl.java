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
package com.anrisoftware.resources.templates.internal.maps;

import static com.google.inject.Guice.createInjector;

import javax.inject.Inject;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import com.anrisoftware.resources.templates.external.TemplatesMap;
import com.anrisoftware.resources.templates.external.TemplatesMapFactory;
import com.anrisoftware.resources.templates.external.TemplatesMapService;
import com.google.inject.AbstractModule;

/**
 * Templates resources map service.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 2.1
 */
@Component
@Service(TemplatesMapService.class)
public class TemplatesDefaultMapServiceImpl implements TemplatesMapService {

    @Inject
    private TemplatesMapFactory mapFactory;

    @Override
    public TemplatesMap create() {
        return mapFactory.create();
    }

    @Activate
    protected void start() {
        createInjector(new TemplatesDefaultMapsModule(), new AbstractModule() {

            @Override
            protected void configure() {
            }
        }).injectMembers(this);
    }

}
