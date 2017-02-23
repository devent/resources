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

import com.anrisoftware.resources.templates.external.TemplatesBundlesMap;
import com.anrisoftware.resources.templates.external.TemplatesBundlesMapFactory;
import com.anrisoftware.resources.templates.external.TemplatesMap;
import com.anrisoftware.resources.templates.external.TemplatesMapFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the Java hash map as the template resources maps.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class TemplatesDefaultMapsModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(TemplatesMap.class,
				TemplatesMapImpl.class).build(TemplatesMapFactory.class));
        install(new FactoryModuleBuilder().implement(TemplatesBundlesMap.class,
                TemplatesBundlesMapImpl.class).build(TemplatesBundlesMapFactory.class));
	}

}
