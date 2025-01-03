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
package com.anrisoftware.resources.texts.internal.texts;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import org.apache.commons.text.StringTokenizer;

import com.anrisoftware.resources.api.external.ResourcesException;
import com.anrisoftware.resources.getbundle.external.GetBundle;
import com.anrisoftware.resources.getbundle.external.GetBundleWithClassLoader;
import com.anrisoftware.resources.getbundle.external.GetBundleWithClassLoaderAndControl;
import com.anrisoftware.resources.getbundle.external.GetBundleWithControl;
import com.anrisoftware.resources.texts.external.TextResource;
import com.anrisoftware.resources.texts.external.TextResourceFactory;
import com.anrisoftware.resources.texts.external.Texts;
import com.anrisoftware.resources.texts.external.TextsBundlesMap;
import com.anrisoftware.resources.texts.external.TextsBundlesMapFactory;
import com.anrisoftware.resources.texts.external.TextsMap;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import jakarta.inject.Named;

class TextsImpl implements Texts {

    private final TextsImplLogger log;

    private final TextsBundlesMap texts;

    private final TextResourceFactory textResourceFactory;

    private final GetBundle getBundle;

    private final Charset defaultCharset;

    /**
     * Sets the default character set and the base name of the text resources.
     *
     * @param logger              the {@link TextsImplLogger} for the logging
     *                            messages.
     *
     * @param texts               the {@link TextsBundlesMapFactory} that cache the
     *                            resource bundles.
     *
     * @param textResourceFactory the {@link TextResourceFactory} that creates the
     *                            text resources.
     *
     * @param defaultCharset      the default {@link Charset} for the text
     *                            resources.
     *
     * @param baseName            the base name for the text resources.
     */
    @AssistedInject
    TextsImpl(TextsImplLogger logger, TextsBundlesMapFactory texts, TextResourceFactory textResourceFactory,
            @Named("texts-default-charset") Charset defaultCharset, @Assisted String baseName) {
        this(logger, texts, textResourceFactory, defaultCharset, new GetBundle(baseName));
    }

    /**
     * Sets the default character set, the base name and the class loader for the
     * text resources.
     *
     * @param logger              the {@link TextsImplLogger} for the logging
     *                            messages.
     *
     * @param texts               the {@link TextsBundlesMapFactory} that cache the
     *                            resource bundles.
     *
     * @param textResourceFactory the {@link TextResourceFactory} that creates the
     *                            text resources.
     *
     * @param defaultCharset      the default {@link Charset} for the text
     *                            resources.
     *
     * @param baseName            the base name for the text resources.
     *
     * @param classLoader         the {@link ClassLoader} to load the text
     *                            resources.
     */
    @AssistedInject
    TextsImpl(TextsImplLogger logger, TextsBundlesMapFactory texts, TextResourceFactory textResourceFactory,
            @Named("texts-default-charset") Charset defaultCharset, @Assisted String baseName,
            @Assisted ClassLoader classLoader) {
        this(logger, texts, textResourceFactory, defaultCharset, new GetBundleWithClassLoader(baseName, classLoader));
    }

    /**
     * Sets the default character set, the base name and the resource bundle control
     * for the text resources.
     *
     * @param logger              the {@link TextsImplLogger} for the logging
     *                            messages.
     *
     * @param texts               the {@link TextsBundlesMapFactory} that cache the
     *                            resource bundles.
     *
     * @param textResourceFactory the {@link TextResourceFactory} that creates the
     *                            text resources.
     *
     * @param defaultCharset      the default {@link Charset} for the text
     *                            resources.
     *
     * @param baseName            the base name for the text resources.
     *
     * @param control             the {@link ResourceBundle.Control} for the text
     *                            resources.
     */
    @AssistedInject
    TextsImpl(TextsImplLogger logger, TextsBundlesMapFactory texts, TextResourceFactory textResourceFactory,
            @Named("texts-default-charset") Charset defaultCharset, @Assisted String baseName,
            @Assisted ResourceBundle.Control control) {
        this(logger, texts, textResourceFactory, defaultCharset, new GetBundleWithControl(baseName, control));
    }

    /**
     * Sets the default character set, the base name, the class loader and the
     * resource bundle control for the text resources.
     *
     * @param logger              the {@link TextsImplLogger} for the logging
     *                            messages.
     *
     * @param texts               the {@link TextsBundlesMapFactory} that cache the
     *                            resource bundles.
     *
     * @param textResourceFactory the {@link TextResourceFactory} that creates the
     *                            text resources.
     *
     * @param defaultCharset      the default {@link Charset} for the text
     *                            resources.
     *
     * @param baseName            the base name for the text resources.
     *
     * @param classLoader         the {@link ClassLoader} to load the text
     *                            resources.
     *
     * @param control             the {@link ResourceBundle.Control} for the text
     *                            resources.
     */
    @AssistedInject
    TextsImpl(TextsImplLogger logger, TextsBundlesMapFactory texts, TextResourceFactory textResourceFactory,
            @Named("texts-default-charset") Charset defaultCharset, @Assisted String baseName,
            @Assisted ClassLoader classLoader, @Assisted ResourceBundle.Control control) {
        this(logger, texts, textResourceFactory, defaultCharset,
                new GetBundleWithClassLoaderAndControl(baseName, classLoader, control));
    }

    private TextsImpl(TextsImplLogger logger, TextsBundlesMapFactory texts, TextResourceFactory textResourceFactory,
            Charset defaultCharset, GetBundle getBundle) {
        this.log = logger;
        this.texts = texts.create();
        this.textResourceFactory = textResourceFactory;
        this.defaultCharset = defaultCharset;
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
    public TextResource getResource(String name) throws ResourcesException {
        return getResource(name, Locale.getDefault());
    }

    @Override
    public TextResource getResource(String name, Locale locale) throws ResourcesException {
        ResourceBundle bundle = getBundle.bundleFor(locale);
        log.loadedResourceBundle(name, bundle);
        TextResource text = lazyLoadResource(name, bundle);
        log.checkHaveResource(text, name, locale, bundle);
        return text;
    }

    private TextResource lazyLoadResource(String name, ResourceBundle bundle) {
        String location = bundle.getString(name);
        TextsMap map = texts.getTexts(bundle);
        TextResource text = map.getText(name);
        if (text == null) {
            text = loadTextResource(bundle, map, name, location);
        }
        return text;
    }

    private TextResource loadTextResource(ResourceBundle bundle, TextsMap map, String name, String value)
            throws ResourcesException {
        var tokenizer = new StringTokenizer(value, ',', '\\');
        String[] tokens = tokenizer.getTokenArray();
        Charset charset = parseCharset(tokens);
        URL url = parseUrl(tokens);
        Locale locale = bundle.getLocale();
        TextResource text = loadText(locale, map, name, charset, url);
        log.checkTextLoaded(map.haveText(name), name, locale, bundle);
        return text;
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
            URL url = getClassLoader().getResource(urlString);
            return log.checkResourceURL(url, urlString);
        }
    }

    private TextResource loadText(Locale locale, TextsMap map, String name, Charset charset, URL url) {
        if (url != null) {
            TextResource text = textResourceFactory.create(name, locale, url, charset);
            map.putText(name, text);
            return text;
        } else {
            return null;
        }
    }

}
