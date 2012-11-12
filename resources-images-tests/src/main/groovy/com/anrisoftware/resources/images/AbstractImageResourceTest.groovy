/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-images-tests.
 *
 * resources-images-tests is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-images-tests is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-images-tests. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.images

import java.awt.Dimension

import com.anrisoftware.globalpom.utils.ShowImagesFrame
import com.google.common.io.Resources

/**
 * Create tests to test the image resource.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractImageResourceTest extends AbstractImageResourcesTestUtils {

	public static imageLowURL = Resources.getResource("com/anrisoftware/resources/images/logos/xx/ldpi/x-mail-distribution-list.png")

	static IMAGE_NAME = "logo"

	void "load image with no resize and ldpi"() {
		def name = IMAGE_NAME
		def locale = Locale.ENGLISH
		def width = 171
		def height = 171

		def image = imageResourceFactory.create name, locale, low, imageLowURL

		assert image.name == name
		assert image.locale == locale
		assert image.URL == imageLowURL
		assert image.image != null
		assert image.widthPx == width
		assert image.heightPx == height
		assert image.size == new Dimension(width, height)

		new ShowImagesFrame(images: image.image, timeout: timeout)()
	}
}
