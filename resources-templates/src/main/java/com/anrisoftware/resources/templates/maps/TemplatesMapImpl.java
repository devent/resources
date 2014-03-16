/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
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

import javax.inject.Inject;

import com.anrisoftware.resources.templates.api.TemplateResource;
import com.anrisoftware.resources.templates.api.TemplatesMap;

/**
 * Uses a Java hash map to store the template resources identified by their
 * name.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TemplatesMapImpl implements TemplatesMap {

	private final TemplatesMapLogger log;

	private final Map<String, TemplateResource> texts;

	@Inject
	TemplatesMapImpl(TemplatesMapLogger logger) {
		this.log = logger;
		this.texts = new HashMap<String, TemplateResource>();
	}

	@Override
	public void putTemplate(String name, TemplateResource text) {
		if (!texts.containsKey(name)) {
			texts.put(name, text);
		} else {
			log.textAlreadyInMap(name);
		}
	}

	@Override
	public TemplateResource getTemplate(String name) {
		TemplateResource resource = texts.get(name);
		return resource;
	}

	@Override
	public boolean haveText(String name) {
		return texts.containsKey(name);
	}
}
