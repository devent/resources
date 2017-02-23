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

import com.anrisoftware.resources.binary.external.BinariesService

/**
 *
 *
 * @author Erwin Müller <erwin.mueller@deventm.de>
 * @version 2.2
 */
@RunWith(PaxExam.class)
@ExamReactorStrategy(PerMethod.class)
class BinariesServiceTest extends AbstractTestPax {

    @Inject
    BinariesService binariesService

    List<Option> createConfig(List<Option> options) {
        super.createConfig options
        options << mavenBundle("com.anrisoftware.resources", "resources-binary").versionAsInProject()
    }

    @Test
    void "load lorem zipfile"() {
        def b = binariesService.create 'Zipfiles', BinariesServiceTest.class.classLoader
        def locale = Locale.GERMAN
        def res = b.getResource 'lorem', locale
        assert res.name == 'lorem'
        assert res.locale == locale
        assert res.URL.toString() =~ 'bundle://.*Lorem ipsum\\.html\\.zip'
        assert res.binary.size() == 71639
        assert res.stream.available() == 71639
    }
}
