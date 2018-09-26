
package com.anrisoftware.resources.binary.internal.binaries;

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
import static com.google.inject.util.Providers.of;

import java.util.ResourceBundle.Control;

import javax.inject.Inject;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.anrisoftware.resources.binary.external.Binaries;
import com.anrisoftware.resources.binary.external.BinariesBundlesMapFactory;
import com.anrisoftware.resources.binary.external.BinariesBundlesMapService;
import com.anrisoftware.resources.binary.external.BinariesFactory;
import com.anrisoftware.resources.binary.external.BinariesMapFactory;
import com.anrisoftware.resources.binary.external.BinariesMapService;
import com.anrisoftware.resources.binary.external.BinariesService;
import com.google.inject.AbstractModule;

/**
 * Provides the binaries resources as a service.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 2.1
 */
@Component(service = BinariesService.class)
public class BinariesServiceImpl implements BinariesService {

    @Reference
    private BinariesBundlesMapService bundlesMapService;

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
    public Binaries create(String baseName, ClassLoader classLoader, Control control) {
        return binariesFactory.create(baseName, classLoader, control);
    }

    @Activate
    protected void start() {
        createInjector(new BinariesResourcesModule(), new AbstractModule() {

            @Override
            protected void configure() {
                bind(BinariesBundlesMapFactory.class).toProvider(of(bundlesMapService));
                bind(BinariesMapFactory.class).toProvider(of(binariesMapService));
            }
        }).injectMembers(this);
    }

}
