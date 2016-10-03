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
package com.anrisoftware.resources.images

import java.awt.Dimension

import com.anrisoftware.globalpom.utils.imagetesting.ShowImagesFrame
import com.anrisoftware.globalpom.utils.imagetesting.ShowImagesFrame.ShowImagesFrameFactory

/**
 * Defines the image resources tests.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractImageResourcesTest extends AbstractImageResourcesTestUtils {

    static IMAGE_NAME = "logo"

    void "load image with no resize and ldpi"() {
        def name = IMAGE_NAME
        def size = new Dimension(171, 171)
        def resolution = low
        def baseName = "Logos"
        def locale = Locale.GERMAN
        def images = createImages(baseName)

        def image = images.getResource name, locale, size, resolution
        assert image.name == name
        assert image.locale == locale
        assert image.resolution == resolution
        assert image.image != null
        assert image.URL.toString() =~ "com/anrisoftware/resources/images/logos/de/ldpi/x-mail-distribution-list.png"
        assert image.getSizePx() == size

        def frameFactory = injector.getInstance ShowImagesFrameFactory
        def frame = frameFactory.create image: image.image, size: image.size
        frame()
    }

    void "load image with resize and ldpi"() {
        def name = IMAGE_NAME
        def size = new Dimension(256, 256)
        def resolution = low
        def baseName = "Logos"
        def locale = Locale.GERMAN
        def images = factory.create(baseName)

        def image = images.getResource name, locale, size, resolution
        assert image.name == name
        assert image.locale == locale
        assert image.resolution == resolution
        assert image.image != null
        assert image.URL == null
        assert image.getSizePx() == size

        def frameFactory = injector.getInstance ShowImagesFrameFactory
        def frame = frameFactory.create image: image.image, size: image.size
        frame()
    }

    void "load image with resize and xhdpi"() {
        def name = IMAGE_NAME
        def size = new Dimension(128, 128)
        def resolution = extraHigh
        def baseName = "Logos"
        def locale = Locale.GERMAN
        def images = factory.create(baseName)

        def image = images.getResource name, locale, size, resolution
        assert image.name == name
        assert image.locale == locale
        assert image.resolution == resolution
        assert image.image != null
        assert image.URL == null
        assert image.getSizePx() == size

        def frameFactory = injector.getInstance ShowImagesFrameFactory
        def frame = frameFactory.create image: image.image, size: image.size
        frame()
    }

    void "same image with different sizes"() {
        def baseName = "Logos"
        def locale = Locale.GERMAN
        def images = factory.create(baseName)
        def res = []
        res << images.getResource(IMAGE_NAME, locale, 256, 256, extraHigh)
        res << images.getResource(IMAGE_NAME, locale, 230, 230, extraHigh)
        res << images.getResource(IMAGE_NAME, locale, 260, 260, extraHigh)
        res << images.getResource(IMAGE_NAME, locale, 455, 455, extraHigh)

        assertImage res[0], null, 256, 256
        assertImage res[1], null, 230, 230
        assertImage res[2], null, 260, 260
        assertImage res[3], null, 455, 455, "com/anrisoftware/resources/images/logos/de/xhdpi/x-mail-distribution-list.png"

        def frameFactory = injector.getInstance ShowImagesFrameFactory
        def frame = frameFactory.create images: res.inject([], { list, value ->
            list << value.image
        })
        frame()
    }

    void "same image with different sizes and auto-resolution"() {
        def baseName = "Logos"
        def locale = Locale.GERMAN
        def images = factory.create(baseName)
        def res = []
        res << images.getResource(IMAGE_NAME, locale, 50, 50)
        res << images.getResource(IMAGE_NAME, locale, 171, 171)
        res << images.getResource(IMAGE_NAME, locale, 191, 191)
        res << images.getResource(IMAGE_NAME, locale, 200, 200)
        res << images.getResource(IMAGE_NAME, locale, 228, 228)
        res << images.getResource(IMAGE_NAME, locale, 300, 300)
        res << images.getResource(IMAGE_NAME, locale, 341, 341)
        res << images.getResource(IMAGE_NAME, locale, 400, 400)
        res << images.getResource(IMAGE_NAME, locale, 455, 455)
        res << images.getResource(IMAGE_NAME, locale, 600, 600)

        assertImage res[0], null, 50, 50
        assertImage res[1], null, 171, 171, "com/anrisoftware/resources/images/logos/de/ldpi/x-mail-distribution-list.png"
        assertImage res[2], null, 191, 191
        assertImage res[3], null, 200, 200
        assertImage res[4], null, 228, 228, "com/anrisoftware/resources/images/logos/de/mdpi/x-mail-distribution-list.png"
        assertImage res[5], null, 300, 300
        assertImage res[6], null, 341, 341, "com/anrisoftware/resources/images/logos/de/hdpi/x-mail-distribution-list.png"
        assertImage res[7], null, 400, 400
        assertImage res[8], null, 455, 455, "com/anrisoftware/resources/images/logos/de/xhdpi/x-mail-distribution-list.png"
        assertImage res[9], null, 600, 600

        def frameFactory = injector.getInstance ShowImagesFrameFactory
        def frame = frameFactory.create images: res.inject([], { list, value ->
            list << value.image
        })
        frame()
    }

    /**
     * Creates the images resources.
     *
     * @since 2.1
     */
    def createImages(String baseName) {
        factory.create(baseName)
    }

    def assertImage(def resource, def image, def width, def height, def url=null) {
        assert resource.image != image
        if (resource.URL != null && url != null) {
            assert resource.URL.toString() =~ url
        }
        assert resource.getWidthPx() == width
        assert resource.getWidthPx() == height
    }
}
