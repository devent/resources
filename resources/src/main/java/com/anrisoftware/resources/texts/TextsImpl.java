package com.anrisoftware.resources.texts;

import static java.lang.String.format;
import static org.apache.commons.lang.StringUtils.replace;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.text.StrTokenizer;

import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.api.TextResource;
import com.anrisoftware.resources.api.TextResourceFactory;
import com.anrisoftware.resources.api.Texts;

class TextsImpl implements Texts {

	private final Properties textsProperties;

	private final TextsImplLogger log;

	private final TextsMap texts;

	private final TextResourceFactory textResourceFactory;

	private Charset defaultCharset;

	@Inject
	TextsImpl(@Named("texts-properties") Properties textsProperties,
			TextsImplLogger logger, TextsMap texts,
			TextResourceFactory textResourceFactory) {
		this.textsProperties = textsProperties;
		this.log = logger;
		this.texts = texts;
		this.textResourceFactory = textResourceFactory;
	}

	@Override
	public Texts loadResources() throws ResourcesException {
		loadTextsResources();
		return this;
	}

	private void loadTextsResources() throws ResourcesException {
		for (Map.Entry<Object, Object> entry : textsProperties.entrySet()) {
			loadTextResourceForLanguages((String) entry.getKey(),
					(String) entry.getValue());
		}
	}

	private void loadTextResourceForLanguages(String name, String value)
			throws ResourcesException {
		StrTokenizer tokenizer = new StrTokenizer(value, ',', '\\');
		String[] tokens = tokenizer.getTokenArray();
		Charset charset = splitCharset(tokens);
		String urlPattern = splitUrlPattern(tokens);
		loadTextForDefaultLanguage(name, charset, urlPattern);
		loadTextResourcesForLanguages(name, charset, urlPattern);
		log.checkTextLoaded(texts.haveText(name), name);
	}

	private Charset splitCharset(String[] tokens) {
		if (tokens.length == 2) {
			String name = tokens[0];
			return Charset.forName(name);
		} else {
			return defaultCharset;
		}
	}

	private String splitUrlPattern(String[] tokens) {
		if (tokens.length == 2) {
			return tokens[1];
		} else {
			return tokens[0];
		}
	}

	private void loadTextResourcesForLanguages(String name, Charset charset,
			String urlPattern) {
		String urlString;
		URL url;
		TextResource text;
		for (Locale locale : Locale.getAvailableLocales()) {
			urlString = format(urlPattern, locale.getLanguage());
			url = createURL(urlString);
			if (url == null) {
				continue;
			}
			text = textResourceFactory.create(locale, url, charset);
			addTextResource(name, text);
		}
	}

	private void loadTextForDefaultLanguage(String name, Charset charset,
			String urlPattern) {
		String urlString = replace(format(urlPattern, ""), "//", "/");
		URL url = createURL(urlString);
		if (url != null) {
			TextResource text = textResourceFactory.create(null, url, charset);
			addTextResource(name, text);
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

	private void addTextResource(String name, TextResource text) {
		texts.putText(name, text);
	}

	@Override
	public TextResource textResource(String name, Locale locale)
			throws ResourcesException {
		TextResource text = texts.getText(name, locale);
		log.checkHaveResource(text, name, locale);
		return text;
	}

	@Override
	public TextResource textResource(String name) throws ResourcesException {
		return textResource(name, Locale.getDefault());
	}

}
