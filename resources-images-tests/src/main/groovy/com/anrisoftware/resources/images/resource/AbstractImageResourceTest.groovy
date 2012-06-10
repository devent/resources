package com.anrisoftware.resources.images.resource

import static com.anrisoftware.resources.api.ImageResolution.*

import java.awt.Dimension

import org.junit.Before

import com.anrisoftware.globalpom.utils.ShowImagesFrame
import com.anrisoftware.resources.api.ImageResource
import com.anrisoftware.resources.api.ImageResourceFactory
import com.anrisoftware.resources.images.AbstractImageResourcesTestUtils
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

	ImageResourceFactory factory

	@Before
	void before() {
		super.before()
		factory = injector.getInstance(ImageResourceFactory)
	}

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
