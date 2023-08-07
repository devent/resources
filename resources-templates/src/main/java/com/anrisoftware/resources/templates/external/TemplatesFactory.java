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
package com.anrisoftware.resources.templates.external;

import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

public interface TemplatesFactory {

    /**
     * Creates a new {@link Templates} with the resource bundle base name and the
     * caller's class loader.
     * 
     * @param baseName the base name {@link String}.
     * 
     * @return {@link Templates}
     */
    Templates create(String baseName);

    /**
     * Creates a new {@link Templates} with the resource bundle base name and the
     * caller's class loader.
     * 
     * @param baseName   the base name {@link String}.
     * 
     * @param attributes the attributes {@link Map} for the template.
     * 
     * @return {@link Templates}
     * 
     * @since 1.4
     */
    Templates create(String baseName, Map<Serializable, Serializable> attributes);

    /**
     * Creates a new {@link Templates} with the resource bundle base name and the
     * class loader.
     * 
     * @param baseName    the base name {@link String}.
     * 
     * @param classLoader the {@link ClassLoader}.
     * 
     * @return {@link Templates}
     */
    Templates create(String baseName, ClassLoader classLoader);

    /**
     * Creates a new {@link Templates} with the resource bundle base name and the
     * class loader.
     * 
     * @param baseName    the base name {@link String}.
     * 
     * @param attributes  the attributes {@link Map} for the template.
     * 
     * @param classLoader the {@link ClassLoader}.
     * 
     * @return {@link Templates}
     * 
     * @since 1.4
     */
    Templates create(String baseName, Map<Serializable, Serializable> attributes, ClassLoader classLoader);

    /**
     * Creates a new {@link Templates} with the resource bundle base name and the
     * control.
     * 
     * @param baseName the base name {@link String}.
     * 
     * @param control  the {@link ResourceBundle.Control}.
     * 
     * @return {@link Templates}
     */
    Templates create(String baseName, ResourceBundle.Control control);

    /**
     * Creates a new {@link Templates} with the resource bundle base name and the
     * control.
     * 
     * @param baseName   the base name {@link String}.
     * 
     * @param attributes the attributes {@link Map} for the template.
     * 
     * @param control    the {@link ResourceBundle.Control}.
     * 
     * @return {@link Templates}
     * 
     * @since 1.4
     */
    Templates create(String baseName, Map<Serializable, Serializable> attributes, ResourceBundle.Control control);

    /**
     * Creates a new {@link Templates} with the resource bundle base name, the class
     * loader and the control.
     * 
     * @param baseName    the base name {@link String}.
     * 
     * @param classLoader the {@link ClassLoader}.
     * 
     * @param control     the {@link ResourceBundle.Control}.
     * 
     * @return {@link Templates}
     */
    Templates create(String baseName, ClassLoader classLoader, ResourceBundle.Control control);

    /**
     * Creates a new {@link Templates} with the resource bundle base name, the class
     * loader and the control.
     * 
     * @param baseName    the base name {@link String}.
     * 
     * @param attributes  the attributes {@link Map} for the template.
     * 
     * @param classLoader the {@link ClassLoader}.
     * 
     * @param control     the {@link ResourceBundle.Control}.
     * 
     * @return {@link Templates}
     * 
     * @since 1.4
     */
    Templates create(String baseName, Map<Serializable, Serializable> attributes, ClassLoader classLoader,
            ResourceBundle.Control control);

}
