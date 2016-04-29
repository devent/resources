/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-images.
 *
 * resources-images is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-images is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-images. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.images.internal.mapcachedresolutions;

import com.anrisoftware.resources.images.external.BundlesMap;
import com.anrisoftware.resources.images.external.BundlesMapFactory;
import com.anrisoftware.resources.images.external.ImagesMap;
import com.anrisoftware.resources.images.external.ImagesMapFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the Java hash map as the image resources map. The map caches only
 * images with different resolutions.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.17
 */
public class ResourcesImagesCachedResolutionsMapModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(ImagesMap.class,
                ImagesMapImpl.class).build(ImagesMapFactory.class));
        install(new FactoryModuleBuilder().implement(BundlesMap.class,
                BundlesMapImpl.class).build(BundlesMapFactory.class));
    }

}
