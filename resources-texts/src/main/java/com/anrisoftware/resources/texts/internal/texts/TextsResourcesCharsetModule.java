/*
 * Copyright 2012-2022 Erwin Müller <erwin.mueller@anrisoftware.com>
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
package com.anrisoftware.resources.texts.internal.texts;

import static com.google.inject.name.Names.named;

import java.nio.charset.Charset;

import com.google.inject.AbstractModule;

public class TextsResourcesCharsetModule extends AbstractModule {

    private final Charset defaultCharset;

    /**
     * Sets the default character set of the JVM as the default character set for
     * text resources.
     * 
     * @see Charset#defaultCharset()
     */
    public TextsResourcesCharsetModule() {
        this(Charset.defaultCharset());
    }

    /**
     * Sets the specified character set as the default character set for text
     * resources.
     * 
     * @param charset the {@link Charset}.
     */
    public TextsResourcesCharsetModule(Charset charset) {
        this.defaultCharset = charset;
    }

    @Override
    protected void configure() {
        bind(Charset.class).annotatedWith(named("texts-default-charset")).toInstance(defaultCharset);
    }

}
