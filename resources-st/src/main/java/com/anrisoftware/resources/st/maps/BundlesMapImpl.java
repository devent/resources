package com.anrisoftware.resources.st.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.anrisoftware.resources.st.api.BundlesMap;
import com.anrisoftware.resources.st.api.TemplatesMap;
import com.anrisoftware.resources.st.api.TemplatesMapFactory;
import com.google.common.collect.Maps;

/**
 * <p>
 * Uses a {@link HashMap} to store the texts for each resource bundle.
 * </p>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
class BundlesMapImpl implements BundlesMap {

	private final Map<ResourceBundle, TemplatesMap> map;

	private final TemplatesMapFactory textsFactory;

	@Inject
	BundlesMapImpl(TemplatesMapFactory textsFactory) {
		this.map = Maps.newHashMap();
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
