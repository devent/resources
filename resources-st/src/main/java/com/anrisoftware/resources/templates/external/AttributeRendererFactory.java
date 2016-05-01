/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
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

import java.util.Locale;

/**
 * Factory to create a new attribute renderer.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.6
 */
public class AttributeRendererFactory {

    /**
     * @see #wrap(Class, org.stringtemplate.v4.AttributeRenderer)
     */
    public static AttributeRenderer wrapAttributeRenderer(final Class<?> type,
            final org.stringtemplate.v4.AttributeRenderer renderer) {
        return wrap(type, renderer);
    }

    /**
     * Wraps the ST attribute renderer with the attribute type.
     *
     * @param type
     *            the attribute type {@link Class}.
     *
     * @param renderer
     *            the {@link org.stringtemplate.v4.AttributeRenderer}.
     *
     * @return the {@link AttributeRenderer}.
     */
    @SuppressWarnings("serial")
    public static AttributeRenderer wrap(final Class<?> type,
            final org.stringtemplate.v4.AttributeRenderer renderer) {
        return new AttributeRenderer() {

            @Override
            public String toString(Object o, String formatString, Locale locale) {
                return renderer.toString(o, formatString, locale);
            }

            @Override
            public Class<?> getAttributeType() {
                return type;
            }

        };
    }

}
