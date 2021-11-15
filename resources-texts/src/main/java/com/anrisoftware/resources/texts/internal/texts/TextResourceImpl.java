/*
 * Copyright 2012-2021 Erwin Müller <erwin.mueller@anrisoftware.com>
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
package com.anrisoftware.resources.texts.internal.texts;


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

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.resources.api.external.ResourcesException;
import com.anrisoftware.resources.binary.external.BinaryResource;
import com.anrisoftware.resources.binary.external.BinaryResourceFactory;
import com.anrisoftware.resources.texts.external.TextResource;
import com.google.inject.assistedinject.Assisted;

class TextResourceImpl implements TextResource, Serializable {

    /**
     * @since 1.0
     */
    private static final long serialVersionUID = -2425350301703068585L;

    /**
     * Wrapper around a character set to make the character set serializable.
     *
     * @author Erwin Mueller, erwin.mueller@deventm.org
     * @since 1.0
     */
    private static class SerializableCharsetWrapper implements Externalizable {

        private Charset charset;

        /**
         * @deprecated Used only for serialization.
         */
        @Deprecated
        public SerializableCharsetWrapper() {
        }

        /**
         * Sets the character set for serialization.
         *
         * @param charset
         *            the {@link Charset}.
         */
        public SerializableCharsetWrapper(Charset charset) {
            this.charset = charset;
        }

        /**
         * Returns the character set.
         *
         * @return the {@link Charset}.
         */
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

    private final TextResourceImplLogger log;

    private final BinaryResource binary;

    private final SerializableCharsetWrapper charsetWrapper;

    private String text;

    private String formattedText;

    /**
     * Sets the needed properties of the text resource.
     *
     * @param logger
     *            the {@link TextResourceImplLogger} for logging messages.
     *
     * @param factory
     *            the {@link BinaryResourceFactory} that creates the binary
     *            resource from which we load the text resource.
     *
     * @param name
     *            the name of this resource.
     *
     * @param locale
     *            the {@link Locale} of this resource.
     *
     * @param charset
     *            the {@link Charset} of this resource.
     *
     * @param url
     *            the {@link URL} of this resource.
     */
    @Inject
    TextResourceImpl(TextResourceImplLogger logger,
            BinaryResourceFactory factory, @Assisted String name,
            @Assisted Locale locale, @Assisted Charset charset,
            @Assisted URL url) {
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
            text = IOUtils.toString(reader);
        } catch (IOException e) {
            throw log.errorLoadText(this, e);
        }
    }

    @Override
    public String getFormattedText(Object... args) throws ResourcesException {
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

    /**
     * String representation for debugging. Contains the locale, name and the
     * URL of this resource.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("locale", getLocale())
                .append("name", getName()).append("url", getURL()).toString();
    }

}
