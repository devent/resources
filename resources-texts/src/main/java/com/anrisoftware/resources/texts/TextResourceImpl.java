package com.anrisoftware.resources.texts;

import static com.google.common.io.Resources.newReaderSupplier;
import static java.lang.String.format;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.api.TextResource;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;
import com.google.inject.assistedinject.Assisted;

class TextResourceImpl implements TextResource {

	private final TextResourceImplLogger log;

	private final ResourceBundle bundle;

	private final String name;

	private final URL url;

	private final Charset charset;

	private String text;

	private String formattedText;

	@Inject
	TextResourceImpl(@Assisted ResourceBundle bundle, @Assisted String name,
			@Assisted URL url, @Assisted Charset charset,
			TextResourceImplLogger logger) {
		this.log = logger;
		this.bundle = bundle;
		this.name = name;
		this.url = url;
		this.charset = charset;
	}

	@Override
	public String getText() throws ResourcesException {
		if (text == null) {
			text = readText();
		}
		return text;
	}

	private String readText() throws ResourcesException {
		try {
			InputSupplier<InputStreamReader> reader = getReader();
			return CharStreams.toString(reader);
		} catch (IOException e) {
			throw log.errorLoadText(bundle, name, e);
		}
	}

	private InputSupplier<InputStreamReader> getReader() {
		return newReaderSupplier(url, charset);
	}

	@Override
	public String formatText(Object... args) throws ResourcesException {
		if (formattedText == null) {
			formattedText = createformatText(args);
		}
		return formattedText;
	}

	private String createformatText(Object[] args) throws ResourcesException {
		String text = getText();
		if (getLocale() != null) {
			return format(getLocale(), text, args);
		} else {
			return format(text, args);
		}
	}

	@Override
	public Locale getLocale() {
		return bundle.getLocale();
	}

	@Override
	public URL getURL() {
		return url;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("locale", getLocale())
				.append("name", name).toString();
	}

}
