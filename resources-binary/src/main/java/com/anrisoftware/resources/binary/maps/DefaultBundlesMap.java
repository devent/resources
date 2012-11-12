/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.binary.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.anrisoftware.resources.binary.api.BinariesMap;
import com.anrisoftware.resources.binary.api.BinariesMapFactory;
import com.anrisoftware.resources.binary.api.BundlesMap;

/**
 * Uses Java hash map to store the binaries for each resource bundle.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class DefaultBundlesMap implements BundlesMap {

	private final Map<ResourceBundle, BinariesMap> map;

	private final BinariesMapFactory factory;

	@Inject
	DefaultBundlesMap(BinariesMapFactory imagesFactory) {
		this.map = new HashMap<ResourceBundle, BinariesMap>();
		this.factory = imagesFactory;
	}

	@Override
	public BinariesMap getBinaries(String baseName, ResourceBundle bundle) {
		BinariesMap images = map.get(bundle);
		if (images == null) {
			images = factory.create();
			map.put(bundle, images);
		}
		return images;
	}

	@Override
	public String toString() {
		return map.toString();
	}
}
