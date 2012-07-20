package com.anrisoftware.resources.st;

import com.anrisoftware.resources.api.TemplateResource;
import com.anrisoftware.resources.api.Templates;
import com.anrisoftware.resources.api.TextResourceFactory;
import com.anrisoftware.resources.api.TextsFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the template resources classes.
 * <p>
 * It will use the <a
 * href=http://www.antlr.org/wiki/display/ST4/StringTemplate+4+Wiki+Home>String
 * Template</a> template engine to process the templates.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class StResourcesModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(Templates.class,
				StImpl.class).build(TextsFactory.class));
		install(new FactoryModuleBuilder().implement(TemplateResource.class,
				StResourceImpl.class).build(TextResourceFactory.class));
	}

}
