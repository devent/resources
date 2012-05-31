package com.anrisoftware.resources.texts;

import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

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
class BundlesMap {

	private final Map<ResourceBundle, TextsMap> map;

	private final TextsMapFactory textsFactory;

	@Inject
	BundlesMap(TextsMapFactory textsFactory) {
		this.map = Maps.newHashMap();
		this.textsFactory = textsFactory;
	}

	/**
	 * <p>
	 * Returns the texts for the specified resource bundle.
	 * </p>
	 * <p>
	 * If no texts are found for the specified resource bundle a new
	 * {@link TextsMap} is created.
	 * </p>
	 * 
	 * @param bundle
	 *            the {@link ResourceBundle}.
	 * 
	 * @return the {@link TextsMap} for the resource bundle.
	 */
	public TextsMap getTexts(ResourceBundle bundle) {
		TextsMap texts = map.get(bundle);
		if (texts == null) {
			texts = textsFactory.create();
			map.put(bundle, texts);
		}
		return texts;
	}
}
