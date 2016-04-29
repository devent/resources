/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-texts.
 *
 * resources-texts is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-texts is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-texts. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.texts.internal.texts;

import static com.google.inject.Guice.createInjector;
import static com.google.inject.util.Providers.of;

import java.util.ResourceBundle.Control;

import javax.inject.Inject;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;

import com.anrisoftware.resources.binary.external.BinariesFactory;
import com.anrisoftware.resources.binary.external.BinariesMapFactory;
import com.anrisoftware.resources.binary.external.BinariesMapService;
import com.anrisoftware.resources.binary.external.BinariesService;
import com.anrisoftware.resources.binary.external.BinaryResourceFactory;
import com.anrisoftware.resources.binary.external.BinaryResourceService;
import com.anrisoftware.resources.texts.external.BundlesMapFactory;
import com.anrisoftware.resources.texts.external.BundlesMapService;
import com.anrisoftware.resources.texts.external.Texts;
import com.anrisoftware.resources.texts.external.TextsFactory;
import com.anrisoftware.resources.texts.external.TextsMapFactory;
import com.anrisoftware.resources.texts.external.TextsMapService;
import com.anrisoftware.resources.texts.external.TextsService;
import com.google.inject.AbstractModule;

/**
 * Provides the texts resources as a service.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 2.1
 */
@Component
@Service(TextsService.class)
public class TextsServiceImpl implements TextsService {

    @Reference
    private BundlesMapService bundlesMapService;

    @Reference
    private TextsMapService textsMapService;

    @Reference
    private BinariesService binariesService;

    @Reference
    private com.anrisoftware.resources.binary.external.BundlesMapService binariesBundlesMapService;

    @Reference
    private BinariesMapService binariesMapService;

    @Reference
    private BinaryResourceService binaryResourceService;

    @Inject
    private TextsFactory textsFactory;

    @Override
    public Texts create(String baseName) {
        return textsFactory.create(baseName);
    }

    @Override
    public Texts create(String baseName, ClassLoader classLoader) {
        return textsFactory.create(baseName, classLoader);
    }

    @Override
    public Texts create(String baseName, Control control) {
        return textsFactory.create(baseName, control);
    }

    @Override
    public Texts create(String baseName, ClassLoader classLoader,
            Control control) {
        return textsFactory.create(baseName, classLoader, control);
    }

    @Activate
    protected void start() {
        createInjector(new TextsResourcesModule(), new AbstractModule() {

            @Override
            protected void configure() {
                install(new TextsResourcesCharsetModule());
                bind(BundlesMapFactory.class).toProvider(of(bundlesMapService));
                bind(TextsMapFactory.class).toProvider(of(textsMapService));
                bind(BinariesFactory.class).toProvider(of(binariesService));
                bind(
                        com.anrisoftware.resources.binary.external.BundlesMapFactory.class)
                        .toProvider(of(binariesBundlesMapService));
                bind(BinariesMapFactory.class).toProvider(
                        of(binariesMapService));
                bind(BinaryResourceFactory.class).toProvider(
                        of(binaryResourceService));
            }
        }).injectMembers(this);
    }

}
