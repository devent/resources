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
import com.anrisoftware.resources.images.maps.ResourcesImagesMapsModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule

/**
 * Test the images map.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ImagesMapTest extends AbstractImagesMapTest {

    @Test
    void "put images to map without dublicates"() {
        super."put images to map without dublicates"()
    }

    @Test
    void "put images to map with dublicates"() {
        super."put images to map with dublicates"()
    }

    @Test
    void "return custom sizes of images"() {
        super."return custom sizes of images"()
    }

    @Test
    void "return custom sizes of images with auto resolution"() {
        super."return custom sizes of images with auto resolution"()
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
        new ResourcesImagesMapsModule()
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
