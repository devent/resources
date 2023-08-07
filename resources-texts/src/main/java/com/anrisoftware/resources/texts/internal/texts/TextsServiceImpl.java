/*
 * Copyright 2012-2023 Erwin Müller <erwin.mueller@anrisoftware.com>
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
package com.anrisoftware.resources.texts.internal.texts;


import static com.google.inject.Guice.createInjector;
import static com.google.inject.util.Providers.of;

import java.util.ResourceBundle.Control;

import jakarta.inject.Inject;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.anrisoftware.resources.binary.external.BinariesFactory;
import com.anrisoftware.resources.binary.external.BinariesMapFactory;
import com.anrisoftware.resources.binary.external.BinariesMapService;
import com.anrisoftware.resources.binary.external.BinariesService;
import com.anrisoftware.resources.binary.external.BinaryResourceFactory;
import com.anrisoftware.resources.binary.external.BinaryResourceService;
import com.anrisoftware.resources.texts.external.Texts;
import com.anrisoftware.resources.texts.external.TextsBundlesMapFactory;
import com.anrisoftware.resources.texts.external.TextsBundlesMapService;
import com.anrisoftware.resources.texts.external.TextsFactory;
import com.anrisoftware.resources.texts.external.TextsMapFactory;
import com.anrisoftware.resources.texts.external.TextsMapService;
import com.anrisoftware.resources.texts.external.TextsService;
import com.google.inject.AbstractModule;

@Component(service = TextsService.class)
public class TextsServiceImpl implements TextsService {

    @Reference
    private TextsBundlesMapService bundlesMapService;

    @Reference
    private TextsMapService textsMapService;

    @Reference
    private BinariesService binariesService;

    @Reference
    private com.anrisoftware.resources.binary.external.BinariesBundlesMapService binariesBundlesMapService;

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
    public Texts create(String baseName, ClassLoader classLoader, Control control) {
        return textsFactory.create(baseName, classLoader, control);
    }

    @Activate
    protected void start() {
        createInjector(new TextsResourcesModule(), new AbstractModule() {

            @Override
            protected void configure() {
                install(new TextsResourcesCharsetModule());
                bind(TextsBundlesMapFactory.class).toProvider(of(bundlesMapService));
                bind(TextsMapFactory.class).toProvider(of(textsMapService));
                bind(BinariesFactory.class).toProvider(of(binariesService));
                bind(com.anrisoftware.resources.binary.external.BinariesBundlesMapFactory.class)
                        .toProvider(of(binariesBundlesMapService));
                bind(BinariesMapFactory.class).toProvider(of(binariesMapService));
                bind(BinaryResourceFactory.class).toProvider(of(binaryResourceService));
            }
        }).injectMembers(this);
    }

}
