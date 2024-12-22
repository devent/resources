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
package com.anrisoftware.resources.texts.external;

import java.util.Locale;
import java.util.ResourceBundle;

import com.anrisoftware.resources.api.external.ResourcesException;

public interface Texts {

    /**
     * Returns the used resource bundle base name.
     * 
     * @return the used base name {@link String}.
     * 
     * @since 1.1
     */
    String getBaseName();

    /**
     * Returns the used resource bundle class loader.
     * 
     * @return the used {@link ClassLoader}.
     * 
     * @since 1.1
     */
    ClassLoader getClassLoader();

    /**
     * Returns the used resource bundle control.
     * 
     * @return the used {@link ResourceBundle.Control}.
     * 
     * @since 1.1
     */
    ResourceBundle.Control getControl();

    /**
     * <p>
     * Returns the text resource with the given name and the default locale as in
     * {@link Locale#getDefault()}.
     * </p>
     * 
     * @param name the name of the resource.
     * 
     * @return the {@link TextResource}.
     * 
     * @throws ResourcesException if the resource is not available.
     * 
     * @since 1.2
     */
    TextResource getResource(String name);

    /**
     * <p>
     * Returns the text resource with the given name and language.
     * </p>
     * 
     * @param name   the name of the resource.
     * 
     * @param locale the {@link Locale} of the resource.
     * 
     * @return the {@link TextResource}.
     * 
     * @throws ResourcesException if the resource is not available.
     * 
     * @since 1.2
     */
    TextResource getResource(String name, Locale locale) throws ResourcesException;

}
