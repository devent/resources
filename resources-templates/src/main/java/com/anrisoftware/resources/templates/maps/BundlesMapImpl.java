/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-templates.
 *
 * resources-templates is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-templates is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-templates. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.templates.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.anrisoftware.resources.templates.api.BundlesMap;
import com.anrisoftware.resources.templates.api.TemplatesMap;
import com.anrisoftware.resources.templates.api.TemplatesMapFactory;

/**
 * Uses a Java hash map to store the texts for each resource bundle.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
class BundlesMapImpl implements BundlesMap {

	private final Map<ResourceBundle, TemplatesMap> map;

	private final TemplatesMapFactory textsFactory;

	@Inject
	BundlesMapImpl(TemplatesMapFactory textsFactory) {
		this.map = new HashMap<ResourceBundle, TemplatesMap>();
		this.textsFactory = textsFactory;
	}

	@Override
	public TemplatesMap getTemplates(ResourceBundle bundle) {
		TemplatesMap texts = map.get(bundle);
		if (texts == null) {
			texts = textsFactory.create();
			map.put(bundle, texts);
		}
		return texts;
	}
}
