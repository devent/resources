/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.images.internal.mapcached;

import com.anrisoftware.resources.images.external.BundlesMap;
import com.anrisoftware.resources.images.external.BundlesMapFactory;
import com.anrisoftware.resources.images.external.ImagesMap;
import com.anrisoftware.resources.images.external.ImagesMapFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the Java hash map as the image resources map. The map caches all
 * resolutions and all resized images.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.17
 */
public class ResourcesImagesCachedMapModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(ImagesMap.class,
				ImagesMapImpl.class).build(ImagesMapFactory.class));
        install(new FactoryModuleBuilder().implement(BundlesMap.class,
                BundlesMapImpl.class).build(BundlesMapFactory.class));
	}

}
