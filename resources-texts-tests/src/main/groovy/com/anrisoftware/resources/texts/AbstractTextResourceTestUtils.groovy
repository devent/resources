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
package com.anrisoftware.resources.texts

import static com.anrisoftware.globalpom.utils.TestUtils.*

import org.junit.Before
import org.junit.BeforeClass

import com.anrisoftware.resources.binary.internal.binaries.BinariesResourcesModule
import com.anrisoftware.resources.binary.internal.maps.BinariesDefaultMapsModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Creates the environment to unit test the texts resources. Adds binary
 * resources modules.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractTextResourceTestUtils {

    Injector injector

    def factory

    @BeforeClass
    static void setupToStringStyle() {
        toStringStyle
    }

    @Before
    void createFactories() {
        injector = createInjector()
        factory = createFactory()
    }

    /**
     * Creates the needed modules if they are not already have been created.
     *
     * @since 1.2
     */
    Injector createInjector() {
        Guice.createInjector(textsModule,
                textsMapModule,
                characterSetModule,
                binariesModule,
                binariesMapModule)
    }

    /**
     * Create the texts resources factory.
     *
     * @since 1.2
     */
    abstract createFactory()

    /**
     * Returns the module that binds the texts resources.
     */
    abstract getTextsModule()

    /**
     * Returns the module that binds the texts map.
     */
    abstract getTextsMapModule()

    /**
     * Returns the module that binds the default character set for
     * text resources.
     *
     * @since 1.2
     */
    abstract getCharacterSetModule()

    /**
     * Returns the module that binds the binaries resources.
     */
    def getBinariesModule() {
        new BinariesResourcesModule()
    }

    /**
     * Returns the module that binds the binaries map.
     */
    def getBinariesMapModule() {
        new BinariesDefaultMapsModule()
    }
}
