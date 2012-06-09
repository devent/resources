package com.anrisoftware.resources.texts;

import static java.lang.String.format;

import java.io.Externalizable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.anrisoftware.resources.api.BinaryResource;
import com.anrisoftware.resources.api.BinaryResourceFactory;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.api.TextResource;
import com.google.common.io.CharStreams;
import com.google.inject.assistedinject.Assisted;

@SuppressWarnings("serial")
class TextResourceImpl implements TextResource, Serializable {

	private static class SerializableCharsetWrapper implements Externalizable {

		private Charset charset;

		/**
		 * For serialization.
		 */
		@SuppressWarnings("unused")
		@Deprecated
		public SerializableCharsetWrapper() {
		}

		public SerializableCharsetWrapper(Charset charset) {
			this.charset = charset;
		}

		public Charset getCharset() {
			return charset;
		}

		@Override
		public void writeExternal(ObjectOutput out) throws IOException {
			out.writeUTF(charset.name());
		}

		@Override
		public void readExternal(ObjectInput in) throws IOException,
				ClassNotFoundException {
			String charsetName = in.readUTF();
			charset = Charset.forName(charsetName);
		}

	}

	private TextResourceImplLogger log;

	private BinaryResource binary;

	private SerializableCharsetWrapper charsetWrapper;

	private String text;

	private String formattedText;

	/**
	 * For serialization.
	 */
	@Deprecated
	public TextResourceImpl() {
	}

	@Inject
	TextResourceImpl(TextResourceImplLogger logger,
			BinaryResourceFactory factory, @Assisted String name,
			@Assisted Locale locale, @Assisted URL url,
			@Assisted Charset charset) {
		this.binary = factory.create(name, locale, url);
		this.log = logger;
		this.charsetWrapper = new SerializableCharsetWrapper(charset);
	}

	@Override
	public String getName() {
		return binary.getName();
	}

	@Override
	public Locale getLocale() {
		return binary.getLocale();
	}

	@Override
	public URL getURL() {
		return binary.getURL();
	}

	@Override
	public Charset getCharset() {
		return charsetWrapper.getCharset();
	}

	@Override
	public String getText() throws ResourcesException {
		if (text == null) {
			readText();
			binary.discardBinary();
		}
		return text;
	}

	private void readText() throws ResourcesException {
		InputStream stream = binary.getStream();
		Charset charset = charsetWrapper.getCharset();
		InputStreamReader reader = new InputStreamReader(stream, charset);
		try {
			text = CharStreams.toString(reader);
		} catch (IOException e) {
			throw log.errorLoadText(this, e);
		}
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
	public String toString() {
		return new ToStringBuilder(this).append("locale", getLocale())
				.append("name", getName()).append("url", getURL()).toString();
	}

}
