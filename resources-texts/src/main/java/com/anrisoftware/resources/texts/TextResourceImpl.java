package com.anrisoftware.resources.texts;

import static com.google.common.io.Resources.newReaderSupplier;
import static java.lang.String.format;

import java.io.Externalizable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.api.TextResource;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;
import com.google.inject.assistedinject.Assisted;

class TextResourceImpl implements TextResource, Serializable, Externalizable {

	private TextResourceImplLogger log;

	private String name;

	private Locale locale;

	private URL url;

	private Charset charset;

	private String text;

	private String formattedText;

	/**
	 * For serialization.
	 */
	@Deprecated
	public TextResourceImpl() {
	}

	@Inject
	TextResourceImpl(@Assisted String name, @Assisted Locale locale,
			@Assisted URL url, @Assisted Charset charset,
			TextResourceImplLogger logger) {
		this.log = logger;
		this.name = name;
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
			throw log.errorLoadText(name, locale, e);
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
		return locale;
	}

	@Override
	public URL getURL() {
		return url;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("locale", getLocale())
				.append("name", name).append("url", getURL()).toString();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(log);
		out.writeObject(name);
		out.writeObject(locale);
		out.writeObject(url);
		out.writeUTF(charset.name());
		out.flush();
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		log = (TextResourceImplLogger) in.readObject();
		name = (String) in.readObject();
		locale = (Locale) in.readObject();
		url = (URL) in.readObject();
		String charsetName = in.readUTF();
		charset = Charset.forName(charsetName);
	}

}
