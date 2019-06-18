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

package com.anrisoftware.resources.texts.internal.texts

import org.apache.sling.testing.mock.osgi.junit5.OsgiContext
import org.apache.sling.testing.mock.osgi.junit5.OsgiContextExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

import com.anrisoftware.resources.binary.internal.binaries.BinariesServiceImpl
import com.anrisoftware.resources.binary.internal.maps.BinariesBundlesMapServiceImpl
import com.anrisoftware.resources.binary.internal.maps.BinariesMapServiceImpl
import com.anrisoftware.resources.binary.internal.resources.BinaryResourceServiceImpl
import com.anrisoftware.resources.texts.external.TextsService
import com.anrisoftware.resources.texts.internal.AbstractTextResourceTest
import com.anrisoftware.resources.texts.internal.maps.TextsBundlesMapServiceImpl
import com.anrisoftware.resources.texts.internal.maps.TextsMapDefaultServiceImpl
import com.google.inject.Injector

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

/**
 *
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
@Slf4j
@CompileStatic
@ExtendWith(OsgiContextExtension.class)
class TextsResourcesServiceTest extends AbstractTextResourceTest {

    public final OsgiContext context = new OsgiContext()

    @Test
    void "load plain text with defined locale"() {
        super."load plain text with defined locale"()
    }

    TextsService service

    @Override
    def createFactory() {
        context.registerInjectActivateService(new BinariesBundlesMapServiceImpl(), null)
        context.registerInjectActivateService(new BinariesMapServiceImpl(), null)
        context.registerInjectActivateService(new BinariesServiceImpl(), null)
        context.registerInjectActivateService(new BinaryResourceServiceImpl(), null)
        context.registerInjectActivateService(new TextsBundlesMapServiceImpl(), null)
        context.registerInjectActivateService(new TextsMapDefaultServiceImpl(), null)
        this.service = context.registerInjectActivateService(new TextsServiceImpl(), null)
    }

    @Override
    Injector createInjector() {
    }

    @Override
    def getTextsModule() {
    }

    @Override
    def getTextsMapModule() {
    }

    @Override
    def getCharacterSetModule() {
    }

    @Override
    def getBinariesModule() {
    }

    @Override
    def getBinariesMapModule() {
    }
}
