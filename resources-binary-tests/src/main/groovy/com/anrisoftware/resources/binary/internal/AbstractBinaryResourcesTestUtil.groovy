/*-
 * #%L
 * Resources :: Binary Tests
 * %%
 * Copyright (C) 2012 - 2018 Advanced Natural Research Institute
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
/*
 * Copyright 2017 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.binary.internal

import static com.anrisoftware.globalpom.utils.TestUtils.*

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach

import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Creates the environment to test the binary resources.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractBinaryResourcesTestUtil {

    static inputs

    static outputs

    @BeforeAll
    static void setupToStringStyle() {
        toStringStyle
    }

    @BeforeAll
    static void createTestIO() {
        inputs = [
            [baseName: "Zipfiles", resources: [
                    [name: "lorem", locale: Locale.GERMAN],
                    [name: "lorem1", locale: Locale.GERMAN],
                    [name: "lorem2", locale: Locale.GERMAN],
                    [name: "lorem3", locale: Locale.GERMAN],
                    [name: "lorem4", locale: Locale.GERMAN],
                    [name: "lorem5", locale: Locale.GERMAN],
                    [name: "lorem6", locale: Locale.GERMAN],
                    [name: "lorem7", locale: Locale.GERMAN],
                    [name: "lorem8", locale: Locale.GERMAN],
                    [name: "lorem9", locale: Locale.GERMAN],
                    [name: "lorem", locale: new Locale("ru")],
                    [name: "lorem", locale: null]
                ]],
        ]
        def zipfiles = '/com/anrisoftware/resources/binary/zipfiles'
        outputs = [
            [baseName: "Zipfiles", resources: [
                    [url: resource("$zipfiles/de/Lorem ipsum.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
                    [url: resource("$zipfiles/de/Lorem ipsum-1.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
                    [url: resource("$zipfiles/de/Lorem ipsum-2.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
                    [url: resource("$zipfiles/de/Lorem ipsum-3.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
                    [url: resource("$zipfiles/de/Lorem ipsum-4.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
                    [url: resource("$zipfiles/de/Lorem ipsum-5.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
                    [url: resource("$zipfiles/de/Lorem ipsum-6.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
                    [url: resource("$zipfiles/de/Lorem ipsum-7.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
                    [url: resource("$zipfiles/de/Lorem ipsum-8.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
                    [url: resource("$zipfiles/de/Lorem ipsum-9.html.zip"), locale: Locale.GERMAN, availableBytes: 71639],
                    [url: resource("$zipfiles/ru/Lorem ipsum.html.zip"), locale: new Locale("ru"), availableBytes: 71906],
                    [url: resource("$zipfiles/Lorem ipsum.html.zip"), locale: Locale.getDefault(), availableBytes: 71573],
                ]],
        ]
    }

    static URL resource(String name) {
        AbstractBinaryResourcesTestUtil.class.getResource(name)
    }

    Injector injector

    def factory

    @BeforeEach
    void createFactories() {
        injector = createInjector()
        factory = createFactory()
    }

    Injector createInjector() {
        Guice.createInjector(resourcesModule, mapModule)
    }

    def createBinaries(def baseName) {
        factory.create(baseName)
    }

    /**
     * Create the binary resources factory.
     */
    abstract createFactory()

    /**
     * Returns the binary resources module.
     */
    abstract getResourcesModule()

    /**
     * Returns the binary resources map module.
     */
    abstract getMapModule()
}
