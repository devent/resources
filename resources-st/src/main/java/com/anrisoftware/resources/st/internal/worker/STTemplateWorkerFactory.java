
package com.anrisoftware.resources.st.internal.worker;

/*-
 * #%L
 * Resources :: Templates :: ST
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

import org.stringtemplate.v4.STGroup;

import com.anrisoftware.resources.st.external.AttributeRenderer;
import com.anrisoftware.resources.templates.external.TemplateWorker;
import com.anrisoftware.resources.templates.external.TemplateWorkerFactory;

/**
 * Factory to create a new template worker that is using a <a
 * href=http://www.antlr.org/wiki/display/ST4/StringTemplate+4+Wiki+Home>String
 * Template</a> template engine.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface STTemplateWorkerFactory extends TemplateWorkerFactory {

    static final String DELIMITER_STOP_CHAR_PROPERTY = "template_delimiter_stop_character";

    static final String DELIMITER_START_CHAR_PROPERTY = "template_delimiter_start_character";

    static final String ENCODING_PROPERTY = "template_encoding";

    /**
     * The map key for the attribute renderers.
     *
     * @see AttributeRenderer
     */
    static final String RENDERERS_KEY = "renderers";

    /**
     * The map key for the attribute imports.
     *
     * @see STGroup#importTemplates(STGroup)
     *
     * @since 2.1
     */
    static final String IMPORTS_KEY = "imports";

    /**
     * Creates a new template worker that is using a <a
     * href=http://www.antlr.org
     * /wiki/display/ST4/StringTemplate+4+Wiki+Home>String Template</a> template
     * engine.
     *
     * @param templateUrl
     *            the {@link URL} of the template group file.
     *
     * @param properties
     *            the {@link Properties} for the template group file. Have the
     *            properties:
     *            <ul>
     *            <li>{@value #ENCODING_PROPERTY}</li>
     *            <li>{@value #DELIMITER_START_CHAR_PROPERTY}</li>
     *            <li>{@value #DELIMITER_STOP_CHAR_PROPERTY}</li>
     *            </ul>
     *
     * @param attributes
     *            the attributes {@link Map} for the template. Can contain the
     *            attribute renderers in the map key {@link RENDERERS_KEY}.
     *
     *
     * @return the {@link TemplateWorker}.
     */
    @Override
    TemplateWorker create(URL templateUrl, Properties properties,
            Map<Serializable, Serializable> attributes);
}
