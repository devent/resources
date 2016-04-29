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
package com.anrisoftware.resources.images.internal.images

import static com.anrisoftware.globalpom.utils.TestUtils.*
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import org.apache.sling.testing.mock.osgi.junit.OsgiContext
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.globalpom.utils.imagetesting.ImageTestingModule
import com.anrisoftware.resources.images.AbstractImageResourcesTest
import com.anrisoftware.resources.images.external.ImageResolution
import com.anrisoftware.resources.images.external.ImageResource
import com.anrisoftware.resources.images.external.ImagesService
import com.anrisoftware.resources.images.internal.mapcached.ImagesBundlesMapCachedServiceImpl
import com.anrisoftware.resources.images.internal.mapcached.ImagesMapCachedServiceImpl
import com.anrisoftware.resources.images.internal.mapcachedbounded.ImagesBundlesMapCachedBoundedServiceImpl
import com.anrisoftware.resources.images.internal.mapcachedresolutions.ImagesBundlesMapCachedResolutionsServiceImpl
import com.anrisoftware.resources.images.internal.mapcachedsingle.ImagesBundlesMapCachedSingleServiceImpl
import com.anrisoftware.resources.images.internal.scaling.SmoothImageScalingWorkerServiceImpl
import com.google.inject.Guice
import com.google.inject.Injector

/**
 *
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 2.1
 */
@Slf4j
@CompileStatic
class ImagesResourcesServiceTest extends AbstractImageResourcesTest {

    @Rule
    public final OsgiContext context = new OsgiContext()

    @Test
    void "map cached service"() {
        context.registerInjectActivateService(new ImagesBundlesMapCachedServiceImpl(), null)
        context.registerInjectActivateService(new ImagesMapCachedServiceImpl(), null)
        context.registerInjectActivateService(new SmoothImageScalingWorkerServiceImpl(), null)
        this.service = context.registerInjectActivateService(new ImagesServiceImpl(), null)
        super."load image with no resize and ldpi"()
    }

    @Test
    void "map cached bounded service"() {
        context.registerInjectActivateService(new ImagesBundlesMapCachedBoundedServiceImpl(), null)
        context.registerInjectActivateService(new ImagesMapCachedServiceImpl(), null)
        context.registerInjectActivateService(new SmoothImageScalingWorkerServiceImpl(), null)
        this.service = context.registerInjectActivateService(new ImagesServiceImpl(), null)
        super."load image with no resize and ldpi"()
    }

    @Test
    void "map cached resolutions service"() {
        context.registerInjectActivateService(new ImagesBundlesMapCachedResolutionsServiceImpl(), null)
        context.registerInjectActivateService(new ImagesMapCachedServiceImpl(), null)
        context.registerInjectActivateService(new SmoothImageScalingWorkerServiceImpl(), null)
        this.service = context.registerInjectActivateService(new ImagesServiceImpl(), null)
        super."load image with no resize and ldpi"()
    }

    @Test
    void "map cached single service"() {
        context.registerInjectActivateService(new ImagesBundlesMapCachedSingleServiceImpl(), null)
        context.registerInjectActivateService(new ImagesMapCachedServiceImpl(), null)
        context.registerInjectActivateService(new SmoothImageScalingWorkerServiceImpl(), null)
        this.service = context.registerInjectActivateService(new ImagesServiceImpl(), null)
        super."load image with no resize and ldpi"()
    }

    def createImages(String baseName) {
        service.create baseName
    }

    ImagesService service

    @Before
    void createFactories() {
        injector = createInjector()
    }

    Injector createInjector() {
        Guice.createInjector(
                new ImageTestingModule(), new FrameTestingModule())
    }

    @Override
    def createFactory() {
    }

    @Override
    def createImagesMapFactory() {
    }

    @Override
    def createImageResourceFactory() {
    }

    @Override
    def getImagesModule() {
    }

    @Override
    def getMapModule() {
    }

    @Override
    def getScalingWorkerModule() {
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
