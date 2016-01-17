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
package com.anrisoftware.resources.images

import static javax.swing.SwingUtilities.invokeAndWait

import java.awt.Dimension

import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel

import org.junit.Test

import com.anrisoftware.globalpom.utils.frametesting.FrameTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.resources.images.api.ImageResolution
import com.anrisoftware.resources.images.api.ImageResource
import com.anrisoftware.resources.images.api.ImageResourceFactory
import com.anrisoftware.resources.images.api.Images
import com.anrisoftware.resources.images.api.ImagesFactory
import com.anrisoftware.resources.images.api.ImagesMapFactory
import com.anrisoftware.resources.images.images.ImagesResourcesModule
import com.anrisoftware.resources.images.mapcachedsingle.ResourcesImagesCachedSingleMapModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule

/**
 * Test the image resources with a cached resolutions map.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.18
 */
class ImageResourcesCachedSingleMapTest extends AbstractImageResourcesTest {

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

    @Test
    void "resize image, same image"() {
        Images images = createFactory().create "Images"
        def title = "${getClass().simpleName}-resize image, same image"
        def label
        def frameTesting = injector.createChildInjector(new FrameTestingModule()).getInstance(FrameTestingFactory).create(
                title: title, size: new Dimension(270, 270), createComponent: { JFrame frame ->
                    label = new JLabel()
                    return label
                })()
        frameTesting.withFixture({
            //.
            invokeAndWait {
                label.setIcon new ImageIcon(images.getResource("test", Locale.US, new Dimension(112, 112)).getImage())
            }
        }, {
            //.
            invokeAndWait {
                label.setIcon new ImageIcon(images.getResource("test", Locale.US, new Dimension(250, 250)).getImage())
            }
        }, {
            //.
            invokeAndWait {
                label.setIcon new ImageIcon(images.getResource("test", Locale.US, new Dimension(112, 112)).getImage())
            }
        })
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
        new ResourcesImagesCachedSingleMapModule()
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
