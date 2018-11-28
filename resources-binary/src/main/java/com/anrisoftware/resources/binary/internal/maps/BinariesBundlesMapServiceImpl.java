/**
 * Copyright © 2012 Erwin Müller (erwin.mueller@anrisoftware.com)
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

package com.anrisoftware.resources.binary.internal.maps;


import static com.google.inject.Guice.createInjector;

import javax.inject.Inject;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import com.anrisoftware.resources.binary.external.BinariesBundlesMap;
import com.anrisoftware.resources.binary.external.BinariesBundlesMapFactory;
import com.anrisoftware.resources.binary.external.BinariesBundlesMapService;
import com.google.inject.AbstractModule;

@Component(service = BinariesBundlesMapService.class)
public class BinariesBundlesMapServiceImpl implements BinariesBundlesMapService {

    @Inject
    private BinariesBundlesMapFactory bundlesMapFactory;

    @Override
    public BinariesBundlesMap create() {
        return bundlesMapFactory.create();
    }

    @Activate
    protected void start() {
        createInjector(new BinariesDefaultMapsModule(), new AbstractModule() {

            @Override
            protected void configure() {
            }
        }).injectMembers(this);
    }

}
