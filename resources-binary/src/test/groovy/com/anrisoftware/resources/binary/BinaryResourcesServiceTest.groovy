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

package com.anrisoftware.resources.binary

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static org.junit.jupiter.api.Assertions.assertThrows

import org.apache.sling.testing.mock.osgi.junit5.OsgiContext
import org.apache.sling.testing.mock.osgi.junit5.OsgiContextExtension
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.junit.jupiter.api.extension.ExtendWith

import com.anrisoftware.resources.api.external.ResourcesException
import com.anrisoftware.resources.binary.external.BinariesService
import com.anrisoftware.resources.binary.internal.AbstractBinaryResourcesTest
import com.anrisoftware.resources.binary.internal.binaries.BinariesServiceImpl
import com.anrisoftware.resources.binary.internal.maps.BinariesBundlesMapServiceImpl
import com.anrisoftware.resources.binary.internal.maps.BinariesMapServiceImpl

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

@Slf4j
@CompileStatic
@ExtendWith(OsgiContextExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class BinaryResourcesServiceTest extends AbstractBinaryResourcesTest {

    final OsgiContext context = new OsgiContext()

    @Test
    void "load lorem zipfile with different locale"() {
        super."load lorem zipfile with different locale"()
    }

    @Test
    void "test serialize binary resource"() {
        super."test serialize binary resource"()
    }

    @Test
    void "load missing resource"() {
        assertThrows(ResourcesException.class,{ super."load missing resource"() })
    }

    def createBinaries(def baseName) {
        service.create(baseName as String)
    }

    BinariesService service

    @BeforeAll
    void createFactories() {
        context.registerInjectActivateService(new BinariesBundlesMapServiceImpl(), null)
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
