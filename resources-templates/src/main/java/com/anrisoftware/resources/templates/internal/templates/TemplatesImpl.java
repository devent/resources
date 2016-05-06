/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.templates.internal.templates;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import com.anrisoftware.resources.external.GetBundle;
import com.anrisoftware.resources.external.GetBundleWithClassLoader;
import com.anrisoftware.resources.external.GetBundleWithClassLoaderAndControl;
import com.anrisoftware.resources.external.GetBundleWithControl;
import com.anrisoftware.resources.external.ResourcesException;
import com.anrisoftware.resources.templates.external.TemplatesBundlesMap;
import com.anrisoftware.resources.templates.external.TemplatesBundlesMapFactory;
import com.anrisoftware.resources.templates.external.TemplateResource;
import com.anrisoftware.resources.templates.external.TemplateResourceFactory;
import com.anrisoftware.resources.templates.external.Templates;
import com.anrisoftware.resources.templates.external.TemplatesFactory;
import com.anrisoftware.resources.templates.external.TemplatesMap;
import com.anrisoftware.resources.templates.external.TemplatesPropertiesFactory;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Loads the template resources from the resource bundle.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TemplatesImpl implements Templates {

    private static final Map<Serializable, Serializable> EMPTY_ATTRIBUTES = new HashMap<Serializable, Serializable>();

    private final TemplatesImplLogger log;

    private final TemplatesBundlesMap texts;

    private final TemplateResourceFactory resourceFactory;

    private final GetBundle getBundle;

    private final Properties properties;

    private final Map<Serializable, Serializable> attributes;

    /**
     * @see TemplatesFactory#create(String)
     */
    @AssistedInject
    TemplatesImpl(TemplatesImplLogger logger, TemplatesBundlesMapFactory texts,
            TemplateResourceFactory textResourceFactory,
            TemplatesPropertiesFactory propertiesFactory,
            @Assisted String baseName) {
        this(logger, texts, textResourceFactory, propertiesFactory,
                EMPTY_ATTRIBUTES, new GetBundle(baseName));
    }

    /**
     * @see TemplatesFactory#create(String, Map)
     */
    @AssistedInject
    TemplatesImpl(TemplatesImplLogger logger, TemplatesBundlesMapFactory texts,
            TemplateResourceFactory textResourceFactory,
            TemplatesPropertiesFactory propertiesFactory,
            @Assisted String baseName,
            @Assisted Map<Serializable, Serializable> attributes) {
        this(logger, texts, textResourceFactory, propertiesFactory, attributes,
                new GetBundle(baseName));
    }

    /**
     * @see TemplatesFactory#create(String, ClassLoader)
     */
    @AssistedInject
    TemplatesImpl(TemplatesImplLogger logger, TemplatesBundlesMapFactory texts,
            TemplateResourceFactory textResourceFactory,
            TemplatesPropertiesFactory propertiesFactory,
            @Assisted String baseName, @Assisted ClassLoader classLoader) {
        this(logger, texts, textResourceFactory, propertiesFactory,
                EMPTY_ATTRIBUTES, new GetBundleWithClassLoader(baseName,
                        classLoader));
    }

    /**
     * @see TemplatesFactory#create(String, Map, ClassLoader)
     */
    @AssistedInject
    TemplatesImpl(TemplatesImplLogger logger, TemplatesBundlesMapFactory texts,
            TemplateResourceFactory textResourceFactory,
            TemplatesPropertiesFactory propertiesFactory,
            @Assisted String baseName,
            @Assisted Map<Serializable, Serializable> attributes,
            @Assisted ClassLoader classLoader) {
        this(logger, texts, textResourceFactory, propertiesFactory, attributes,
                new GetBundleWithClassLoader(baseName, classLoader));
    }

    /**
     * @see TemplatesFactory#create(String, Control)
     */
    @AssistedInject
    TemplatesImpl(TemplatesImplLogger logger, TemplatesBundlesMapFactory texts,
            TemplateResourceFactory textResourceFactory,
            TemplatesPropertiesFactory propertiesFactory,
            @Assisted String baseName, @Assisted ResourceBundle.Control control) {
        this(logger, texts, textResourceFactory, propertiesFactory,
                EMPTY_ATTRIBUTES, new GetBundleWithControl(baseName, control));
    }

    /**
     * @see TemplatesFactory#create(String, Map, Control)
     */
    @AssistedInject
    TemplatesImpl(TemplatesImplLogger logger, TemplatesBundlesMapFactory texts,
            TemplateResourceFactory textResourceFactory,
            TemplatesPropertiesFactory propertiesFactory,
            @Assisted String baseName,
            @Assisted Map<Serializable, Serializable> attributes,
            @Assisted ResourceBundle.Control control) {
        this(logger, texts, textResourceFactory, propertiesFactory, attributes,
                new GetBundleWithControl(baseName, control));
    }

    /**
     * @see TemplatesFactory#create(String, ClassLoader, Control)
     */
    @AssistedInject
    TemplatesImpl(TemplatesImplLogger logger, TemplatesBundlesMapFactory texts,
            TemplateResourceFactory textResourceFactory,
            TemplatesPropertiesFactory propertiesFactory,
            @Assisted String baseName, @Assisted ClassLoader classLoader,
            @Assisted ResourceBundle.Control control) {
        this(logger, texts, textResourceFactory, propertiesFactory,
                EMPTY_ATTRIBUTES, new GetBundleWithClassLoaderAndControl(
                        baseName, classLoader, control));
    }

    /**
     * @see TemplatesFactory#create(String, Map, ClassLoader, Control)
     */
    @AssistedInject
    TemplatesImpl(TemplatesImplLogger logger, TemplatesBundlesMapFactory texts,
            TemplateResourceFactory textResourceFactory,
            TemplatesPropertiesFactory propertiesFactory,
            @Assisted String baseName,
            @Assisted Map<Serializable, Serializable> attributes,
            @Assisted ClassLoader classLoader,
            @Assisted ResourceBundle.Control control) {
        this(logger, texts, textResourceFactory, propertiesFactory, attributes,
                new GetBundleWithClassLoaderAndControl(baseName, classLoader,
                        control));
    }

    private TemplatesImpl(TemplatesImplLogger logger, TemplatesBundlesMapFactory texts,
            TemplateResourceFactory textResourceFactory,
            TemplatesPropertiesFactory propertiesFactory,
            Map<Serializable, Serializable> attributes, GetBundle getBundle) {
        this.log = logger;
        this.texts = texts.create();
        this.resourceFactory = textResourceFactory;
        try {
            this.properties = propertiesFactory.create();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.getBundle = getBundle;
        this.attributes = attributes;
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
    public TemplateResource getResource(String name) throws ResourcesException {
        return getResource(name, Locale.getDefault());
    }

    @Override
    public TemplateResource getResource(String name, Locale locale)
            throws ResourcesException {
        ResourceBundle bundle = getBundle.bundleFor(locale);
        log.loadedResourceBundle(name, bundle);
        TemplateResource res = lazyLoadResource(name, bundle);
        log.checkHaveResource(res, name, locale, bundle);
        return res;
    }

    private TemplateResource lazyLoadResource(String name, ResourceBundle bundle) {
        String location = bundle.getString(name);
        TemplatesMap map = texts.getTemplates(bundle);
        TemplateResource text = map.getTemplate(name);
        if (text == null) {
            text = loadTextResource(bundle, map, name, location);
        }
        return text;
    }

    private TemplateResource loadTextResource(ResourceBundle bundle,
            TemplatesMap map, String name, String value)
            throws ResourcesException {
        URL url = parseURL(value);
        Locale locale = bundle.getLocale();
        TemplateResource text = loadTemplate(locale, map, name, url);
        log.checkTemplateLoaded(map.haveText(name), name, locale, bundle);
        return text;
    }

    private URL parseURL(String string) {
        try {
            return new URL(string);
        } catch (MalformedURLException e) {
            URL url = getClassLoader().getResource(string);
            return log.checkResourceURL(url, string);
        }
    }

    private TemplateResource loadTemplate(Locale locale, TemplatesMap map,
            String name, URL url) {
        if (url != null) {
            TemplateResource text;
            text = resourceFactory.create(name, locale, url, properties,
                    attributes);
            map.putTemplate(name, text);
            return text;
        } else {
            return null;
        }
    }

}
