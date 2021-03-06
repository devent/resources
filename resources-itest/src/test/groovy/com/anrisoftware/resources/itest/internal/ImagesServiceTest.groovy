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

package com.anrisoftware.resources.itest.internal

import static org.ops4j.pax.exam.CoreOptions.*

import java.awt.Dimension

import javax.inject.Inject

import org.junit.Test
import org.junit.runner.RunWith
import org.ops4j.pax.exam.Option
import org.ops4j.pax.exam.junit.PaxExam
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy
import org.ops4j.pax.exam.spi.reactors.PerMethod

import com.anrisoftware.resources.images.external.ImagesService

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerMethod.class)
class ImagesServiceTest extends AbstractTestPax {

    @Inject
    ImagesService imagesService

    List<Option> createConfig(List<Option> options) {
        super.createConfig options
        options << mavenBundle("com.anrisoftware.resources", "resources-images").versionAsInProject()
    }

    @Test
    void "load logo"() {
        def b = imagesService.create 'Logos', ImagesServiceTest.class.classLoader
        def name = 'logo'
        def locale = Locale.GERMAN
        def size = new Dimension(171, 171)
        def res = b.getResource 'logo', locale, size
        assert res.name == name
        assert res.locale == locale
        assert res.URL == null
        assert res.getSizePx() == size
    }
}
