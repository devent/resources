package com.anrisoftware.resources.texts;

import static com.google.common.io.Resources.newReaderSupplier;
import static java.lang.String.format;
import static org.apache.commons.lang.StringUtils.length;
import static org.apache.commons.lang.StringUtils.substring;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;

import javax.annotation.Nullable;
import javax.inject.Inject;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.api.TextResource;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;
import com.google.inject.assistedinject.Assisted;

class TextResourceImpl implements TextResource {

	private final TextResourceImplLogger log;

	private final Locale locale;

	private final URL url;

	private final Charset charset;

	private String text;

	private String formattedText;

	@Inject
	TextResourceImpl(@Assisted @Nullable Locale locale, @Assisted URL url,
			@Assisted Charset charset, TextResourceImplLogger logger) {
		this.log = logger;
		this.locale = locale;
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
			throw log.errorLoadText(this, e);
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
		if (getLanguage() != null) {
			return format(getLanguage(), text, args);
		} else {
			return format(text, args);
		}
	}

	@Override
	public Locale getLanguage() {
		return locale;
	}

	@Override
	public URL getURL() {
		return url;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(getShortText()).append(locale)
				.append(url).toString();
	}

	private String getShortText() {
		return length(text) > 15 ? substring(text, 12) + "..." : text;
	}
}
