/*
 * Copyright 2012-2025 Erwin Müller <erwin.mueller@anrisoftware.com>
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
import java.util.ResourceBundle;

import javax.annotation.concurrent.ThreadSafe;
import jakarta.inject.Inject;

import com.anrisoftware.resources.binary.external.BinariesMap;
import com.anrisoftware.resources.binary.external.BinariesMapFactory;
import com.anrisoftware.resources.binary.external.BinariesBundlesMap;

@ThreadSafe
public class DefaultBundlesMap implements BinariesBundlesMap {

    private final Map<ResourceBundle, BinariesMap> map;

    private final BinariesMapFactory factory;

    @Inject
    DefaultBundlesMap(BinariesMapFactory imagesFactory) {
        this.map = synchronizedMap(new HashMap<ResourceBundle, BinariesMap>());
        this.factory = imagesFactory;
    }

    @Override
    public BinariesMap getBinaries(String baseName, ResourceBundle bundle) {
        BinariesMap resources = map.get(bundle);
        if (resources == null) {
            resources = factory.create();
            map.put(bundle, resources);
        }
        return resources;
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
