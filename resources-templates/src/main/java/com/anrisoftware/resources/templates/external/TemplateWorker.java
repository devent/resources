
package com.anrisoftware.resources.templates.external;

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
import java.util.Map;
import java.util.Properties;

import com.anrisoftware.resources.api.external.ResourcesException;

/**
 * Process the calculated data and create a string for output.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface TemplateWorker extends Serializable {

    /**
     * Returns the URL of the template.
     *
     * @return the {@link URL} of the template.
     */
    URL getURL();

    /**
     * Returns the properties of the template.
     *
     * @return the {@link Properties} of the template.
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
     * Process the template and return the text.
     *
     * @param name
     *            the name of the template.
     *
     * @param data
     *            the data.
     *
     * @return the processed template as a string.
     *
     * @throws ResourcesException
     *             if an error has occurred while processing the template.
     */
    String process(String name, Object... data) throws ResourcesException;

    /**
     * Returns the template engine of the resource.
     *
     * @return the template engine of the resource.
     *
     * @since 2.1
     */
    <T> T getTemplate();
}