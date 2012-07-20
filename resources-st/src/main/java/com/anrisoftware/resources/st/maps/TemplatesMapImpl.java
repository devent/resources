package com.anrisoftware.resources.st.maps;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.anrisoftware.resources.api.TemplateResource;
import com.anrisoftware.resources.st.api.TemplatesMap;
import com.google.common.collect.Maps;

/**
 * <p>
 * Uses a {@link HashMap} to store the template resources identified by their
 * name.
 * </p>
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
		this.texts = Maps.newHashMap();
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