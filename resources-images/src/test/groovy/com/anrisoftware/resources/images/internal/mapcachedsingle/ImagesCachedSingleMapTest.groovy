/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-images.
 *
 * resources-images is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-images is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-images. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.images.internal.mapcachedsingle

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.google.inject.Guice.createInjector
import groovy.util.logging.Slf4j

import java.awt.Dimension

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.resources.images.external.ImageResolution
import com.anrisoftware.resources.images.external.ImageResourceFactory
import com.anrisoftware.resources.images.external.ImagesMapFactory
import com.anrisoftware.resources.images.internal.resource.ImageResourceModule
import com.google.inject.Injector

/**
 * @see ResourcesImagesCachedSingleMapModule
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.18
 */
@Slf4j
class ImagesCachedSingleMapTest {

    @Test
    void "get image for unset resolution"() {
        def map = factory.create()
        def testCases = [
            [
                keyImages: [
                    imageResourceFactory.create("a", Locale.US, ImageResolution.LOW, testLdpi),
                    imageResourceFactory.create("a", Locale.US, ImageResolution.HIGH, testHdpi),
                    imageResourceFactory.create("a", Locale.US, ImageResolution.EXTRA_HIGH, testXhdpi),
                ],
                getImage: [
                    name: "a",
                    size: new Dimension(31, 31),
                    resolution: ImageResolution.MEDIUM,
                ],
                expected: [
                    name: "a",
                    size: new Dimension(32, 32),
                    resolution: ImageResolution.LOW,
                ]
            ],
            [
                keyImages: [
                    imageResourceFactory.create("a", Locale.US, ImageResolution.LOW, testLdpi),
                    imageResourceFactory.create("a", Locale.US, ImageResolution.HIGH, testHdpi),
                    imageResourceFactory.create("a", Locale.US, ImageResolution.EXTRA_HIGH, testXhdpi),
                ],
                getImage: [
                    name: "a",
                    size: new Dimension(33, 33),
                    resolution: ImageResolution.MEDIUM,
                ],
                expected: [
                    name: "a",
                    size: new Dimension(128, 128),
                    resolution: ImageResolution.HIGH,
                ]
            ],
            [
                keyImages: [
                    imageResourceFactory.create("a", Locale.US, ImageResolution.LOW, testLdpi),
                    imageResourceFactory.create("a", Locale.US, ImageResolution.HIGH, testHdpi),
                    imageResourceFactory.create("a", Locale.US, ImageResolution.EXTRA_HIGH, testXhdpi),
                ],
                getImage: [
                    name: "a",
                    size: new Dimension(128, 128),
                    resolution: ImageResolution.MEDIUM,
                ],
                expected: [
                    name: "a",
                    size: new Dimension(128, 128),
                    resolution: ImageResolution.HIGH,
                ]
            ],
            [
                keyImages: [
                    imageResourceFactory.create("a", Locale.US, ImageResolution.LOW, testLdpi),
                    imageResourceFactory.create("a", Locale.US, ImageResolution.HIGH, testHdpi),
                    imageResourceFactory.create("a", Locale.US, ImageResolution.EXTRA_HIGH, testXhdpi),
                ],
                getImage: [
                    name: "a",
                    size: new Dimension(129, 129),
                    resolution: ImageResolution.MEDIUM,
                ],
                expected: [
                    name: "a",
                    size: new Dimension(256, 256),
                    resolution: ImageResolution.EXTRA_HIGH,
                ]
            ],
            [
                keyImages: [
                    imageResourceFactory.create("a", Locale.US, ImageResolution.LOW, testLdpi),
                    imageResourceFactory.create("a", Locale.US, ImageResolution.HIGH, testHdpi),
                    imageResourceFactory.create("a", Locale.US, ImageResolution.EXTRA_HIGH, testXhdpi),
                ],
                getImage: [
                    name: "a",
                    size: new Dimension(257, 257),
                    resolution: ImageResolution.MEDIUM,
                ],
                expected: [
                    name: "a",
                    size: new Dimension(256, 256),
                    resolution: ImageResolution.EXTRA_HIGH,
                ]
            ],
        ]
        testCases.eachWithIndex { testCase, int k ->
            log.info "{} case: {}", k, testCase
            testCase.keyImages.each { map.putImage it }
            def image = map.getImage testCase.getImage.name, testCase.getImage.size, testCase.getImage.resolution
            assert image.getName() == testCase.expected.name
            assert image.getSizePx() == testCase.expected.size
            assert image.getResolution() == testCase.expected.resolution
        }
    }

    @Test
    void "get image for size"() {
        def map = factory.create()
        def keyImages = [
            imageResourceFactory.create("a", Locale.US, ImageResolution.LOW, testLdpi),
            imageResourceFactory.create("a", Locale.US, ImageResolution.MEDIUM, testMdpi),
            imageResourceFactory.create("a", Locale.US, ImageResolution.HIGH, testHdpi),
            imageResourceFactory.create("a", Locale.US, ImageResolution.EXTRA_HIGH, testXhdpi),
        ]
        def testCases = [
            [
                getImage: [
                    name: "a",
                    size: new Dimension(31, 31),
                ],
                expected: [
                    name: "a",
                    size: new Dimension(32, 32),
                    resolution: ImageResolution.LOW,
                ]
            ],
            [
                getImage: [
                    name: "a",
                    size: new Dimension(32, 32),
                ],
                expected: [
                    name: "a",
                    size: new Dimension(32, 32),
                    resolution: ImageResolution.LOW,
                ]
            ],
            [
                getImage: [
                    name: "a",
                    size: new Dimension(33, 33),
                ],
                expected: [
                    name: "a",
                    size: new Dimension(64, 64),
                    resolution: ImageResolution.MEDIUM,
                ]
            ],
            [
                getImage: [
                    name: "a",
                    size: new Dimension(64, 64),
                ],
                expected: [
                    name: "a",
                    size: new Dimension(64, 64),
                    resolution: ImageResolution.MEDIUM,
                ]
            ],
            [
                getImage: [
                    name: "a",
                    size: new Dimension(65, 65),
                ],
                expected: [
                    name: "a",
                    size: new Dimension(128, 128),
                    resolution: ImageResolution.HIGH,
                ]
            ],
            [
                getImage: [
                    name: "a",
                    size: new Dimension(257, 257),
                ],
                expected: [
                    name: "a",
                    size: new Dimension(256, 256),
                    resolution: ImageResolution.EXTRA_HIGH,
                ]
            ],
        ]
        keyImages.each { map.putImage it }
        testCases.eachWithIndex { testCase, int k ->
            log.info "{} case: {}", k, testCase
            def image = map.getImage testCase.getImage.name, testCase.getImage.size
            assert image.getName() == testCase.expected.name
            assert image.getSizePx() == testCase.expected.size
            assert image.getResolution() == testCase.expected.resolution
        }
    }

    static Injector injector

    static ImagesMapFactory factory

    static ImageResourceFactory imageResourceFactory

    static URL testXhdpi = ImagesCachedSingleMapTest.class.getResource("/com/anrisoftware/resources/images/resources/images/xhdpi/test.png")

    static URL testHdpi = ImagesCachedSingleMapTest.class.getResource("/com/anrisoftware/resources/images/resources/images/hdpi/test.png")

    static URL testMdpi = ImagesCachedSingleMapTest.class.getResource("/com/anrisoftware/resources/images/resources/images/mdpi/test.png")

    static URL testLdpi = ImagesCachedSingleMapTest.class.getResource("/com/anrisoftware/resources/images/resources/images/ldpi/test.png")

    @BeforeClass
    static void createFactory() {
        toStringStyle
        this.injector = createInjector(
                new ResourcesImagesCachedSingleMapModule(),
                new ImageResourceModule())
        this.factory = injector.getInstance ImagesMapFactory
        this.imageResourceFactory = injector.getInstance ImageResourceFactory
    }
}
