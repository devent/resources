/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-templates.
 *
 * resources-templates is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-templates is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-templates. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.templates.templates;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import javax.inject.Named;

import com.anrisoftware.resources.GetBundle;
import com.anrisoftware.resources.GetBundleWithClassLoader;
import com.anrisoftware.resources.GetBundleWithClassLoaderAndControl;
import com.anrisoftware.resources.GetBundleWithControl;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.templates.api.BundlesMap;
import com.anrisoftware.resources.templates.api.TemplateResource;
import com.anrisoftware.resources.templates.api.TemplateResourceFactory;
import com.anrisoftware.resources.templates.api.Templates;
import com.anrisoftware.resources.templates.api.TemplatesFactory;
import com.anrisoftware.resources.templates.api.TemplatesMap;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Loads the template resources from the resource bundle.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TemplatesImpl implements Templates {

	private static final Map<Serializable, Serializable> EMPTY_ATTRIBUTES = new HashMap<Serializable, Serializable>();

	private final TemplatesImplLogger log;

	private final BundlesMap texts;

	private final TemplateResourceFactory resourceFactory;

	private final GetBundle getBundle;

	private final Properties properties;

	private final Map<Serializable, Serializable> attributes;

	/**
	 * @see TemplatesFactory#create(String)
	 */
	@AssistedInject
	TemplatesImpl(TemplatesImplLogger logger, BundlesMap texts,
			TemplateResourceFactory textResourceFactory,
			@Named("st-default-properties") Properties defaultProperties,
			@Assisted String baseName) {
		this(logger, texts, textResourceFactory, defaultProperties,
				EMPTY_ATTRIBUTES, new GetBundle(baseName));
	}

	/**
	 * @see TemplatesFactory#create(String, Map)
	 */
	@AssistedInject
	TemplatesImpl(TemplatesImplLogger logger, BundlesMap texts,
			TemplateResourceFactory textResourceFactory,
			@Named("st-default-properties") Properties defaultProperties,
			@Assisted String baseName,
			@Assisted Map<Serializable, Serializable> attributes) {
		this(logger, texts, textResourceFactory, defaultProperties, attributes,
				new GetBundle(baseName));
	}

	/**
	 * @see TemplatesFactory#create(String, ClassLoader)
	 */
	@AssistedInject
	TemplatesImpl(TemplatesImplLogger logger, BundlesMap texts,
			TemplateResourceFactory textResourceFactory,
			@Named("st-default-properties") Properties defaultProperties,
			@Assisted String baseName, @Assisted ClassLoader classLoader) {
		this(logger, texts, textResourceFactory, defaultProperties,
				EMPTY_ATTRIBUTES, new GetBundleWithClassLoader(baseName,
						classLoader));
	}

	/**
	 * @see TemplatesFactory#create(String, Map, ClassLoader)
	 */
	@AssistedInject
	TemplatesImpl(TemplatesImplLogger logger, BundlesMap texts,
			TemplateResourceFactory textResourceFactory,
			@Named("st-default-properties") Properties defaultProperties,
			@Assisted String baseName,
			@Assisted Map<Serializable, Serializable> attributes,
			@Assisted ClassLoader classLoader) {
		this(logger, texts, textResourceFactory, defaultProperties, attributes,
				new GetBundleWithClassLoader(baseName, classLoader));
	}

	/**
	 * @see TemplatesFactory#create(String, Control)
	 */
	@AssistedInject
	TemplatesImpl(TemplatesImplLogger logger, BundlesMap texts,
			TemplateResourceFactory textResourceFactory,
			@Named("st-default-properties") Properties defaultProperties,
			@Assisted String baseName, @Assisted ResourceBundle.Control control) {
		this(logger, texts, textResourceFactory, defaultProperties,
				EMPTY_ATTRIBUTES, new GetBundleWithControl(baseName, control));
	}

	/**
	 * @see TemplatesFactory#create(String, Map, Control)
	 */
	@AssistedInject
	TemplatesImpl(TemplatesImplLogger logger, BundlesMap texts,
			TemplateResourceFactory textResourceFactory,
			@Named("st-default-properties") Properties defaultProperties,
			@Assisted String baseName,
			@Assisted Map<Serializable, Serializable> attributes,
			@Assisted ResourceBundle.Control control) {
		this(logger, texts, textResourceFactory, defaultProperties, attributes,
				new GetBundleWithControl(baseName, control));
	}

	/**
	 * @see TemplatesFactory#create(String, ClassLoader, Control)
	 */
	@AssistedInject
	TemplatesImpl(TemplatesImplLogger logger, BundlesMap texts,
			TemplateResourceFactory textResourceFactory,
			@Named("st-default-properties") Properties defaultProperties,
			@Assisted String baseName, @Assisted ClassLoader classLoader,
			@Assisted ResourceBundle.Control control) {
		this(logger, texts, textResourceFactory, defaultProperties,
				EMPTY_ATTRIBUTES, new GetBundleWithClassLoaderAndControl(
						baseName, classLoader, control));
	}

	/**
	 * @see TemplatesFactory#create(String, Map, ClassLoader, Control)
	 */
	@AssistedInject
	TemplatesImpl(TemplatesImplLogger logger, BundlesMap texts,
			TemplateResourceFactory textResourceFactory,
			@Named("st-default-properties") Properties defaultProperties,
			@Assisted String baseName,
			@Assisted Map<Serializable, Serializable> attributes,
			@Assisted ClassLoader classLoader,
			@Assisted ResourceBundle.Control control) {
		this(logger, texts, textResourceFactory, defaultProperties, attributes,
				new GetBundleWithClassLoaderAndControl(baseName, classLoader,
						control));
	}

	private TemplatesImpl(TemplatesImplLogger logger, BundlesMap texts,
			TemplateResourceFactory textResourceFactory,
			Properties defaultProperties,
			Map<Serializable, Serializable> attributes, GetBundle getBundle) {
		this.log = logger;
		this.texts = texts;
		this.resourceFactory = textResourceFactory;
		this.properties = defaultProperties;
		this.getBundle = getBundle;
		this.attributes = attributes;
	}

	@Override
	public String getBaseName() {
		return getBundle.getBaseName();
	}

	@Override
	public ClassLoader getClassLoader() {
		return getBundle.getClassLoader();
	}

	@Override
	public Control getControl() {
		return getBundle.getControl();
	}

	@Override
	public TemplateResource getResource(String name) throws ResourcesException {
		return getResource(name, Locale.getDefault());
	}

	@Override
	public TemplateResource getResource(String name, Locale locale)
			throws ResourcesException {
		ResourceBundle bundle = getBundle.bundleFor(locale);
		log.loadedResourceBundle(name, bundle);
		TemplateResource res = lazyLoadResource(name, bundle);
		log.checkHaveResource(res, name, locale, bundle);
		return res;
	}

	private TemplateResource lazyLoadResource(String name, ResourceBundle bundle) {
		String location = bundle.getString(name);
		TemplatesMap map = texts.getTemplates(bundle);
		TemplateResource text = map.getTemplate(name);
		if (text == null) {
			text = loadTextResource(bundle, map, name, location);
		}
		return text;
	}

	private TemplateResource loadTextResource(ResourceBundle bundle,
			TemplatesMap map, String name, String value)
			throws ResourcesException {
		URL url = parseURL(value);
		Locale locale = bundle.getLocale();
		TemplateResource text = loadTemplate(locale, map, name, url);
		log.checkTemplateLoaded(map.haveText(name), name, locale, bundle);
		return text;
	}

	private URL parseURL(String string) {
		try {
			return new URL(string);
		} catch (MalformedURLException e) {
			URL url = getClassLoader().getResource(string);
			return log.checkResourceURL(url, string);
		}
	}

	private TemplateResource loadTemplate(Locale locale, TemplatesMap map,
			String name, URL url) {
		if (url != null) {
			TemplateResource text;
			text = resourceFactory.create(name, locale, url, properties,
					attributes);
			map.putTemplate(name, text);
			return text;
		} else {
			return null;
		}
	}

}
