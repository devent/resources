/*
 * Copyright 2012-2023 Erwin Müller <erwin.mueller@anrisoftware.com>
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
package com.anrisoftware.resources.images.internal.mapcachedresolutions;


import com.anrisoftware.resources.images.external.ImagesBundlesMap;
import com.anrisoftware.resources.images.external.ImagesBundlesMapFactory;
import com.anrisoftware.resources.images.external.ImagesMap;
import com.anrisoftware.resources.images.external.ImagesMapFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class ResourcesImagesCachedResolutionsMapModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(ImagesMap.class,
                ImagesMapImpl.class).build(ImagesMapFactory.class));
        install(new FactoryModuleBuilder().implement(ImagesBundlesMap.class,
                BundlesMapImpl.class).build(ImagesBundlesMapFactory.class));
    }

}
