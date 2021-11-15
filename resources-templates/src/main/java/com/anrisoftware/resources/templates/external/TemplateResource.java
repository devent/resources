/*
 * Copyright 2012-2021 Erwin Müller <erwin.mueller@anrisoftware.com>
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
import java.util.Properties;

import com.anrisoftware.resources.api.external.Resource;
import com.anrisoftware.resources.api.external.ResourcesException;

public interface TemplateResource extends Resource {

    /**
     * Process the template and returns the text.
     * <p>
     * The processed text is cached and is returned for the same data.
     *
     * @param data the data for the template.
     *
     * @return {@link String} the text.
     *
     * @throws ResourcesException if there was an error loading the text.
     */
    String getText(Object... data);

    /**
     * Process the template and returns the text.
     * <p>
     * The processed text is cached and is returned for the same data.
     *
     * @param invalidate set to {@code true} to invalidate the cache before
     *                   processing the text.
     *
     * @param data       the data for the template.
     *
     * @return {@link String} the text.
     *
     * @throws ResourcesException if there was an error loading the text.
     *
     * @since 1.1
     */
    String getText(boolean invalidate, Object... data);

    /**
     * Invalidate the template resource. Delete the cached resource so a subsequent
     * call of {@link #getText(Object...)} will process the template.
     * <p>
     * Use this if the data for the template have changed.
     */
    void invalidate();

    /**
     * Returns the properties of the template resource.
     *
     * @return the {@link Properties}.
     */
    Properties getProperties();

    /**
     * Returns the attributes for the template engine.
     *
     * @return the attributes {@link Map}.
     *
     * @since 1.4
     */
    Map<Serializable, Serializable> getAttributes();

    /**
     * Returns the template engine of the resource.
     * 
     * @param <T> the type of the engine.
     *
     * @return the template engine of the resource.
     *
     * @since 2.1
     */
    <T> T getTemplate();
}
