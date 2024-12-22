/*
 * Copyright 2012-2025 Erwin Müller <erwin.mueller@anrisoftware.com>
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
package com.anrisoftware.resources.texts.external;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;

public interface TextResourceFactory {

    /**
     * Creates a new text resource which will load the text from the URL.
     * 
     * @param name    the name {@link String} of this resource.
     * 
     * @param locale  the {@link Locale} this text resource.
     * 
     * @param url     the {@link URL} of the resource.
     * 
     * @param charset the {@link Charset} of the resource.
     * 
     * @return {@link TextResource}
     * 
     * @since 1.1
     */
    TextResource create(String name, Locale locale, URL url, Charset charset);
}
