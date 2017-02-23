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
package com.anrisoftware.resources.templates.internal.worker

import static com.anrisoftware.globalpom.utils.TestUtils.*

import org.apache.sling.testing.mock.osgi.junit.OsgiContext
import org.junit.Rule
import org.junit.Test

import com.anrisoftware.resources.st.internal.worker.STDefaultPropertiesServiceImpl
import com.anrisoftware.resources.st.internal.worker.STTemplateWorkerServiceImpl
import com.anrisoftware.resources.templates.external.TemplatesService
import com.anrisoftware.resources.templates.internal.AbstractTemplateResourceTest
import com.anrisoftware.resources.templates.internal.maps.TemplatesBundlesDefaultMapServiceImpl
import com.anrisoftware.resources.templates.internal.maps.TemplatesDefaultMapServiceImpl
import com.anrisoftware.resources.templates.internal.templates.TemplatesServiceImpl
import com.google.inject.Injector

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

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
