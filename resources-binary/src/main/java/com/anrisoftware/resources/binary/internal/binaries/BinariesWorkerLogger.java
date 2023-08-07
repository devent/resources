/*
 * Copyright 2012-2023 Erwin Müller <erwin.mueller@anrisoftware.com>
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
package com.anrisoftware.resources.binary.internal.binaries;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.external.ResourcesException;
import com.anrisoftware.resources.binary.external.BinaryResource;

class BinariesWorkerLogger extends AbstractLogger {

    private static final String RESOURCE_URL_NOT_FOUND = "Resource URL not found '{}'.";
    private static final String NO_RESOURCE = "Binary resource not found";
    private static final String NO_RESOURCE_MESSAGE = "Binary resource not found '{}' ({}).";
    private static final String ADD_NEW_BINARY_RESOUCE = "Binary resouce {} added.";
    private static final String LOADED_RESOURCE_BUNDLE = "Resource bundle {} loaded for '{}'.";

    BinariesWorkerLogger() {
        super(BinariesImpl.class);
    }

    void loadedResourceBundle(String name, ResourceBundle bundle) {
        if (log.isDebugEnabled()) {
            log.debug(LOADED_RESOURCE_BUNDLE, bundleToString(bundle), name);
        }
    }

    private String bundleToString(ResourceBundle bundle) {
        return new ToStringBuilder(bundle).append("locale", bundle.getLocale()).toString();
    }

    void addedBinaryResource(BinaryResource resource) {
        log.debug(ADD_NEW_BINARY_RESOUCE, resource);
    }

    void checkBinaryLoaded(boolean haveResource, String name, Locale locale, ResourceBundle bundle) {
        if (!haveResource) {
            throw logException(new ResourcesException(NO_RESOURCE, bundle.getClass().toString(), name)
                    .addContext("name", name).addContext("locale", locale), NO_RESOURCE_MESSAGE, name, locale);
        }
    }

    URL checkResourceURL(URL url, String value) {
        if (url == null) {
            log.warn(RESOURCE_URL_NOT_FOUND, value);
        }
        return url;
    }

}
