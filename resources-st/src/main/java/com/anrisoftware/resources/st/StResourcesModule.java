package com.anrisoftware.resources.st;

import com.anrisoftware.resources.api.TemplateResource;
import com.anrisoftware.resources.api.TemplateResourceFactory;
import com.anrisoftware.resources.api.Templates;
import com.anrisoftware.resources.api.TemplatesFactory;
import com.anrisoftware.resources.st.worker.STTemplateDefaultPropertiesModule;
import com.anrisoftware.resources.st.worker.STWorkerModule;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the template resources classes.
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
 * List modules = { new StResourcesModule(), new TemplatesDefaultMapsModule(), 
 * new STTemplateDefaultPropertiesModule() }
 * Injector injector = Guice.createInjector(modules);
 * TemplatesFactory factory = injector.getInstance(TemplatesFactory);
 * 
 * // ...
 * String baseName = "StTemplates";
 * Templates templates = factory.create(baseName);
 * 
 * // no specific locale
 * TemplateResource template = texts.getResource("test");
 * String textString = template.getText("name", "Erwin");
 * System.out.println(textString);
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class StResourcesModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(Templates.class,
				StImpl.class).build(TemplatesFactory.class));
		install(new FactoryModuleBuilder().implement(TemplateResource.class,
				StResourceImpl.class).build(TemplateResourceFactory.class));
		install(new STWorkerModule());
	}

}
