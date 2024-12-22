/*
 * Copyright 2012-2025 Erwin Müller <erwin.mueller@anrisoftware.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.anrisoftware.resources.binary.internal.resources;

import static org.apache.commons.io.IOUtils.readFully;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Locale;

import jakarta.inject.Inject;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.resources.binary.external.BinaryResource;
import com.google.inject.assistedinject.Assisted;

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
     * @param logger the {@link BinaryResourceImplLogger} for logging messages.
     * 
     * @param name   the name of the resource.
     * 
     * @param locale the {@link Locale} of the resource.
     * 
     * @param url    the {@link URL} of the resource.
     */
    @Inject
    BinaryResourceImpl(BinaryResourceImplLogger logger, @Assisted String name, @Assisted Locale locale,
            @Assisted URL url) {
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
    public byte[] getBinary() {
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
            readFully(stream, buffer);
        } catch (IOException e) {
            throw log.errorReadStreamToBuffer(e, this);
        }
    }

    @Override
    public InputStream getStream() {
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
    public void discardBinary() {
        buffer = null;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(name).append("locale", locale).append("url", url).toString();
    }
}
