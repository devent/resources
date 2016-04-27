/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-binary.
 *
 * resources-binary is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-binary is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-binary. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.binary.internal.binaries;

import static com.google.inject.Guice.createInjector;
import static com.google.inject.util.Providers.of;

import java.util.ResourceBundle.Control;

import javax.inject.Inject;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;

import com.anrisoftware.resources.binary.external.Binaries;
import com.anrisoftware.resources.binary.external.BinariesFactory;
import com.anrisoftware.resources.binary.external.BinariesMapFactory;
import com.anrisoftware.resources.binary.external.BinariesMapService;
import com.anrisoftware.resources.binary.external.BinariesService;
import com.anrisoftware.resources.binary.external.BundlesMapFactory;
import com.anrisoftware.resources.binary.external.BundlesMapService;
import com.google.inject.AbstractModule;

/**
 * Provides the binaries resources as a service.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 2.1
 */
@Component
@Service(BinariesService.class)
public class BinariesServiceImpl implements BinariesService {

    @Reference
    private BundlesMapService bundlesMapService;

    @Reference
    private BinariesMapService binariesMapService;

    @Inject
    private BinariesFactory binariesFactory;

    @Override
    public Binaries create(String baseName) {
        return binariesFactory.create(baseName);
    }

    @Override
    public Binaries create(String baseName, ClassLoader classLoader) {
        return binariesFactory.create(baseName, classLoader);
    }

    @Override
    public Binaries create(String baseName, Control control) {
        return binariesFactory.create(baseName, control);
    }

    @Override
    public Binaries create(String baseName, ClassLoader classLoader,
            Control control) {
        return binariesFactory.create(baseName, classLoader, control);
    }

    @Activate
    protected void start() {
        createInjector(new BinariesResourcesModule(), new AbstractModule() {

            @Override
            protected void configure() {
                bind(BundlesMapFactory.class).toProvider(of(bundlesMapService));
                bind(BinariesMapFactory.class).toProvider(
                        of(binariesMapService));
            }
        }).injectMembers(this);
    }

}
