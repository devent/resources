
package com.anrisoftware.resources.images.internal.resource;

/*-
 * #%L
 * Resources :: Image
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

import com.anrisoftware.resources.images.external.ImageResource;
import com.anrisoftware.resources.images.external.ImageResourceFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the image resource implementation.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.3
 */
public class ImageResourceModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(ImageResource.class,
				ImageResourceImpl.class).build(ImageResourceFactory.class));
	}

}
