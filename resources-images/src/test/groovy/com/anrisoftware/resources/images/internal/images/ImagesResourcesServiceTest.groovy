/*-
 * #%L
 * Resources :: Image
 * %%
 * Copyright (C) 2012 - 2018 Advanced Natural Research Institute
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
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
package com.anrisoftware.resources.images.internal.images

import static com.anrisoftware.globalpom.utils.TestUtils.*

import org.apache.sling.testing.mock.osgi.junit5.OsgiContext
import org.apache.sling.testing.mock.osgi.junit5.OsgiContextExtension
import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.globalpom.utils.imagetesting.ImageTestingModule
import com.anrisoftware.resources.images.external.ImageResolution
import com.anrisoftware.resources.images.external.ImageResource
import com.anrisoftware.resources.images.external.ImagesService
import com.anrisoftware.resources.images.internal.AbstractImageResourcesTest
import com.anrisoftware.resources.images.internal.mapcached.ImagesBundlesMapCachedServiceImpl
import com.anrisoftware.resources.images.internal.mapcached.ImagesMapCachedServiceImpl
import com.anrisoftware.resources.images.internal.mapcachedbounded.ImagesBundlesMapCachedBoundedServiceImpl
import com.anrisoftware.resources.images.internal.mapcachedresolutions.ImagesBundlesMapCachedResolutionsServiceImpl
import com.anrisoftware.resources.images.internal.mapcachedsingle.ImagesBundlesMapCachedSingleServiceImpl
import com.anrisoftware.resources.images.internal.scaling.SmoothImageScalingWorkerServiceImpl
import com.google.inject.Guice
import com.google.inject.Injector

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

/**
 *
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 2.1
 */
@Slf4j
@CompileStatic
@ExtendWith(OsgiContextExtension.class)
class ImagesResourcesServiceTest extends AbstractImageResourcesTest {

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
