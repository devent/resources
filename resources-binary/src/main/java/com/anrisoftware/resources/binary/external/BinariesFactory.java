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
package com.anrisoftware.resources.binary.external;

import java.util.ResourceBundle;

/**
 * Factory to create a new binaries resources bundle with the specified resource
 * bundle base name and optional class loader and resource bundle control.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 * @see Binaries
 */
public interface BinariesFactory {

    /**
     * Creates a new {@link Binaries} with the resource bundle base name and the
     * caller's class loader.
     *
     * @param baseName
     *            the base name {@link String}.
     */
    Binaries create(String baseName);

    /**
     * Creates a new {@link Binaries} with the resource bundle base name and the
     * class loader.
     *
     * @param baseName
     *            the base name {@link String}.
     *
     * @param classLoader
     *            the {@link ClassLoader}.
     */
    Binaries create(String baseName, ClassLoader classLoader);

    /**
     * Creates a new {@link Binaries} with the resource bundle base name and the
     * control.
     *
     * @param baseName
     *            the base name {@link String}.
     *
     * @param control
     *            the {@link ResourceBundle.Control}.
     */
    Binaries create(String baseName, ResourceBundle.Control control);

    /**
     * Creates a new {@link Binaries} with the resource bundle base name, the
     * class loader and the control.
     *
     * @param baseName
     *            the base name {@link String}.
     *
     * @param classLoader
     *            the {@link ClassLoader}.
     *
     * @param control
     *            the {@link ResourceBundle.Control}.
     */
    Binaries create(String baseName, ClassLoader classLoader,
            ResourceBundle.Control control);

}
