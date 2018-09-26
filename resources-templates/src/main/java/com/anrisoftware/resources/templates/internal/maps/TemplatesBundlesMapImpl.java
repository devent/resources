
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
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.anrisoftware.resources.templates.external.TemplatesBundlesMap;
import com.anrisoftware.resources.templates.external.TemplatesMap;
import com.anrisoftware.resources.templates.external.TemplatesMapFactory;

/**
 * Uses a Java hash map to store the texts for each resource bundle.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
class TemplatesBundlesMapImpl implements TemplatesBundlesMap {

	private final Map<ResourceBundle, TemplatesMap> map;

	private final TemplatesMapFactory textsFactory;

	@Inject
	TemplatesBundlesMapImpl(TemplatesMapFactory textsFactory) {
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
