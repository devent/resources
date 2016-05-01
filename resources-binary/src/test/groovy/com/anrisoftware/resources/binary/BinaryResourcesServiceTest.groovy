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
package com.anrisoftware.resources.binary

import static com.anrisoftware.globalpom.utils.TestUtils.*
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import org.apache.sling.testing.mock.osgi.junit.OsgiContext
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import com.anrisoftware.resources.binary.external.BinariesService
import com.anrisoftware.resources.binary.internal.binaries.BinariesServiceImpl
import com.anrisoftware.resources.binary.internal.maps.BinariesMapServiceImpl
import com.anrisoftware.resources.binary.internal.maps.BundlesMapServiceImpl
import com.anrisoftware.resources.external.ResourcesException

/**
 *
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 2.1
 */
@Slf4j
@CompileStatic
class BinaryResourcesServiceTest extends AbstractBinaryResourcesTest {

    @Rule
    public final OsgiContext context = new OsgiContext()

    @Test
    void "load lorem zipfile with different locale"() {
        super."load lorem zipfile with different locale"()
    }

    @Test
    void "test serialize binary resource"() {
        super."test serialize binary resource"()
    }

    @Test(expected = ResourcesException)
    void "load missing resource"() {
        super."load missing resource"()
    }

    def createBinaries(def baseName) {
        service.create(baseName as String)
    }

    BinariesService service

    @Before
    void createFactories() {
        context.registerInjectActivateService(new BundlesMapServiceImpl(), null)
        context.registerInjectActivateService(new BinariesMapServiceImpl(), null)
        this.service = context.registerInjectActivateService(new BinariesServiceImpl(), null)
    }

    @Override
    def createFactory() {
    }

    @Override
    def getResourcesModule() {
    }

    @Override
    def getMapModule() {
    }
}
