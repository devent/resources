/*
 * Copyright 2016 Erwin Müller <erwin.mueller@deventm.org>
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

import org.junit.Test

import com.anrisoftware.resources.binary.external.BinariesFactory
import com.anrisoftware.resources.binary.internal.binaries.BinariesResourcesModule
import com.anrisoftware.resources.binary.internal.maps.BinariesDefaultMapsModule
import com.anrisoftware.resources.external.ResourcesException

/**
 * Test the binary resources.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class BinaryResourcesTest extends AbstractBinaryResourcesTest {

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

    @Override
    def createFactory() {
        injector.getInstance(BinariesFactory)
    }

    @Override
    def getResourcesModule() {
        new BinariesResourcesModule()
    }

    @Override
    def getMapModule() {
        new BinariesDefaultMapsModule()
    }
}
