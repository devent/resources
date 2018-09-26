
package com.anrisoftware.resources.templates.internal.templates;

/*-
 * #%L
 * Resources :: Templates
 * %%
 * Copyright (C) 2012 - 2018 Advanced Natural Research Institute
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.resources.api.external.ResourcesException;
import com.anrisoftware.resources.templates.external.TemplateResource;
import com.anrisoftware.resources.templates.external.TemplateResourceFactory;
import com.anrisoftware.resources.templates.external.TemplateWorker;
import com.anrisoftware.resources.templates.external.TemplateWorkerFactory;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Serializable template resource. Uses a template worker to process the
 * templates.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @see TemplateWorker
 * @since 1.0
 */
@SuppressWarnings("serial")
class TemplateResourceImpl implements TemplateResource, Serializable {

    private static final Map<Serializable, Serializable> EMPTY_ATTRIBUTES = new HashMap<Serializable, Serializable>();

    private TemplateResourceImplLogger log;

    private String text;

    private TemplateWorker worker;

    private String name;

    private Locale locale;

    /**
     * For serialization.
     */
    @Deprecated
    public TemplateResourceImpl() {
    }

    /**
     * @see TemplateResourceFactory#create(String, Locale, URL, Properties)
     */
    @AssistedInject
    TemplateResourceImpl(TemplateResourceImplLogger logger,
            TemplateWorkerFactory workerFactory, @Assisted String name,
            @Assisted Locale locale, @Assisted URL url,
            @Assisted Properties properties) {
        this(logger, workerFactory, name, locale, url, properties,
                EMPTY_ATTRIBUTES, true);
    }

    /**
     * @see TemplateResourceFactory#create(String, Locale, URL, Properties, Map)
     */
    @AssistedInject
    TemplateResourceImpl(TemplateResourceImplLogger logger,
            TemplateWorkerFactory workerFactory, @Assisted String name,
            @Assisted Locale locale, @Assisted URL url,
            @Assisted Properties properties,
            @Assisted Map<Serializable, Serializable> attributes) {
        this(logger, workerFactory, name, locale, url, properties, attributes,
                true);
    }

    private TemplateResourceImpl(TemplateResourceImplLogger logger,
            TemplateWorkerFactory workerFactory, String name, Locale locale,
            URL url, Properties properties,
            Map<Serializable, Serializable> attributes, boolean differentCtor) {
        this.name = name;
        this.locale = locale;
        this.worker = workerFactory.create(url, properties, attributes);
        this.log = logger;
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
        return worker.getURL();
    }

    @Override
    public String getText(boolean invalidate, Object... data)
            throws ResourcesException {
        if (invalidate) {
            invalidate();
        }
        return getText(data);
    }

    @Override
    public String getText(Object... data) throws ResourcesException {
        if (text == null) {
            log.processTemplate(this);
            text = worker.process(name, data);
        }
        return text;
    }

    @Override
    public void invalidate() {
        text = null;
        log.resourceInvalidated(this);
    }

    @Override
    public Properties getProperties() {
        return worker.getProperties();
    }

    @Override
    public Map<Serializable, Serializable> getAttributes() {
        return worker.getAttributes();
    }

    @Override
    public <T> T getTemplate() {
        return worker.getTemplate();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("locale", getLocale())
                .append("name", getName()).append("url", getURL()).toString();
    }

}
