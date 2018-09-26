
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

import static com.anrisoftware.resources.binary.internal.maps.BinariesMapLogger.m.resourceAlreadyInMap;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.binary.external.BinaryResource;

/**
 * Logger messages for the {@link DefaultBinariesMap}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Singleton
final class BinariesMapLogger extends AbstractLogger {

    enum m {

        resourceAlreadyInMap("Resource in map: {}");

        private String name;

        private m(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    BinariesMapLogger() {
        super(DefaultBinariesMap.class);
    }

    void resourceAlreadyInMap(BinaryResource res) {
        debug(resourceAlreadyInMap, res);
    }

}