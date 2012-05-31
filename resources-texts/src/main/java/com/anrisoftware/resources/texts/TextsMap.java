package com.anrisoftware.resources.texts;

import java.util.Locale;
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

	private final Map<String, Map<Locale, TextResource>> texts;

	@Inject
	TextsMap(TextsMapLogger logger) {
		this.log = logger;
		this.texts = Maps.newHashMap();
	}

	/**
	 * Adds a new text resource to the map. Only one resource for name and
	 * language.
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @param text
	 *            the {@link TextResource} to add.
	 */
	public void putText(String name, TextResource text) {
		Map<Locale, TextResource> resources = resourcesMap(name);
		if (!resources.containsKey(name)) {
			resources.put(text.getLocale(), text);
		} else {
			log.imageAlreadyInMap(this, text);
		}
	}

	private Map<Locale, TextResource> resourcesMap(String name) {
		Map<Locale, TextResource> resources = texts.get(name);
		if (resources == null) {
			resources = Maps.newHashMap();
			texts.put(name, resources);
		}
		return resources;
	}

	/**
	 * Returns the text resource with the name and language. If no resource can
	 * be found with the given language, the resource with the default language
	 * will be returned.
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @param locale
	 *            the language {@link Locale}.
	 * 
	 * @return the {@link TextResource} with the name and language, the resource
	 *         with the default language, or <code>null</code>.
	 */
	public TextResource getText(String name, Locale locale) {
		Map<Locale, TextResource> resources = resourcesMap(name);
		TextResource resource = null;
		if (resources != null) {
			resource = textFrom(resources, locale);
		}
		log.checkHaveText(this, resource, name, locale);
		return resource;
	}

	private TextResource textFrom(Map<Locale, TextResource> resources,
			Locale locale) {
		TextResource text = resources.get(localeLanguage(locale));
		text = text == null ? resources.get(null) : text;
		return text;
	}

	private Locale localeLanguage(Locale locale) {
		String language = locale.getLanguage();
		return new Locale(language);
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
