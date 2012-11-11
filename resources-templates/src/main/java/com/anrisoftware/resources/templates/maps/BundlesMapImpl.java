package com.anrisoftware.resources.templates.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.anrisoftware.resources.templates.api.BundlesMap;
import com.anrisoftware.resources.templates.api.TemplatesMap;
import com.anrisoftware.resources.templates.api.TemplatesMapFactory;

/**
 * Uses a Java hash map to store the texts for each resource bundle.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
class BundlesMapImpl implements BundlesMap {

	private final Map<ResourceBundle, TemplatesMap> map;

	private final TemplatesMapFactory textsFactory;

	@Inject
	BundlesMapImpl(TemplatesMapFactory textsFactory) {
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
