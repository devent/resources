package com.anrisoftware.resources.texts.maps;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.anrisoftware.resources.texts.api.TextResource;
import com.anrisoftware.resources.texts.api.TextsMap;

/**
 * Uses the Java hash map to store the text resources identified by their name.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
class TextsMapImpl implements TextsMap {

	private final TextsMapLogger log;

	private final Map<String, TextResource> texts;

	@Inject
	TextsMapImpl(TextsMapLogger logger) {
		this.log = logger;
		this.texts = new HashMap<String, TextResource>();
	}

	/**
	 * Adds a new text resource to the map. Already added text resource with the
	 * name are discarded.
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @param text
	 *            the {@link TextResource} to add.
	 */
	@Override
	public void putText(String name, TextResource text) {
		if (!texts.containsKey(name)) {
			texts.put(name, text);
		} else {
			log.textAlreadyInMap(name);
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
	 */
	@Override
	public TextResource getText(String name) {
		TextResource resource = texts.get(name);
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
	@Override
	public boolean haveText(String name) {
		return texts.containsKey(name);
	}
}
