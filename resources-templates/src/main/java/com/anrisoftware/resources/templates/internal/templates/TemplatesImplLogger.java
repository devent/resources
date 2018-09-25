/*
 * Copyright 2017 Erwin Müller <erwin.mueller@deventm.org>
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

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.external.ResourcesException;
import com.anrisoftware.resources.templates.external.TemplateResource;

/**
 * Logging messages for {@link TemplatesImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TemplatesImplLogger extends AbstractLogger {

    private static final String NAME = "name";
    private static final String LOADED_RESOURCE_BUNDLE = "Loaded resource bundle {} for '{}'.";
    private static final String TEMPLATE_NOT_AVAILABLE = "Template resource not available";
    private static final String NOT_FOUND_RESOURCE = "Template resource not found at '{}'.";
    private static final String LOCALE = "locale";
    private static final String TEMPLATE_NOT_FOUND = "Template resource not found";

    /**
     * Creates a logger for {@link TemplatesImpl}.
     */
    TemplatesImplLogger() {
        super(TemplatesImpl.class);
    }

    void checkTemplateLoaded(boolean haveTemplate, String name, Locale locale,
            ResourceBundle bundle) throws ResourcesException {
        if (haveTemplate) {
            return;
        }
        throw new ResourcesException(TEMPLATE_NOT_FOUND,
                bundle.getClass().toString(), name).addContext(LOCALE, locale);
    }

    public URL checkResourceURL(URL url, String urlString) {
        if (url == null) {
            log.warn(NOT_FOUND_RESOURCE, urlString);
        }
        return url;
    }

    void checkHaveResource(TemplateResource res, String name, Locale locale,
            ResourceBundle bundle) throws ResourcesException {
        if (res != null) {
            return;
        }
        throw new ResourcesException(TEMPLATE_NOT_AVAILABLE,
                bundle.getClass().toString(), name).addContext(NAME, name)
                        .addContext(LOCALE, locale);
    }

    void loadedResourceBundle(String name, ResourceBundle bundle) {
        if (log.isDebugEnabled()) {
            log.debug(LOADED_RESOURCE_BUNDLE, bundleToString(bundle), name);
        }
    }

    private String bundleToString(ResourceBundle bundle) {
        return new ToStringBuilder(bundle).append(LOCALE, bundle.getLocale())
                .toString();
    }

}
