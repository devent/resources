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

package com.anrisoftware.resources.st.internal.worker;

import java.io.Serializable;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import com.anrisoftware.resources.templates.external.TemplateWorker;
import com.anrisoftware.resources.templates.external.TemplateWorkerFactory;

public interface STTemplateWorkerFactory extends TemplateWorkerFactory {

    /**
     * Creates a new template worker that is using a <a href=
     * "https://github.com/antlr/stringtemplate4/blob/master/doc/index.md">StringTemplate</a>
     * template engine.
     *
     * @param templateUrl the {@link URL} of the template group file.
     *
     * @param properties  the {@link Properties} for the template group file. Have
     *                    the properties:
     *                    <ul>
     *                    <li>{@value STTemplateProperties#ENCODING_PROPERTY}</li>
     *                    <li>{@value STTemplateProperties#DELIMITER_START_CHAR_PROPERTY}</li>
     *                    <li>{@value STTemplateProperties#DELIMITER_STOP_CHAR_PROPERTY}</li>
     *                    </ul>
     *
     * @param attributes  the attributes {@link Map} for the template. Can contain
     *                    the attribute renderers in the map key
     *                    {@link STTemplateProperties#RENDERERS_KEY}.
     *
     *
     * @return the {@link TemplateWorker}.
     */
    @Override
    TemplateWorker create(URL templateUrl, Properties properties, Map<Serializable, Serializable> attributes);
}
