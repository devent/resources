
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
package com.anrisoftware.resources.images.internal

import java.awt.Dimension

import org.junit.jupiter.api.BeforeEach

/**
 * Tests for images map.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractImagesMapTest extends AbstractImageResourcesTestUtils {

    static en = Locale.ENGLISH

    def map

    def noDublicates

    def withDublicates

    def outputsWithDublicates

    def customSizes

    @BeforeEach
    void createImagesMap() {
        def factory = createImagesMapFactory()
        map = factory.create()
    }

    private "create data with custom height and width"() {
        def images = [
            createImage([name: "imageA", locale: en, resolution: low,    image: "00", width: 1, height: 2]),
            createImage([name: "imageA", locale: en, resolution: medium, image: "01", width: 1, height: 3]),
            createImage([name: "imageA", locale: en, resolution: high,   image: "02", width: 1, height: 4]),
        ]
        customSizes = [
            inputs: [
                images[0],
                images[1],
                images[2],
            ],
            outputs: [
                [name: "imageA", width: 1, height: 1, image: images[0], resolution: low],
                [name: "imageA", width: 1, height: 2, image: images[0], resolution: low],
                [name: "imageA", width: 1, height: 3, image: images[1], resolution: medium],
                [name: "imageA", width: 1, height: 4, image: images[2], resolution: high],
                [name: "imageA", width: 1, height: 5, image: images[2], resolution: high],
            ],
        ]
    }

    private "create data containing duplicates"() {
        def images = [
            createImage([name: "imageA", locale: en, resolution: low,    image: "00", width: 1, height: 2]),
            createImage([name: "imageA", locale: en, resolution: low,    image: "01", width: 1, height: 2]),
            createImage([name: "imageA", locale: en, resolution: low,    image: "02", width: 1, height: 2]),
            createImage([name: "imageA", locale: en, resolution: medium, image: "03", width: 1, height: 2]),
            createImage([name: "imageA", locale: en, resolution: high,   image: "04", width: 1, height: 2]),
        ]
        withDublicates = [
            inputs: [
                images[0],
                images[1],
                images[2],
                images[3],
                images[4],
            ],
            outputs: [
                images[0],
                images[3],
                images[4],
            ]]
    }

    private "create data containing no duplicates"() {
        noDublicates = [
            inputs_and_outputs: [
                createImage([name: "imageA", locale: en, resolution: low,    image: "00", width: 1, height: 2]),
                createImage([name: "imageA", locale: en, resolution: low,    image: "01", width: 1, height: 3]),
                createImage([name: "imageA", locale: en, resolution: low,    image: "02", width: 1, height: 4]),
                createImage([name: "imageA", locale: en, resolution: medium, image: "03", width: 1, height: 2]),
                createImage([name: "imageA", locale: en, resolution: high,   image: "04", width: 1, height: 2]),
                createImage([name: "imageB", locale: en, resolution: low,    image: "05", width: 1, height: 2]),
                createImage([name: "imageB", locale: en, resolution: medium, image: "06", width: 1, height: 2]),
                createImage([name: "imageB", locale: en, resolution: high,   image: "07", width: 1, height: 2]),
                createImage([name: "imageC", locale: en, resolution: low,    image: "08", width: 1, height: 2]),
                createImage([name: "imageC", locale: en, resolution: medium, image: "09", width: 1, height: 2]),
                createImage([name: "imageC", locale: en, resolution: high,   image: "10", width: 1, height: 2]),
                createImage([name: "imageD", locale: en, resolution: low,    image: "11", width: 1, height: 2]),
                createImage([name: "imageD", locale: en, resolution: medium, image: "12", width: 1, height: 2]),
                createImage([name: "imageD", locale: en, resolution: high,   image: "13", width: 1, height: 2]),
            ]]
    }

    void "put images to map without dublicates"() {
        "create data containing no duplicates"()
        noDublicates.inputs_and_outputs.each { map.putImage it }
        noDublicates.inputs_and_outputs.each {
            def size = new Dimension(it.widthPx, it.heightPx)
            def image = map.getImage it.name, size, it.resolution
            assert image == it
        }
    }

    void "put images to map with dublicates"() {
        "create data containing duplicates"()
        withDublicates.inputs.each { map.putImage it }
        withDublicates.outputs.each {
            def size = new Dimension(it.widthPx, it.heightPx)
            def image = map.getImage it.name, size, it.resolution
            assert image == it
        }
    }

    void "return custom sizes of images"() {
        "create data with custom height and width"()
        customSizes.inputs.each { map.putImage it }
        customSizes.outputs.each {
            def size = new Dimension(it.width, it.height)
            def image = map.getImage it.name, size, it.resolution
            assert image == it.image
        }
    }

    void "return custom sizes of images with auto resolution"() {
        "create data with custom height and width"()
        customSizes.inputs.each { map.putImage it }
        customSizes.outputs.each {
            def size = new Dimension(it.width, it.height)
            def image = map.getImage it.name, size
            assert image == it.image
        }
    }
}
