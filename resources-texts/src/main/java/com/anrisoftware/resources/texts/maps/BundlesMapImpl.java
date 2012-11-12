package com.anrisoftware.resources.texts.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.anrisoftware.resources.texts.api.BundlesMap;
import com.anrisoftware.resources.texts.api.TextsMap;
import com.anrisoftware.resources.texts.api.TextsMapFactory;

/**
 * Uses a Java hash map to store the texts for each resource bundle.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
class BundlesMapImpl implements BundlesMap {

	private final Map<ResourceBundle, TextsMap> map;

	private final TextsMapFactory textsFactory;

	@Inject
	BundlesMapImpl(TextsMapFactory textsFactory) {
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
