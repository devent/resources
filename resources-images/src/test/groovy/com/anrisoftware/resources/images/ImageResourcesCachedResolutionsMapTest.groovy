/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.images

import org.junit.Test

import com.anrisoftware.resources.images.api.ImageResolution
import com.anrisoftware.resources.images.api.ImageResource
import com.anrisoftware.resources.images.api.ImageResourceFactory
import com.anrisoftware.resources.images.api.ImagesFactory
import com.anrisoftware.resources.images.api.ImagesMapFactory
import com.anrisoftware.resources.images.images.ImagesResourcesModule
import com.anrisoftware.resources.images.mapcachedresolutions.ResourcesImagesCachedResolutionsMapModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule

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
    def toImageResource(def map) {
        map as ImageResource
    }
}