/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-texts.
 *
 * resources-texts is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-texts is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-texts. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.texts.texts;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import javax.inject.Named;

import org.apache.commons.lang3.text.StrTokenizer;

import com.anrisoftware.resources.external.GetBundle;
import com.anrisoftware.resources.external.GetBundleWithClassLoader;
import com.anrisoftware.resources.external.GetBundleWithClassLoaderAndControl;
import com.anrisoftware.resources.external.GetBundleWithControl;
import com.anrisoftware.resources.external.ResourcesException;
import com.anrisoftware.resources.texts.api.BundlesMap;
import com.anrisoftware.resources.texts.api.TextResource;
import com.anrisoftware.resources.texts.api.TextResourceFactory;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsMap;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Loads the text resources from the resource bundle.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TextsImpl implements Texts {

	private final TextsImplLogger log;

	private final BundlesMap texts;

	private final TextResourceFactory textResourceFactory;

	private final GetBundle getBundle;

	private final Charset defaultCharset;

	/**
	 * Sets the default character set and the base name of the text resources.
	 * 
	 * @param logger
	 *            the {@link TextsImplLogger} for the logging messages.
	 * 
	 * @param texts
	 *            the {@link BundlesMap} that cache the resource bundles.
	 * 
	 * @param textResourceFactory
	 *            the {@link TextResourceFactory} that creates the text
	 *            resources.
	 * 
	 * @param defaultCharset
	 *            the default {@link Charset} for the text resources.
	 * 
	 * @param baseName
	 *            the base name for the text resources.
	 */
	@AssistedInject
	TextsImpl(TextsImplLogger logger, BundlesMap texts,
			TextResourceFactory textResourceFactory,
			@Named("texts-default-charset") Charset defaultCharset,
			@Assisted String baseName) {
		this(logger, texts, textResourceFactory, defaultCharset, new GetBundle(
				baseName));
	}

	/**
	 * Sets the default character set, the base name and the class loader for
	 * the text resources.
	 * 
	 * @param logger
	 *            the {@link TextsImplLogger} for the logging messages.
	 * 
	 * @param texts
	 *            the {@link BundlesMap} that cache the resource bundles.
	 * 
	 * @param textResourceFactory
	 *            the {@link TextResourceFactory} that creates the text
	 *            resources.
	 * 
	 * @param defaultCharset
	 *            the default {@link Charset} for the text resources.
	 * 
	 * @param baseName
	 *            the base name for the text resources.
	 * 
	 * @param classLoader
	 *            the {@link ClassLoader} to load the text resources.
	 */
	@AssistedInject
	TextsImpl(TextsImplLogger logger, BundlesMap texts,
			TextResourceFactory textResourceFactory,
			@Named("texts-default-charset") Charset defaultCharset,
			@Assisted String baseName, @Assisted ClassLoader classLoader) {
		this(logger, texts, textResourceFactory, defaultCharset,
				new GetBundleWithClassLoader(baseName, classLoader));
	}

	/**
	 * Sets the default character set, the base name and the resource bundle
	 * control for the text resources.
	 * 
	 * @param logger
	 *            the {@link TextsImplLogger} for the logging messages.
	 * 
	 * @param texts
	 *            the {@link BundlesMap} that cache the resource bundles.
	 * 
	 * @param textResourceFactory
	 *            the {@link TextResourceFactory} that creates the text
	 *            resources.
	 * 
	 * @param defaultCharset
	 *            the default {@link Charset} for the text resources.
	 * 
	 * @param baseName
	 *            the base name for the text resources.
	 * 
	 * @param control
	 *            the {@link ResourceBundle.Control} for the text resources.
	 */
	@AssistedInject
	TextsImpl(TextsImplLogger logger, BundlesMap texts,
			TextResourceFactory textResourceFactory,
			@Named("texts-default-charset") Charset defaultCharset,
			@Assisted String baseName, @Assisted ResourceBundle.Control control) {
		this(logger, texts, textResourceFactory, defaultCharset,
				new GetBundleWithControl(baseName, control));
	}

	/**
	 * Sets the default character set, the base name, the class loader and the
	 * resource bundle control for the text resources.
	 * 
	 * @param logger
	 *            the {@link TextsImplLogger} for the logging messages.
	 * 
	 * @param texts
	 *            the {@link BundlesMap} that cache the resource bundles.
	 * 
	 * @param textResourceFactory
	 *            the {@link TextResourceFactory} that creates the text
	 *            resources.
	 * 
	 * @param defaultCharset
	 *            the default {@link Charset} for the text resources.
	 * 
	 * @param baseName
	 *            the base name for the text resources.
	 * 
	 * @param classLoader
	 *            the {@link ClassLoader} to load the text resources.
	 * 
	 * @param control
	 *            the {@link ResourceBundle.Control} for the text resources.
	 */
	@AssistedInject
	TextsImpl(TextsImplLogger logger, BundlesMap texts,
			TextResourceFactory textResourceFactory,
			@Named("texts-default-charset") Charset defaultCharset,
			@Assisted String baseName, @Assisted ClassLoader classLoader,
			@Assisted ResourceBundle.Control control) {
		this(logger, texts, textResourceFactory, defaultCharset,
				new GetBundleWithClassLoaderAndControl(baseName, classLoader,
						control));
	}

	private TextsImpl(TextsImplLogger logger, BundlesMap texts,
			TextResourceFactory textResourceFactory, Charset defaultCharset,
			GetBundle getBundle) {
		this.log = logger;
		this.texts = texts;
		this.textResourceFactory = textResourceFactory;
		this.defaultCharset = defaultCharset;
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
	public TextResource getResource(String name) throws ResourcesException {
		return getResource(name, Locale.getDefault());
	}

	@Override
	public TextResource getResource(String name, Locale locale)
			throws ResourcesException {
		ResourceBundle bundle = getBundle.bundleFor(locale);
		log.loadedResourceBundle(name, bundle);
		TextResource text = lazyLoadResource(name, bundle);
		log.checkHaveResource(text, name, locale, bundle);
		return text;
	}

	private TextResource lazyLoadResource(String name, ResourceBundle bundle) {
		String location = bundle.getString(name);
		TextsMap map = texts.getTexts(bundle);
		TextResource text = map.getText(name);
		if (text == null) {
			text = loadTextResource(bundle, map, name, location);
		}
		return text;
	}

	private TextResource loadTextResource(ResourceBundle bundle, TextsMap map,
			String name, String value) throws ResourcesException {
		StrTokenizer tokenizer = new StrTokenizer(value, ',', '\\');
		String[] tokens = tokenizer.getTokenArray();
		Charset charset = parseCharset(tokens);
		URL url = parseUrl(tokens);
		Locale locale = bundle.getLocale();
		TextResource text = loadText(locale, map, name, charset, url);
		log.checkTextLoaded(map.haveText(name), name, locale, bundle);
		return text;
	}

	private Charset parseCharset(String[] tokens) {
		if (tokens.length == 2) {
			String name = tokens[0];
			return Charset.forName(name);
		} else {
			return defaultCharset;
		}
	}

	private URL parseUrl(String[] tokens) {
		if (tokens.length == 2) {
			return createURL(tokens[1]);
		} else {
			return createURL(tokens[0]);
		}
	}

	private URL createURL(String urlString) {
		try {
			return new URL(urlString);
		} catch (MalformedURLException e) {
			URL url = getClassLoader().getResource(urlString);
			return log.checkResourceURL(url, urlString);
		}
	}

	private TextResource loadText(Locale locale, TextsMap map, String name,
			Charset charset, URL url) {
		if (url != null) {
			TextResource text = textResourceFactory.create(name, locale, url,
					charset);
			map.putText(name, text);
			return text;
		} else {
			return null;
		}
	}

}
