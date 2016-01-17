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
package com.anrisoftware.resources.templates.templates;

import com.anrisoftware.resources.templates.api.TemplateResource;
import com.anrisoftware.resources.templates.api.TemplateResourceFactory;
import com.anrisoftware.resources.templates.api.Templates;
import com.anrisoftware.resources.templates.api.TemplatesFactory;
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
		install(new FactoryModuleBuilder().implement(Templates.class,
				TemplatesImpl.class).build(TemplatesFactory.class));
		install(new FactoryModuleBuilder().implement(TemplateResource.class,
				TemplateResourceImpl.class)
				.build(TemplateResourceFactory.class));
	}

}
