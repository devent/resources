/*
 * Copyright 2012-2025 Erwin Müller <erwin.mueller@anrisoftware.com>
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
package com.anrisoftware.resources.templates.internal.templates;


import com.anrisoftware.resources.templates.external.TemplateResource;
import com.anrisoftware.resources.templates.external.TemplateResourceFactory;
import com.anrisoftware.resources.templates.external.Templates;
import com.anrisoftware.resources.templates.external.TemplatesFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class TemplatesResourcesModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(Templates.class, TemplatesImpl.class)
                .build(TemplatesFactory.class));
        install(new FactoryModuleBuilder()
                .implement(TemplateResource.class, TemplateResourceImpl.class)
                .build(TemplateResourceFactory.class));
    }

}
