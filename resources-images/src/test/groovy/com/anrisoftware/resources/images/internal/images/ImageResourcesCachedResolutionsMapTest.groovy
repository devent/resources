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
package com.anrisoftware.resources.images.internal.images

import org.junit.Test

import com.anrisoftware.resources.images.internal.AbstractImageResourcesTest
import com.anrisoftware.resources.images.external.ImageResolution
import com.anrisoftware.resources.images.external.ImageResource
import com.anrisoftware.resources.images.external.ImageResourceFactory
import com.anrisoftware.resources.images.external.ImagesFactory
import com.anrisoftware.resources.images.external.ImagesMapFactory
import com.anrisoftware.resources.images.internal.mapcachedresolutions.ResourcesImagesCachedResolutionsMapModule
import com.anrisoftware.resources.images.internal.scaling.ResourcesSmoothScalingModule

/**
 * Test the image resources with a cached resolutions map.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.17
 */
class ImageResourcesCachedResolutionsMapTest extends AbstractImageResourcesTest {

    @Test
    void "load image with no resize and ldpi"() {
        super."load image with no resize and ldpi"()
    }

    @Test
    void "load image with resize and ldpi"() {
        super."load image with resize and ldpi"()
    }

    @Test
    void "load image with resize and xhdpi"() {
        super."load image with resize and xhdpi"()
    }

    @Test
    void "same image with different sizes"() {
        super."same image with different sizes"()
    }

    @Test
    void "same image with different sizes and auto-resolution"() {
        super."same image with different sizes and auto-resolution"()
    }

    @Override
    def createFactory() {
        injector.getInstance(ImagesFactory)
    }

    @Override
    def createImagesMapFactory() {
        injector.getInstance(ImagesMapFactory)
    }

    @Override
    def createImageResourceFactory() {
        injector.getInstance(ImageResourceFactory)
    }

    @Override
    def getImagesModule() {
        new ImagesResourcesModule()
    }

    @Override
    def getMapModule() {
        new ResourcesImagesCachedResolutionsMapModule()
    }

    @Override
    def getScalingWorkerModule() {
        new ResourcesSmoothScalingModule()
    }

    @Override
    def getLow() {
        ImageResolution.LOW
    }

    @Override
    def getMedium() {
        ImageResolution.MEDIUM
    }

    @Override
    def getHigh() {
        ImageResolution.HIGH
    }

    @Override
    def getExtraHigh() {
        ImageResolution.EXTRA_HIGH
    }

    @Override
    def toImageResource(Map map) {
        map as ImageResource
    }
}
