
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
package com.anrisoftware.resources.itest.internal

import static org.ops4j.pax.exam.CoreOptions.*

import javax.inject.Inject

import org.junit.Test
import org.junit.runner.RunWith
import org.ops4j.pax.exam.Option
import org.ops4j.pax.exam.junit.PaxExam
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy
import org.ops4j.pax.exam.spi.reactors.PerMethod

import com.anrisoftware.resources.texts.external.TextsService

/**
 *
 *
 * @author Erwin Müller <erwin.mueller@deventm.de>
 * @version 2.2
 */
@RunWith(PaxExam.class)
@ExamReactorStrategy(PerMethod.class)
class TextsServiceTest extends AbstractTestPax {

    @Inject
    TextsService textsService

    List<Option> createConfig(List<Option> options) {
        super.createConfig options
        options << mavenBundle("com.anrisoftware.resources", "resources-binary").versionAsInProject()
        options << mavenBundle("com.anrisoftware.resources", "resources-texts").versionAsInProject()
    }

    @Test
    void "load text file"() {
        def b = textsService.create 'Texts', TextsServiceTest.class.classLoader
        def locale = Locale.GERMAN
        def res = b.getResource 'hello', locale
        assert res.name == 'hello'
        assert res.locale == locale
        assert res.URL.toString() =~ 'bundle://.*hello\\.txt'
    }
}
