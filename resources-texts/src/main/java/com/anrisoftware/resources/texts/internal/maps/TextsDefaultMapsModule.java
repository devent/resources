
package com.anrisoftware.resources.texts.internal.maps;

/*-
 * #%L
 * Resources :: Text
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

import com.anrisoftware.resources.texts.external.TextsBundlesMap;
import com.anrisoftware.resources.texts.external.TextsBundlesMapFactory;
import com.anrisoftware.resources.texts.external.TextsMap;
import com.anrisoftware.resources.texts.external.TextsMapFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the text resources that is using Java hash map for the texts maps.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public class TextsDefaultMapsModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(TextsMap.class,
                TextsMapImpl.class).build(TextsMapFactory.class));
        install(new FactoryModuleBuilder().implement(TextsBundlesMap.class,
                TextsBundlesMapImpl.class).build(TextsBundlesMapFactory.class));
    }

}
