
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
package com.anrisoftware.resources.templates.internal

import org.perf4j.LoggingStopWatch
import org.perf4j.StopWatch

/**
 * Test for functionality of the template resources.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.3
 */
abstract class AbstractTemplateResourceTest extends AbstractTemplateResourceTestUtils {

    void "load template"() {
        String[] args = ["arg1", "one", "arg2", "two"]
        def baseName = "Templates"
        def templates = factory.create baseName
        def template = templates.getResource "test"

        assertStringContent template.getText(args), "${args[1]}:${args[3]}"
        assert template.locale.toString() == ""
        assert template.URL.toString() =~ ".+"
    }

    void "load template with set class loader"() {
        String[] args = ["arg1", "one", "arg2", "two"]
        def baseName = "Templates"
        def classLoader = getClass().classLoader
        def templates = factory.create baseName, classLoader
        def template = templates.getResource "test"

        assertStringContent template.getText(args), "${args[1]}:${args[3]}"
        assert template.locale.toString() == ""
        assert template.URL.toString() =~ ".+"
    }

    void "serialize resource"() {
        String[] args = ["arg1", "one", "arg2", "two"]
        def baseName = "Templates"
        def templates = factory.create baseName
        def name = "test"
        def template = templates.getResource name

        assertStringContent template.getText(args), "${args[1]}:${args[3]}"
        assert template.locale.toString() == ""
        assert template.URL.toString() =~ ".+"
        def templateB = reserialize template

        assertStringContent templateB.getText(args), "${args[1]}:${args[3]}"
        assert templateB.name == name
    }

    void "load template with same data"() {
        String[] args = ["arg1", "one", "arg2", "two"]
        def baseName = "Templates"
        def templates = factory.create baseName
        def template = templates.getResource "test"

        StopWatch stopWatch = new LoggingStopWatch("get text from template")
        stopWatch.lap("Load template")
        assertStringContent template.getText(args), "${args[1]}:${args[3]}"
        stopWatch.lap("First access")
        assertStringContent template.getText(args), "${args[1]}:${args[3]}"
        assertStringContent template.getText(args), "${args[1]}:${args[3]}"
        assertStringContent template.getText(args), "${args[1]}:${args[3]}"
        stopWatch.stop("Additional access with same data")
    }
}
