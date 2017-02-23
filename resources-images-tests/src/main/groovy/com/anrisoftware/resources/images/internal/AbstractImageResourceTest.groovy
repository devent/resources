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

import com.anrisoftware.globalpom.utils.imagetesting.ShowImagesFrame
import com.anrisoftware.globalpom.utils.imagetesting.ShowImagesFrame.ShowImagesFrameFactory

/**
 * Create tests to test the image resource.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractImageResourceTest extends AbstractImageResourcesTestUtils {

    public static imageLowURL = AbstractImageResourceTest.class.getResource("/com/anrisoftware/resources/images/logos/xx/ldpi/x-mail-distribution-list.png")

    static IMAGE_NAME = "logo"

    void "load image with no resize and ldpi"() {
        def name = IMAGE_NAME
        def locale = Locale.ENGLISH
        def width = 171
        def height = 171
        def image = createImageResource name, locale, low, imageLowURL
        assert image.name == name
        assert image.locale == locale
        assert image.URL == imageLowURL
        assert image.image != null
        assert image.widthPx == width
        assert image.heightPx == height
        assert image.size == new Dimension(width, height)

        def frameFactory = injector.getInstance ShowImagesFrameFactory
        def frame = frameFactory.create image: image.image, size: image.size
        frame()
    }

    def createImageResource(def name, def locale, def resolution, def resource) {
        imageResourceFactory.create name, locale, resolution, resource
    }
}
