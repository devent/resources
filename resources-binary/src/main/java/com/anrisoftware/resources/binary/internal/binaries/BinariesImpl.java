/*
 * Copyright 2016 Erwin Müller <erwin.mueller@deventm.org>
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

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import com.anrisoftware.resources.binary.external.Binaries;
import com.anrisoftware.resources.binary.external.BinaryResource;
import com.anrisoftware.resources.binary.external.BinariesBundlesMap;
import com.anrisoftware.resources.binary.external.BinariesBundlesMapFactory;
import com.anrisoftware.resources.external.GetBundle;
import com.anrisoftware.resources.external.GetBundleWithClassLoader;
import com.anrisoftware.resources.external.GetBundleWithClassLoaderAndControl;
import com.anrisoftware.resources.external.GetBundleWithControl;
import com.anrisoftware.resources.external.ResourcesException;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

class BinariesImpl implements Binaries {

    private final BinariesBundlesMap bundles;

    private final GetBundle getBundle;

    private final BinariesWorkerFactory workerFactory;

    @AssistedInject
    BinariesImpl(BinariesWorkerFactory workerFactory,
            BinariesBundlesMapFactory bundles, @Assisted String baseName) {
        this(workerFactory, bundles, new GetBundle(baseName));
    }

    @AssistedInject
    BinariesImpl(BinariesWorkerFactory workerFactory,
            BinariesBundlesMapFactory bundles, @Assisted String baseName,
            @Assisted ClassLoader classLoader) {
        this(workerFactory, bundles, new GetBundleWithClassLoader(baseName,
                classLoader));
    }

    @AssistedInject
    BinariesImpl(BinariesWorkerFactory workerFactory,
            BinariesBundlesMapFactory bundles, @Assisted String baseName,
            @Assisted ResourceBundle.Control control) {
        this(workerFactory, bundles,
                new GetBundleWithControl(baseName, control));
    }

    @AssistedInject
    BinariesImpl(BinariesWorkerFactory workerFactory,
            BinariesBundlesMapFactory bundles, @Assisted String baseName,
            @Assisted ClassLoader classLoader,
            @Assisted ResourceBundle.Control control) {
        this(workerFactory, bundles, new GetBundleWithClassLoaderAndControl(
                baseName, classLoader, control));
    }

    private BinariesImpl(BinariesWorkerFactory workerFactory,
            BinariesBundlesMapFactory bundles, GetBundle getBundle) {
        this.workerFactory = workerFactory;
        this.bundles = bundles.create();
        this.getBundle = getBundle;
    }

    @Override
    public ClassLoader getClassLoader() {
        return getBundle.getClassLoader();
    }

    @Override
    public String getBaseName() {
        return getBundle.getBaseName();
    }

    @Override
    public Control getControl() {
        return getBundle.getControl();
    }

    @Override
    public BinaryResource getResource(String name) throws ResourcesException {
        return getResource(name, null);
    }

    @Override
    public BinaryResource getResource(String name, Locale locale)
            throws ResourcesException {
        locale = locale == null ? Locale.getDefault() : locale;
        return workerFactory.create(name, locale, getBundle, bundles)
                .binaryResource();
    }

}
