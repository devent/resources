/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-binary.
 *
 * resources-binary is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-binary is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-binary. If not, see <http://www.gnu.org/licenses/>.
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
