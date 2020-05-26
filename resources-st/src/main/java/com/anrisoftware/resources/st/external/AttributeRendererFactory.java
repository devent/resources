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

package com.anrisoftware.resources.st.external;

import java.util.Locale;

import org.stringtemplate.v4.AttributeRenderer;

/**
 * Wraps {@link AttributeRenderer} with a type to {@link StAttributeRenderer}.
 *
 * @author Erwin Müller
 */
public class AttributeRendererFactory {

    /**
     * @param type     the attribute type {@link Class}.
     *
     * @param renderer the {@link AttributeRenderer}.
     *
     * @param <T>      the type of the renderer.
     *
     * @return the {@link StAttributeRenderer}.
     *
     * @see #wrap(Class, AttributeRenderer)
     *
     * @since 4.5.2
     */
    public static <T> StAttributeRenderer<T> wrapAttributeRenderer(final Class<T> type,
            final AttributeRenderer<T> renderer) {
        return wrap(type, renderer);
    }

    /**
     * Wraps the ST attribute renderer with the attribute type.
     *
     * @param type     the attribute type {@link Class}.
     *
     * @param renderer the {@link AttributeRenderer}.
     *
     * @param <T>      the type of the renderer.
     *
     * @return the {@link StAttributeRenderer}.
     *
     * @since 4.5.2
     */
    @SuppressWarnings("serial")
    public static <T> StAttributeRenderer<T> wrap(final Class<T> type, final AttributeRenderer<T> renderer) {
        return new StAttributeRenderer<>() {

            @Override
            public String toString(T o, String formatString, Locale locale) {
                return renderer.toString(o, formatString, locale);
            }

            @Override
            public Class<T> getAttributeType() {
                return type;
            }

        };
    }

}
