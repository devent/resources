package com.anrisoftware.resources.binary.resources;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.io.WriteAbortedException;
import java.net.URL;
import java.util.Locale;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.binary.api.BinaryResource;
import com.google.common.io.ByteStreams;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Image resource with lazy loading. Two image resources are equals if the
 * resource URL is the same.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class BinaryResourceImpl implements BinaryResource, Serializable {

	private final BinaryResourceImplLogger log;

	private final String name;

	private final Locale locale;

	private final URL url;

	private byte[] buffer;

	@AssistedInject
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

	Object writeReplace() throws ObjectStreamException {
		try {
			getBinary();
		} catch (ResourcesException e) {
			throw new WriteAbortedException("Failed to read binary data.", e);
		}
		return this;
	}
}
