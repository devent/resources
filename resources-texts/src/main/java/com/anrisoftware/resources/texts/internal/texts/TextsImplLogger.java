/*
 * Copyright 2012-2022 Erwin Müller <erwin.mueller@anrisoftware.com>
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

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.external.ResourcesException;
import com.anrisoftware.resources.texts.external.TextResource;

class TextsImplLogger extends AbstractLogger {

    private static final String LOCALE = "locale";

    private static final String RESOURCE_URL_NOT_FOUND = "The resource URL ``{}'' could not be found.";

    private static final String LOADED_RESOURCE_BUNDLE = "Loaded the resource bundle {} for the text resource ``{}''.";

    private static final String NO_TEXT_RESOURCE_AVAILABLE = "No text resource available '%s' (%s)";

    private static final String NO_TEXT_RESOURCE_LOADED = "No text resource loaded '%s' (%s)";

    /**
     * Creates a logger for {@link TextsImpl}.
     */
    TextsImplLogger() {
        super(TextsImpl.class);
    }

    void checkTextLoaded(boolean haveText, String name, Locale locale, ResourceBundle bundle)
            throws ResourcesException {
        if (!haveText) {
            String message = format(NO_TEXT_RESOURCE_LOADED, name, locale);
            ResourcesException ex = new ResourcesException(message, bundle.getClass().getName(), name);
            ex.addContext(LOCALE, locale);
            logException(ex, message);
            throw ex;
        }
    }

    public URL checkResourceURL(URL url, String urlString) {
        if (url == null) {
            log.warn(RESOURCE_URL_NOT_FOUND, urlString);
        }
        return url;
    }

    void checkHaveResource(TextResource text, String name, Locale locale, ResourceBundle bundle)
            throws ResourcesException {
        if (text == null) {
            String message = format(NO_TEXT_RESOURCE_AVAILABLE, name, locale);
            ResourcesException ex = new ResourcesException(message, bundle.getClass().getName(), name);
            ex.addContext(LOCALE, locale);
            logException(ex, message);
            throw ex;
        }
    }

    void loadedResourceBundle(String name, ResourceBundle bundle) {
        if (log.isDebugEnabled()) {
            log.debug(LOADED_RESOURCE_BUNDLE, bundleToString(bundle), name);
        }
    }

    private String bundleToString(ResourceBundle bundle) {
        return new ToStringBuilder(bundle).append(LOCALE, bundle.getLocale()).toString();
    }

}
