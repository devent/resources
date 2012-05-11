package com.anrisoftware.resources.images

import static com.anrisoftware.resources.api.ImageResolution.*



import javax.inject.Named

import org.junit.Before
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestUtils;
import com.anrisoftware.resources.ResourcesModule;
import com.anrisoftware.resources.api.ImageResource
import com.anrisoftware.resources.api.ImageScalingWorker
import com.anrisoftware.resources.api.ImageScalingWorkerFactory
import com.anrisoftware.resources.api.Images
import com.anrisoftware.resources.images.SmoothImageScalingWorker
import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Provides
import com.google.inject.assistedinject.FactoryModuleBuilder;

class ImageResourcesTest extends TestUtils {

	static imageresourcesProperties = resourceURL(ImageResourcesTest, "imagesresources.properties")

	static injector = Guice.createInjector(
	new ResourcesModule(),
	new AbstractModule() {
		@Override
		protected void configure() {
			install(new FactoryModuleBuilder().implement(
					ImageScalingWorker.class,
					SmoothImageScalingWorker.class).
					build(ImageScalingWorkerFactory.class));
		}

		@Provides
		@Named("images-properties")
		Properties getImagesProperties() {
			def properties = new Properties()
			properties.load imageresourcesProperties.openStream()
			properties
		}

		@Provides
		@Named("icons-properties")
		Properties getIconsProperties() {
		}
	}
	)

	static final String IMAGE_NAME = "x-mail-distribution-list"

	Images resources

	@Before
	void before() {
		resources = injector.getInstance(Images).loadResources()
	}

	@Test
	void "load image with no resize and ldpi"() {
		ImageResource image = resources.imageResource IMAGE_NAME, 171, 171, LOW
		assert image.image != null
		assert image.URL.toString() =~ "com/anrisoftware/resources/images/ldpi/x-mail-distribution-list.png"
		assert image.width == 171
		assert image.height == 171

		new ShowIconFrame(images: image.image)()
	}

	@Test
	void "load image with resize and ldpi"() {
		ImageResource image = resources.imageResource IMAGE_NAME, 256, 256, LOW
		assert image.image != null
		assert image.URL == null
		assert image.width == 256
		assert image.height == 256

		new ShowIconFrame(images: image.image)()
	}

	@Test
	void "load image with resize and xhdpi"() {
		ImageResource image = resources.imageResource IMAGE_NAME, 256, 256, EXTRA_HIGH
		assert image.image != null
		assert image.URL == null
		assert image.width == 256
		assert image.height == 256

		new ShowIconFrame(images: image.image)()
	}

	@Test
	void "same image with different sizes"() {
		def res = []
		res << resources.imageResource(IMAGE_NAME, 256, 256, EXTRA_HIGH)
		res << resources.imageResource(IMAGE_NAME, 230, 230, EXTRA_HIGH)
		res << resources.imageResource(IMAGE_NAME, 260, 260, EXTRA_HIGH)
		res << resources.imageResource(IMAGE_NAME, 455, 455, EXTRA_HIGH)

		assertImage res[0], null, 256, 256
		assertImage res[1], null, 230, 230
		assertImage res[2], null, 260, 260
		assertImage res[3], null, 455, 455, "com/anrisoftware/resources/images/xhdpi/x-mail-distribution-list.png"

		new ShowIconFrame(images: res.inject([], { list, value ->
			list << value.image
		}))()
	}

	@Test
	void "same image with different sizes and auto-resolution"() {
		def res = []
		res << resources.imageResource(IMAGE_NAME, 50, 50)
		res << resources.imageResource(IMAGE_NAME, 171, 171)
		res << resources.imageResource(IMAGE_NAME, 191, 191)
		res << resources.imageResource(IMAGE_NAME, 200, 200)
		res << resources.imageResource(IMAGE_NAME, 228, 228)
		res << resources.imageResource(IMAGE_NAME, 300, 300)
		res << resources.imageResource(IMAGE_NAME, 341, 341)
		res << resources.imageResource(IMAGE_NAME, 400, 400)
		res << resources.imageResource(IMAGE_NAME, 455, 455)
		res << resources.imageResource(IMAGE_NAME, 600, 600)

		assertImage res[0], null, 50, 50
		assertImage res[1], null, 171, 171, "com/anrisoftware/resources/images/ldpi/x-mail-distribution-list.png"
		assertImage res[2], null, 191, 191
		assertImage res[3], null, 200, 200
		assertImage res[4], null, 228, 228, "com/anrisoftware/resources/images/mdpi/x-mail-distribution-list.png"
		assertImage res[5], null, 300, 300
		assertImage res[6], null, 341, 341, "com/anrisoftware/resources/images/hdpi/x-mail-distribution-list.png"
		assertImage res[7], null, 400, 400
		assertImage res[8], null, 455, 455, "com/anrisoftware/resources/images/xhdpi/x-mail-distribution-list.png"
		assertImage res[9], null, 600, 600

		new ShowIconFrame(images: res.inject([], { list, value ->
			list << value.image
		}))()
	}

	def assertImage(def resource, def image, def width, def height, def url=/.*/) {
		assert resource.image != image
		assert resource?.URL?.toString() =~ url
		assert resource.width == width
		assert resource.height == height
	}
}
