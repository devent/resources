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
