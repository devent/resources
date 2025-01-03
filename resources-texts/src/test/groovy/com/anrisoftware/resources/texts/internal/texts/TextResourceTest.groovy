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
package com.anrisoftware.resources.texts.internal.texts

import org.junit.jupiter.api.Test

import com.anrisoftware.resources.texts.external.TextsFactory
import com.anrisoftware.resources.texts.internal.AbstractTextResourceTest
import com.anrisoftware.resources.texts.internal.maps.TextsDefaultMapsModule

class TextResourceTest extends AbstractTextResourceTest {

    @Test
    void "load plain text with defined locale"() {
        super."load plain text with defined locale"()
    }

    @Override
    def getTextsModule() {
        new TextsResourcesModule()
    }

    @Override
    def getTextsMapModule() {
        new TextsDefaultMapsModule()
    }

    @Override
    def createFactory() {
        injector.getInstance(TextsFactory)
    }

    @Override
    def getCharacterSetModule() {
        new TextsResourcesCharsetModule()
    }
}
