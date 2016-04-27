/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-binary.
 *
 * resources-binary is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-binary is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-binary. If not, see <http://www.gnu.org/licenses/>.
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
