/*
 * Copyright 2017 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.templates.internal.maps;

/*-
 * #%L
 * Resources :: Templates
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

import javax.inject.Inject;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import com.anrisoftware.resources.templates.external.TemplatesBundlesMap;
import com.anrisoftware.resources.templates.external.TemplatesBundlesMapFactory;
import com.anrisoftware.resources.templates.external.TemplatesBundlesMapService;
import com.google.inject.AbstractModule;

/**
 * Templates resources bundles map service.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 2.1
 */
@Component(service = TemplatesBundlesMapService.class)
public class TemplatesBundlesDefaultMapServiceImpl implements TemplatesBundlesMapService {

    @Inject
    private TemplatesBundlesMapFactory bundlesMapFactory;

    @Override
    public TemplatesBundlesMap create() {
        return bundlesMapFactory.create();
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
