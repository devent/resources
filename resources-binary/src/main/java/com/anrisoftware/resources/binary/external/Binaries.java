/**
 * Copyright © 2012 Erwin Müller (erwin.mueller@anrisoftware.com)
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

import java.util.Locale;
import java.util.ResourceBundle;

import com.anrisoftware.resources.api.external.ResourcesException;

public interface Binaries {

    /**
     * Returns the used resource bundle base name.
     *
     * @return the used base name {@link String}.
     */
    String getBaseName();

    /**
     * Returns the used resource bundle class loader.
     *
     * @return the used {@link ClassLoader}.
     */
    ClassLoader getClassLoader();

    /**
     * Returns the used resource bundle control.
     *
     * @return the used {@link ResourceBundle.Control}.
     */
    ResourceBundle.Control getControl();

    /**
     * Returns the binary resource with the specified name and language.
     *
     * @param name   the name of the resource.
     *
     * @param locale the {@link Locale} of the resource or <code>null</code>. If the
     *               locale is <code>null</code> the default locale as in
     *               {@link Locale#getDefault()} is used.
     *
     * @return the {@link BinaryResource}.
     *
     * @throws ResourcesException if the resource is not available.
     *
     * @since 1.2
     */
    BinaryResource getResource(String name, Locale locale);

    /**
     * Returns the binary resource with the specified name and the default locale as
     * in {@link Locale#getDefault()}.
     *
     * @param name the name of the resource.
     *
     * @return the {@link BinaryResource}.
     *
     * @throws ResourcesException if the resource is not available.
     *
     * @since 1.2
     */
    BinaryResource getResource(String name);

}
