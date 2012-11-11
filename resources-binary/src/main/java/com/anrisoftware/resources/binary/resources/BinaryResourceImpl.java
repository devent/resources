package com.anrisoftware.resources.binary.resources;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.binary.api.BinaryResource;
import com.google.common.io.ByteStreams;
import com.google.inject.assistedinject.Assisted;

/**
 * Binary resource with lazy loading.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class BinaryResourceImpl implements BinaryResource, Serializable {

	/**
	 * @since 1.0
	 */
	private static final long serialVersionUID = -1207420120451049084L;

	private final BinaryResourceImplLogger log;

	private final String name;

	private final Locale locale;

	private final URL url;

	private byte[] buffer;

	/**
	 * Sets the name, locale and URL of the binary resource.
	 * 
	 * @param logger
	 *            the {@link BinaryResourceImplLogger} for logging messages.
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @param locale
	 *            the {@link Locale} of the resource.
	 * 
	 * @param url
	 *            the {@link URL} of the resource.
	 */
	@Inject
	BinaryResourceImpl(BinaryResourceImplLogger logger, @Assisted String name,
			@Assisted Locale locale, @Assisted URL url) {
		this.log = logger;
		this.name = name;
		this.locale = locale;
		this.url = url;
	}

	@Override
	public String getName() {
		return name;
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
	public byte[] getBinary() throws ResourcesException {
		lazyLoadBuffer();
		return buffer;
	}

	private byte[] lazyLoadBuffer() {
		if (buffer != null) {
			return buffer;
		}
		InputStream stream = getStream();
		buffer = createBufferWithStreamSize(stream);
		readStreamToBuffer(stream, buffer);
		log.loadedBuffer(this);
		return buffer;
	}

	private byte[] createBufferWithStreamSize(InputStream stream) {
		try {
			return new byte[stream.available()];
		} catch (IOException e) {
			throw log.errorReadStreamToBuffer(e, this);
		}
	}

	private void readStreamToBuffer(InputStream stream, byte[] buffer) {
		try {
			ByteStreams.readFully(stream, buffer);
		} catch (IOException e) {
			throw log.errorReadStreamToBuffer(e, this);
		}
	}

	@Override
	public InputStream getStream() throws ResourcesException {
		if (buffer == null) {
			return openStream();
		} else {
			return new ByteArrayInputStream(buffer);
		}
	}

	private InputStream openStream() {
		try {
			return url.openStream();
		} catch (IOException e) {
			throw log.errorOpenStream(e, this);
		}
	}

	@Override
	public void discardBinary() throws ResourcesException {
		buffer = null;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(name).append("locale", locale)
				.append("url", url).toString();
	}
}
