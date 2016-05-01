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
package com.anrisoftware.resources.binary.internal.maps;

import static java.util.Collections.synchronizedMap;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Inject;

import com.anrisoftware.resources.binary.external.BinariesMap;
import com.anrisoftware.resources.binary.external.BinaryResource;
import com.anrisoftware.resources.external.ResourcesException;

/**
 * Stores the binary resources in a Java hash map.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@ThreadSafe
public class DefaultBinariesMap implements BinariesMap {

    private final Map<String, BinaryResource> map;

    @Inject
    private BinariesMapLogger log;

    /**
     * Creates the images map.
     */
    @Inject
    DefaultBinariesMap() {
        this.map = synchronizedMap(new HashMap<String, BinaryResource>());
    }

    @Override
    public void putBinary(BinaryResource resource) throws ResourcesException {
        String name = resource.getName();
        if (!map.containsKey(name)) {
            map.put(name, resource);
        } else {
            log.resourceAlreadyInMap(resource);
        }
    }

    @Override
    public BinaryResource getBinary(String name) {
        return map.get(name);
    }

    @Override
    public boolean haveBinary(String name) {
        return map.containsKey(name);
    }

    @Override
    public String toString() {
        return map.toString();
    }

}
