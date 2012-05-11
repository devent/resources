package com.anrisoftware.resources.icons

import static com.anrisoftware.resources.api.IconSize.*

import javax.inject.Named

import org.junit.Before
import org.junit.Test

import com.anrisoftware.globalpom.utils.ShowImagesFrame
import com.anrisoftware.globalpom.utils.TestUtils;
import com.anrisoftware.resources.api.ImageResource
import com.anrisoftware.resources.api.ImageScalingWorker
import com.anrisoftware.resources.api.ImageScalingWorkerFactory
import com.anrisoftware.resources.api.Icons
import com.anrisoftware.resources.images.ResourcesImagesModule
import com.anrisoftware.resources.images.SmoothImageScalingWorker
import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Provides
import com.google.inject.assistedinject.FactoryModuleBuilder;

class IconResourcesTest extends TestUtils {

	static iconsPropertiesURL = resourceURL(IconResourcesTest, "iconsresources.properties")

	static injector = Guice.createInjector(
	new ResourcesIconsModule(),
	new ResourcesImagesModule(),
	new AbstractModule() {
		@Override
		protected void configure() {
			install(new FactoryModuleBuilder().implement(
					ImageScalingWorker.class,
					SmoothImageScalingWorker.class).
					build(ImageScalingWorkerFactory.class));
		}

		@Provides
		@Named("icons-properties")
		Properties getIconsProperties() {
			def properties = new Properties()
			properties.load iconsPropertiesURL.openStream()
			properties
		}

		@Provides
		@Named("images-properties")
		Properties getImagesProperties() {
		}
	})

	Icons resources

	@Before
	void before() {
		resources = injector.getInstance(Icons).loadResources()
	}

	@Test
	void "load go-down with size large"() {
		ImageResource image = resources.iconResource "go-down", LARGE
		assert image.image != null
		assert image.URL.toString() =~ "com/anrisoftware/resources/icons/oxygen/x32/go-down.png"
		assert image.width == 32
		assert image.height == 32

		new ShowImagesFrame(images: image.image)()
	}

	@Test
	void "load go-down with size medium, resized from large"() {
		ImageResource image = resources.iconResource "go-down", MEDIUM
		assert image.image != null
		assert image.URL == null
		assert image.width == 22
		assert image.height == 22

		new ShowImagesFrame(images: image.image)()
	}

	@Test
	void "load go-down with size huge, resized from large"() {
		ImageResource image = resources.iconResource "go-down", HUGE
		assert image.image != null
		assert image.URL == null
		assert image.width == 48
		assert image.height == 48

		new ShowImagesFrame(images: image.image)()
	}
}
