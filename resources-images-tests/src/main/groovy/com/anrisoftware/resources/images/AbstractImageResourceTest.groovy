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
