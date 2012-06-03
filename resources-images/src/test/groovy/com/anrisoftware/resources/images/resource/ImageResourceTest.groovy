package com.anrisoftware.resources.images.resource

import static com.anrisoftware.resources.api.ImageResolution.*

import java.awt.Dimension

import org.junit.Test

import com.anrisoftware.globalpom.utils.ShowImagesFrame
import com.anrisoftware.resources.api.ImageResource
import com.anrisoftware.resources.api.ImageResourceFactory
import com.anrisoftware.resources.api.Images
import com.google.inject.Injector

/**
 * Tests the image resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
class ImageResourceTest extends ImageResourceUtils {

	@Test
	void "load image with no resize and ldpi"() {
		def name = IMAGE_NAME
		def locale = Locale.ENGLISH
		def width = 171
		def height = 171

		ImageResource image = factory.create name, locale, LOW, imageLowURL

		assert image.name == name
		assert image.locale == locale
		assert image.URL == imageLowURL
		assert image.image != null
		assert image.width == width
		assert image.height == height
		assert image.size == new Dimension(width, height)

		new ShowImagesFrame(images: image.image)()
	}
}
