
package com.anrisoftware.resources.templates.internal.maps;

/*-
 * #%L
 * Resources :: Templates
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

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.anrisoftware.resources.templates.external.TemplateResource;
import com.anrisoftware.resources.templates.external.TemplatesMap;

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