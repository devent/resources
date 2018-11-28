/**
 * Copyright © 2012 Erwin Müller (erwin.mueller@anrisoftware.com)
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

package com.anrisoftware.resources.images.internal.images;


import com.anrisoftware.resources.images.external.Images;
import com.anrisoftware.resources.images.external.ImagesFactory;
import com.anrisoftware.resources.images.internal.resource.ImageResourceModule;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class ImagesResourcesModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new ImageResourceModule());
		install(new FactoryModuleBuilder().implement(Images.class,
				ImagesImpl.class).build(ImagesFactory.class));
		install(new FactoryModuleBuilder().implement(ImagesWorker.class,
				ImagesWorker.class).build(ImagesWorkerFactory.class));
	}

}
