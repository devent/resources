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
		def images = factory.create(baseName)

		def image = images.getResource name, locale, size, resolution
		assert image.name == name
		assert image.locale == locale
		assert image.resolution == resolution
		assert image.image != null
		assert image.URL.toString() =~ "com/anrisoftware/resources/images/logos/de/ldpi/x-mail-distribution-list.png"
		assert image.size == size

		new ShowImagesFrame(images: image.image, timeout: timeout)()
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
		assert image.size == size

		new ShowImagesFrame(images: image.image, timeout: timeout)()
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
		assert image.size == size

		new ShowImagesFrame(images: image.image, timeout: timeout)()
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

		new ShowImagesFrame(images: res.inject([], { list, value ->
			list << value.image
		}), timeout: 5 * 1000)()
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

		new ShowImagesFrame(images: res.inject([], { list, value ->
			list << value.image
		}), timeout: timeout)()
	}

	def assertImage(def resource, def image, def width, def height, def url=/.*/) {
		assert resource.image != image
		assert resource?.URL?.toString() =~ url
		assert resource.widthPx == width
		assert resource.heightPx == height
	}
}
