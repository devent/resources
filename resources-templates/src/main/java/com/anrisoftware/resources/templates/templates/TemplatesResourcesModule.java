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
