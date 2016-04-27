/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-binary-tests.
 *
 * resources-binary-tests is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-binary-tests is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-binary-tests. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.binary

import static com.anrisoftware.globalpom.utils.TestUtils.*

/**
 * Test the binary resources.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractBinaryResourcesTest extends AbstractBinaryResourcesTestUtil {

    void "load lorem zipfile with different locale"() {
        inputs.eachWithIndex { it, i ->
            testBinaries(it.baseName, it.resources, outputs[i].resources)
        }
    }

    void "test serialize binary resource"() {
        def input = inputs[0]
        def resource = input.resources[0]
        def output = outputs[0].resources[0]
        def binaries = createBinaries(input.baseName)
        def binary = binaries.getResource resource.name, resource.locale
        def binaryB = reserialize(binary)
        testBinaryArgs(binaryB, [name: resource.name, locale: output.locale, url: output.url, size: output.availableBytes])
    }

    void "load missing resource"() {
        def binaries = createBinaries("Missing")
        def name = "none"
        def locale = Locale.ENGLISH
        def binary = binaries.getResource name, locale
    }

    def testBinaries(def baseName, def resources, def output) {
        def binaries = createBinaries(baseName)
        resources.eachWithIndex { it, i ->
            testBinary(output[i], binaries, it.name, it.locale)
        }
    }

    def testBinary(def output, def binaries, def name, def locale) {
        def binary = binaries.getResource name, locale
        testBinaryArgs(binary, [name: name, locale: output.locale, url: output.url, size: output.availableBytes])
    }

    def testBinaryArgs(def binary, def args) {
        assert binary.name == args.name
        assert binary.locale == args.locale
        assert binary.URL == args.url
        assert binary.binary.size() == args.size
        assert binary.stream.available() == args.size
    }
}
