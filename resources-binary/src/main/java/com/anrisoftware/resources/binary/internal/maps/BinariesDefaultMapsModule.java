
package com.anrisoftware.resources.binary.internal.maps;

/*-
 * #%L
 * Resources :: Binary
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

import com.anrisoftware.resources.binary.external.BinariesMap;
import com.anrisoftware.resources.binary.external.BinariesMapFactory;
import com.anrisoftware.resources.binary.external.BinariesBundlesMap;
import com.anrisoftware.resources.binary.external.BinariesBundlesMapFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the Java hash map as the binary resources map.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class BinariesDefaultMapsModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(BinariesMap.class,
                DefaultBinariesMap.class).build(BinariesMapFactory.class));
        install(new FactoryModuleBuilder().implement(BinariesBundlesMap.class,
                DefaultBundlesMap.class).build(BinariesBundlesMapFactory.class));
    }

}