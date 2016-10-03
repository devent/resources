/*
 * Copyright 2016 Erwin Müller <erwin.mueller@deventm.org>
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

/**
 * Installs the template resources factory.
 * <p>
 * It will use the <a
 * href=http://www.antlr.org/wiki/display/ST4/StringTemplate+4+Wiki+Home>String
 * Template</a> template engine to process the templates.
 * <p>
 * Example to use the templates with the help of Guice. Normally we do not need
 * a specific locale for a template and it is supported to have templates with
 * no specific locale. The ST template needs additional properties, which are
 * loaded in the {@link STTemplateDefaultPropertiesModule}.
 * 
 * <pre>
 * resourcesModule = new TemplatesResourcesModule();
 * mapsModule = new TemplatesDefaultMapsModule();
 * List modules = { resourcesModule, mapsModule, propertiesModule }
 * Injector injector = Guice.createInjector(modules);
 * TemplatesFactory factory = injector.getInstance(TemplatesFactory);
 * 
 * // ...
 * String baseName = "StTemplates";
 * Templates templates = factory.create(baseName);
 * 
 * // use default locale
 * TemplateResource template = texts.getResource("test");
 * String textString = template.getText("name", "Erwin");
 * System.out.println(textString);
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
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
