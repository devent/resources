/*
 * Copyright 2012-2022 Erwin Müller <erwin.mueller@anrisoftware.com>
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
package com.anrisoftware.resources.texts.internal.maps;


import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.anrisoftware.resources.texts.external.TextsBundlesMap;
import com.anrisoftware.resources.texts.external.TextsMap;
import com.anrisoftware.resources.texts.external.TextsMapFactory;

class TextsBundlesMapImpl implements TextsBundlesMap {

	private final Map<ResourceBundle, TextsMap> map;

	private final TextsMapFactory textsFactory;

	@Inject
	TextsBundlesMapImpl(TextsMapFactory textsFactory) {
		this.map = new HashMap<ResourceBundle, TextsMap>();
		this.textsFactory = textsFactory;
	}

	@Override
	public TextsMap getTexts(ResourceBundle bundle) {
		TextsMap texts = map.get(bundle);
		if (texts == null) {
			texts = textsFactory.create();
			map.put(bundle, texts);
		}
		return texts;
	}
}
