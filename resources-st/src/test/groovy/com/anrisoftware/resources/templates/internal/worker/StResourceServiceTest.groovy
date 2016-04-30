/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-st.
 *
 * resources-st is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-st is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-st. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.templates.internal.worker

import static com.anrisoftware.globalpom.utils.TestUtils.*
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import org.apache.sling.testing.mock.osgi.junit.OsgiContext
import org.junit.Rule
import org.junit.Test

import com.anrisoftware.resources.templates.AbstractTemplateResourceTest
import com.anrisoftware.resources.templates.external.TemplatesService
import com.anrisoftware.resources.templates.internal.maps.TemplatesBundlesDefaultMapServiceImpl
import com.anrisoftware.resources.templates.internal.maps.TemplatesDefaultMapServiceImpl
import com.anrisoftware.resources.templates.internal.templates.TemplatesServiceImpl
import com.google.inject.Injector

/**
 *
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 2.1
 */
@Slf4j
@CompileStatic
class StResourceServiceTest extends AbstractTemplateResourceTest {

    @Rule
    public final OsgiContext context = new OsgiContext()

    TemplatesService service

    @Test
    void "load ST template"() {
        super."load template"()
    }

    @Test
    void "load template with set class loader"() {
        super."load template with set class loader"()
    }

    @Test
    void "serialize resource"() {
        super."serialize resource"()
    }

    @Test
    void "load ST template with same data"() {
        super."load template with same data"()
    }

    Injector createInjector() {
    }

    def getTemplatesModule() {
    }

    def getTemplateWorkerModule() {
    }

    def getTemplatesMapModule() {
    }

    @Override
    def createFactory() {
        context.registerInjectActivateService(new STDefaultPropertiesServiceImpl(), null)
        context.registerInjectActivateService(new STTemplateWorkerServiceImpl(), null)
        context.registerInjectActivateService(new TemplatesBundlesDefaultMapServiceImpl(), null)
        context.registerInjectActivateService(new TemplatesDefaultMapServiceImpl(), null)
        this.service = context.registerInjectActivateService(new TemplatesServiceImpl(), null)
    }

    @Override
    def createResourceFactory() {
    }

    @Override
    def getTemplateUrl() {
        StResourceServiceTest.class.getResource("testtemplate.stg")
    }

    @Override
    def getTemplateProperties() {
    }

    @Override
    def getPropertiesModule() {
    }
}
