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
package com.anrisoftware.resources.st.internal.worker;


import static java.lang.System.getProperties;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import com.anrisoftware.propertiesutils.ContextPropertiesFactory;
import com.anrisoftware.resources.templates.external.TemplatesPropertiesFactory;

public class STDefaultPropertiesFactory implements TemplatesPropertiesFactory {

    static final URL PROPERTIES_URL = STDefaultPropertiesFactory.class
            .getResource("stringtemplate.properties");

    @Override
    public Properties create() throws IOException {
        return new ContextPropertiesFactory(STTemplateWorker.class)
                .withProperties(getProperties()).fromResource(PROPERTIES_URL);
    }

}
