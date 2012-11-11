package com.anrisoftware.resources.templates.worker;

import com.anrisoftware.resources.templates.api.TemplateWorkerFactory;
import com.anrisoftware.resources.templates.api.TemplateWorker;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the ST worker classes.
 * 
 * @author Erwin Müller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class STWorkerModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(TemplateWorker.class,
				STTemplateWorker.class).build(TemplateWorkerFactory.class));
	}
}
