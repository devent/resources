package com.anrisoftware.resources.templates.templates;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
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

	private final TemplatesImplLogger log;

	private final BundlesMap texts;

	private final TemplateResourceFactory resourceFactory;

	private final GetBundle getBundle;

	private final Properties properties;

	@AssistedInject
	TemplatesImpl(TemplatesImplLogger logger, BundlesMap texts,
			TemplateResourceFactory textResourceFactory,
			@Named("st-default-properties") Properties defaultProperties,
			@Assisted String baseName) {
		this(logger, texts, textResourceFactory, defaultProperties,
				new GetBundle(baseName));
	}

	@AssistedInject
	TemplatesImpl(TemplatesImplLogger logger, BundlesMap texts,
			TemplateResourceFactory textResourceFactory,
			@Named("st-default-properties") Properties defaultProperties,
			@Assisted String baseName, @Assisted ClassLoader classLoader) {
		this(logger, texts, textResourceFactory, defaultProperties,
				new GetBundleWithClassLoader(baseName, classLoader));
	}

	@AssistedInject
	TemplatesImpl(TemplatesImplLogger logger, BundlesMap texts,
			TemplateResourceFactory textResourceFactory,
			@Named("st-default-properties") Properties defaultProperties,
			@Assisted String baseName, @Assisted ResourceBundle.Control control) {
		this(logger, texts, textResourceFactory, defaultProperties,
				new GetBundleWithControl(baseName, control));
	}

	@AssistedInject
	TemplatesImpl(TemplatesImplLogger logger, BundlesMap texts,
			TemplateResourceFactory textResourceFactory,
			@Named("st-default-properties") Properties defaultProperties,
			@Assisted String baseName, @Assisted ClassLoader classLoader,
			@Assisted ResourceBundle.Control control) {
		this(logger, texts, textResourceFactory, defaultProperties,
				new GetBundleWithClassLoaderAndControl(baseName, classLoader,
						control));
	}

	private TemplatesImpl(TemplatesImplLogger logger, BundlesMap texts,
			TemplateResourceFactory textResourceFactory,
			Properties defaultProperties, GetBundle getBundle) {
		this.log = logger;
		this.texts = texts;
		this.resourceFactory = textResourceFactory;
		this.properties = defaultProperties;
		this.getBundle = getBundle;
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
		log.checkHaveResource(res, bundle, name, locale);
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
		log.checkTemplateLoaded(map.haveText(name), bundle, name);
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
			text = resourceFactory.create(name, locale, url, properties);
			map.putTemplate(name, text);
			return text;
		} else {
			return null;
		}
	}

}
