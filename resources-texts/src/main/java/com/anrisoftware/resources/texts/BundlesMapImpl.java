package com.anrisoftware.resources.texts;

import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.anrisoftware.resources.texts.api.BundlesMap;
import com.anrisoftware.resources.texts.api.TextsMap;
import com.anrisoftware.resources.texts.api.TextsMapFactory;
import com.google.common.collect.Maps;

/**
 * <p>
 * A map of texts for each resource bundle.
 * </p>
 * <p>
 * Lazy create a new texts map for a new resource bundle.
 * </p>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
class BundlesMapImpl implements BundlesMap {

	private final Map<ResourceBundle, TextsMap> map;

	private final TextsMapFactory textsFactory;

	@Inject
	BundlesMapImpl(TextsMapFactory textsFactory) {
		this.map = Maps.newHashMap();
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
