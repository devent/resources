/*
 * Copyright ${project.custom.year} Erwin Müller <erwin.mueller@anrisoftware.com>
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

import org.stringtemplate.v4.STGroup;

import com.anrisoftware.resources.st.external.StAttributeRenderer;

/**
 * @since 4.5.1
 *
 */
public enum STTemplateProperties {

    DELIMITER_STOP_CHAR_PROPERTY("template_delimiter_stop_character"),

    DELIMITER_START_CHAR_PROPERTY("template_delimiter_start_character"),

    ENCODING_PROPERTY("template_encoding"),

    /**
     * The map key for the attribute renderers.
     *
     * @see StAttributeRenderer
     */
    RENDERERS_KEY("renderers"),

    /**
     * The map key for the attribute imports.
     *
     * @see STGroup#importTemplates(STGroup)
     *
     * @since 2.1
     */
    IMPORTS_KEY("imports");

    private String property;

    private STTemplateProperties(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }
}
