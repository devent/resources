package com.anrisoftware.resources.texts;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import javax.inject.Named;

import org.apache.commons.lang.text.StrTokenizer;

import com.anrisoftware.resources.GetBundle;
import com.anrisoftware.resources.GetBundleWithClassLoader;
import com.anrisoftware.resources.GetBundleWithClassLoaderAndControl;
import com.anrisoftware.resources.GetBundleWithControl;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.api.TextResource;
import com.anrisoftware.resources.api.TextResourceFactory;
import com.anrisoftware.resources.api.Texts;
import com.anrisoftware.resources.texts.api.BundlesMap;
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

	@AssistedInject
	TextsImpl(TextsImplLogger logger, BundlesMap texts,
			TextResourceFactory textResourceFactory,
			@Named("texts-default-charset") Charset defaultCharset,
			@Assisted String baseName) {
		this(logger, texts, textResourceFactory, defaultCharset, new GetBundle(
				baseName));
	}

	@AssistedInject
	TextsImpl(TextsImplLogger logger, BundlesMap texts,
			TextResourceFactory textResourceFactory,
			@Named("texts-default-charset") Charset defaultCharset,
			@Assisted String baseName, @Assisted ClassLoader classLoader) {
		this(logger, texts, textResourceFactory, defaultCharset,
				new GetBundleWithClassLoader(baseName, classLoader));
	}

	@AssistedInject
	TextsImpl(TextsImplLogger logger, BundlesMap texts,
			TextResourceFactory textResourceFactory,
			@Named("texts-default-charset") Charset defaultCharset,
			@Assisted String baseName, @Assisted ResourceBundle.Control control) {
		this(logger, texts, textResourceFactory, defaultCharset,
				new GetBundleWithControl(baseName, control));
	}

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
	public TextResource textResource(String name) throws ResourcesException {
		return textResource(name, Locale.getDefault());
	}

	@Override
	public TextResource textResource(String name, Locale locale)
			throws ResourcesException {
		ResourceBundle bundle = getBundle.bundleFor(locale);
		log.loadedResourceBundle(name, bundle);
		TextResource text = lazyLoadResource(name, bundle);
		log.checkHaveResource(text, bundle, name, locale);
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
		log.checkTextLoaded(map.haveText(name), bundle, name);
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
			URL url = TextsImpl.class.getClassLoader().getResource(urlString);
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
