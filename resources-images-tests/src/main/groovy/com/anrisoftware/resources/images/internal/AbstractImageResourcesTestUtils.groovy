
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

import static com.anrisoftware.globalpom.utils.TestUtils.*

import java.awt.Dimension

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach

import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.globalpom.utils.imagetesting.ImageTestingModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Create the images factory to test the image resources.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractImageResourcesTestUtils {

    /**
     * Timeout to show the images in milliseconds.
     */
    static timeout = 5 * 1000

    def modules

    Injector injector

    def factory

    def imageResourceFactory

    @BeforeAll
    static void setupToStringStyle() {
        toStringStyle
    }

    @BeforeEach
    void createFactories() {
        injector = createInjector()
        factory = createFactory()
        imageResourceFactory = createImageResourceFactory()
    }

    Injector createInjector() {
        Guice.createInjector(
                new ImageTestingModule(), new FrameTestingModule(),
                imagesModule, mapModule, scalingWorkerModule)
    }

    /**
     * Create the images resources factory.
     *
     * @since 1.2
     */
    abstract createFactory()

    /**
     * Create the images map factory.
     *
     * @since 1.2
     */
    abstract createImagesMapFactory()

    /**
     * Create the image resorce factory.
     *
     * @since 1.2
     */
    abstract createImageResourceFactory()

    /**
     * Returns the images module.
     */
    abstract getImagesModule()

    /**
     * Returns the images maps module.
     */
    abstract getMapModule()

    /**
     * Returns the image scaling worker module.
     */
    abstract getScalingWorkerModule()

    /**
     * Returns the low resolution of the image resource.
     *
     * @since 1.2
     */
    abstract getLow()

    /**
     * Returns the medium resolution of the image resource.
     *
     * @since 1.2
     */
    abstract getMedium()

    /**
     * Returns the high resolution of the image resource.
     *
     * @since 1.2
     */
    abstract getHigh()

    /**
     * Returns the low resolution of the image resource.
     *
     * @since 1.2
     */
    abstract getExtraHigh()

    /**
     * Converts the map to an image resource.
     *
     * @since 1.2
     */
    abstract toImageResource(Map map)

    /**
     * Creates a stub image resource.
     *
     * @param image
     * 			the parameter of the image resource as a map.
     *
     * @return the image resource.
     *
     * @since 1.2
     */
    def createImage(Map image) {
        toImageResource([
            getName: { image.name },
            getLocale: { image.locale },
            getURL: { image.url },
            getResolution: { image.resolution },
            getImage: { image.image },
            getWidthPx: { image.width },
            getHeightPx: { image.height },
            getSizePx: { new Dimension(image.width, image.height) },
            toString: { "${image.name}: ${image.image}".toString() },
        ])
    }
}
