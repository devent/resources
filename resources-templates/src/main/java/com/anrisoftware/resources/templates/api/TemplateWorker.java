/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-templates.
 *
 * resources-templates is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-templates is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-templates. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.templates.api;

import java.io.Serializable;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import com.anrisoftware.resources.api.ResourcesException;

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
