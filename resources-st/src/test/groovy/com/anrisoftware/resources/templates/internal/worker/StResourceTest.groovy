/*
 * Copyright 2012-2021 Erwin Müller <erwin.mueller@anrisoftware.com>
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

import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.assertThrows

import com.anrisoftware.resources.api.external.ResourcesException
import com.anrisoftware.resources.st.internal.worker.STDefaultPropertiesFactory
import com.anrisoftware.resources.st.internal.worker.STDefaultPropertiesModule
import com.anrisoftware.resources.st.internal.worker.STWorkerModule
import com.anrisoftware.resources.templates.external.TemplateResourceFactory
import com.anrisoftware.resources.templates.external.Templates
import com.anrisoftware.resources.templates.external.TemplatesFactory
import com.anrisoftware.resources.templates.internal.AbstractTemplateResourceTest
import com.anrisoftware.resources.templates.internal.maps.TemplatesDefaultMapsModule
import com.anrisoftware.resources.templates.internal.templates.TemplatesResourcesModule

class StResourceTest extends AbstractTemplateResourceTest {

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

    @Test
    void "load ST template in a template group"() {
        String[] args = [
            "st",
            "arg1",
            "one",
            "arg2",
            "two"
        ]
        def baseName = "Templates"
        def templates = factory.create baseName
        def template = templates.getResource "stttemplate"

        assertStringContent template.getText(args), "${args[2]}:${args[4]}"
        assert template.locale.toString() == ""
        assert template.URL.toString() =~ ".+"
    }

    @Test
    void "load multiple ST template in same template group"() {
        String[] args = [
            "foo",
            "arg1",
            "foo1",
            "arg2",
            "foo2"
        ]
        def baseName = "Templates"
        Templates templates = factory.create baseName
        def template = templates.getResource "multipletemplates"

        assertStringContent template.getText(args), "${args[2]}:${args[4]}"

        args = [
            "bar",
            "arg1",
            "bar1",
            "arg2",
            "bar2"
        ]
        template.invalidate()
        assertStringContent template.getText(args), "${args[2]}:${args[4]}"
    }

    @Test
    void "load missng template"() {
        String[] args = [
            "arg1",
            "one",
            "arg2",
            "two"
        ]
        def baseName = "Templates"
        def templates = factory.create baseName
        def template = templates.getResource "errorstemplate"
        assertThrows(ResourcesException.class, { template.getText(args) })
    }

    @Test
    void "load template with errors"() {
        String[] args = [
            "test",
            "arg1",
            "one",
            "arg2",
            "two"
        ]
        def baseName = "Templates"
        def templates = factory.create baseName
        def template = templates.getResource "errorstemplate"
        assertThrows(ResourcesException.class, { template.getText(args) })
    }

    def getTemplatesModule() {
        new TemplatesResourcesModule()
    }

    def getTemplateWorkerModule() {
        new STWorkerModule()
    }

    def getTemplatesMapModule() {
        new TemplatesDefaultMapsModule()
    }

    @Override
    def createFactory() {
        injector.getInstance TemplatesFactory
    }

    @Override
    def createResourceFactory() {
        injector.getInstance TemplateResourceFactory
    }

    @Override
    def getTemplateUrl() {
        StResourceTest.class.getResource("testtemplate.stg")
    }

    @Override
    def getTemplateProperties() {
        injector.getInstance STDefaultPropertiesFactory
    }

    @Override
    def getPropertiesModule() {
        new STDefaultPropertiesModule()
    }
}
