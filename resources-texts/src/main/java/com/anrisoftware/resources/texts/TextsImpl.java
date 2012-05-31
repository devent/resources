package com.anrisoftware.resources.texts;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import javax.annotation.Nullable;

import org.apache.commons.lang.text.StrTokenizer;

import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.api.TextResource;
import com.anrisoftware.resources.api.TextResourceFactory;
import com.anrisoftware.resources.api.Texts;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

class TextsImpl implements Texts {

	private final TextsImplLogger log;

	private final BundlesMap texts;

	private final TextResourceFactory textResourceFactory;

	private final GetBundle getBundle;

	private Charset defaultCharset;

	@AssistedInject
	TextsImpl(TextsImplLogger logger, BundlesMap texts,
			TextResourceFactory textResourceFactory, @Assisted String baseName) {
		this(logger, texts, textResourceFactory, new GetBundle(baseName));
	}

	@AssistedInject
	TextsImpl(TextsImplLogger logger, BundlesMap texts,
			TextResourceFactory textResourceFactory, @Assisted String baseName,
			@Assisted ClassLoader classLoader) {
		this(logger, texts, textResourceFactory, new GetBundleWithClassLoader(
				baseName, classLoader));
	}

	@AssistedInject
	TextsImpl(TextsImplLogger logger, BundlesMap texts,
			TextResourceFactory textResourceFactory, @Assisted String baseName,
			@Assisted ResourceBundle.Control control) {
		this(logger, texts, textResourceFactory, new GetBundleWithControl(
				baseName, control));
	}

	@AssistedInject
	TextsImpl(TextsImplLogger logger, BundlesMap texts,
			TextResourceFactory textResourceFactory, @Assisted String baseName,
			@Assisted @Nullable ClassLoader classLoader,
			@Assisted @Nullable ResourceBundle.Control control) {
		this(logger, texts, textResourceFactory,
				new GetBundleWithClassLoaderAndControl(baseName, classLoader,
						control));
	}

	private TextsImpl(TextsImplLogger logger, BundlesMap texts,
			TextResourceFactory textResourceFactory, GetBundle getBundle) {
		this.log = logger;
		this.texts = texts;
		this.textResourceFactory = textResourceFactory;
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
		String location = bundle.getString(name);
		TextsMap map = texts.getTexts(bundle);
		loadTextResource(bundle, map, name, location);

		TextResource text = map.getText(name, locale);
		log.checkHaveResource(text, bundle, name, locale);
		return text;
	}

	private TextsMap loadTextResource(ResourceBundle bundle, TextsMap texts,
			String name, String value) throws ResourcesException {
		StrTokenizer tokenizer = new StrTokenizer(value, ',', '\\');
		String[] tokens = tokenizer.getTokenArray();
		Charset charset = parseCharset(tokens);
		URL url = parseUrl(tokens);
		loadText(bundle, texts, name, charset, url);
		log.checkTextLoaded(texts.haveText(name), bundle, name);
		return texts;
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

	private void loadText(ResourceBundle bundle, TextsMap texts, String name,
			Charset charset, URL url) {
		if (url != null) {
			TextResource text = textResourceFactory.create(bundle, name, url,
					charset);
			texts.putText(name, text);
		}
	}

}
