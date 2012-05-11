package com.anrisoftware.resources.texts

import static com.anrisoftware.resources.api.IconSize.*



import javax.inject.Named

import org.junit.Before
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestUtils;
import com.anrisoftware.resources.ResourcesModule;
import com.anrisoftware.resources.api.ImageResource
import com.anrisoftware.resources.api.ImageScalingWorker
import com.anrisoftware.resources.api.ImageScalingWorkerFactory
import com.anrisoftware.resources.api.TextResource
import com.anrisoftware.resources.api.Texts;
import com.anrisoftware.resources.images.ShowIconFrame;
import com.anrisoftware.resources.images.SmoothImageScalingWorker
import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Provides
import com.google.inject.assistedinject.FactoryModuleBuilder;

class TextResourcesTest extends TestUtils {

	static imageresourcesProperties = resourceURL(TextResourcesTest, "texts.properties")

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
		@Named("texts-properties")
		Properties getTextsProperties() {
			def properties = new Properties()
			properties.load imageresourcesProperties.openStream()
			properties
		}

		@Provides
		@Named("images-properties")
		Properties getImagesProperties() {
		}

		@Provides
		@Named("icons-properties")
		Properties getIconsProperties() {
		}
	}
	)

	Texts resources

	@Before
	void before() {
		resources = injector.getInstance(Texts).loadResources()
	}

	@Test
	void "load plain text"() {
		TextResource text = resources.textResource "hello", Locale.GERMAN
		assertStringContent text.text, "Hallo Welt"
		assert text.language.language == "de"
		assert text.URL.toString() =~ "com/anrisoftware/resources/texts/de/hello.txt"
	}

	@Test
	void "load go-down with size medium, resized from large"() {
		ImageResource image = resources.iconResource "go-down", MEDIUM
		assert image.image != null
		assert image.URL == null
		assert image.width == 22
		assert image.height == 22

		new ShowIconFrame(images: image.image)()
	}

	@Test
	void "load go-down with size huge, resized from large"() {
		ImageResource image = resources.iconResource "go-down", HUGE
		assert image.image != null
		assert image.URL == null
		assert image.width == 48
		assert image.height == 48

		new ShowIconFrame(images: image.image)()
	}
}
