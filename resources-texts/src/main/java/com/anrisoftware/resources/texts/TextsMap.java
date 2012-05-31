package com.anrisoftware.resources.texts;

import java.util.Map;

import javax.inject.Inject;

import com.anrisoftware.resources.api.TextResource;
import com.google.common.collect.Maps;

/**
 * Puts {@link TextResource}s and retrieves them. The text resources are
 * identified by their name and locale. No dublicates are allowed.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TextsMap {

	private final TextsMapLogger log;

	private final Map<String, TextResource> texts;

	@Inject
	TextsMap(TextsMapLogger logger) {
		this.log = logger;
		this.texts = Maps.newHashMap();
	}

	/**
	 * Adds a new text resource to the map. Only one resource for the given
	 * name.
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @param text
	 *            the {@link TextResource} to add.
	 */
	public void putText(String name, TextResource text) {
		if (!texts.containsKey(name)) {
			texts.put(name, text);
		}
	}

	/**
	 * Returns the text resource with the name.
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @return the {@link TextResource} with the name and language, the resource
	 *         with the default language, or <code>null</code>.
	 * 
	 * @since 1.1
	 */
	public TextResource getText(String name) {
		TextResource resource = texts.get(name);
		log.checkHaveText(this, resource, name);
		return resource;
	}

	/**
	 * Check if the map contains a text with the name.
	 * 
	 * @param name
	 *            the name of the text.
	 * 
	 * @return <code>true</code> if the map contains the text or
	 *         <code>false</code> if not.
	 */
	public boolean haveText(String name) {
		return texts.containsKey(name);
	}
}
