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

package com.anrisoftware.resources.templates.internal

import org.junit.jupiter.api.BeforeEach

import com.anrisoftware.globalpom.utils.TestUtils
import com.google.inject.Guice
import com.google.inject.Injector

abstract class AbstractTemplateResourceTestUtils extends TestUtils {

    Injector injector

    def factory

    def resourceFactory

    @BeforeEach
    void createFactories() {
        injector = createInjector()
        factory = createFactory()
        resourceFactory = createResourceFactory()
    }

    /**
     * Creates the needed modules if they are not already have been created.
     */
    Injector createInjector() {
        Guice.createInjector(templatesModule, templateWorkerModule,
                templatesMapModule, propertiesModule)
    }

    /**
     * Create the templates factory.
     */
    abstract createFactory()

    /**
     * Create the template resource factory.
     */
    abstract createResourceFactory()

    /**
     * Returns the module that binds the template resources.
     */
    abstract getTemplatesModule()

    /**
     * Returns the module that binds the template worker.
     */
    abstract getTemplateWorkerModule()

    /**
     * Returns the module that binds the template resources maps.
     */
    abstract getTemplatesMapModule()

    /**
     * Returns the URL of the template.
     */
    abstract getTemplateUrl()

    /**
     * Returns the template properties.
     */
    abstract getTemplateProperties()

    /**
     * Returns the module that binds the template properties.
     */
    abstract getPropertiesModule()
}
