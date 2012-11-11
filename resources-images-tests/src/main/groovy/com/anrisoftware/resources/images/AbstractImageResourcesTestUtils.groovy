package com.anrisoftware.resources.images

import static com.anrisoftware.globalpom.utils.TestUtils.*

import java.awt.Dimension

import org.junit.Before
import org.junit.BeforeClass

import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Create the images factory to test the image resources.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractImageResourcesTestUtils {

	/**
	 * Timeout to show the images in milliseconds.
	 */
	static timeout = 5 * 1000

	def modules

	Injector injector

	def factory

	def imageResourceFactory

	@BeforeClass
	static void setupToStringStyle() {
		toStringStyle
	}

	@Before
	void createFactories() {
		injector = createInjector()
		factory = createFactory()
		imageResourceFactory = createImageResourceFactory()
	}

	Injector createInjector() {
		if (injector != null) {
			injector
		}
		Guice.createInjector(imagesModule, mapModule, scalingWorkerModule)
	}

	/**
	 * Create the images resources factory.
	 *
	 * @since 1.2
	 */
	abstract createFactory()

	/**
	 * Create the images map factory.
	 *
	 * @since 1.2
	 */
	abstract createImagesMapFactory()

	/**
	 * Create the image resorce factory.
	 *
	 * @since 1.2
	 */
	abstract createImageResourceFactory()

	/**
	 * Returns the images module.
	 */
	abstract getImagesModule()

	/**
	 * Returns the images maps module.
	 */
	abstract getMapModule()

	/**
	 * Returns the image scaling worker module.
	 */
	abstract getScalingWorkerModule()

	/**
	 * Returns the low resolution of the image resource.
	 *
	 * @since 1.2
	 */
	abstract getLow()

	/**
	 * Returns the medium resolution of the image resource.
	 *
	 * @since 1.2
	 */
	abstract getMedium()

	/**
	 * Returns the high resolution of the image resource.
	 *
	 * @since 1.2
	 */
	abstract getHigh()

	/**
	 * Returns the low resolution of the image resource.
	 *
	 * @since 1.2
	 */
	abstract getExtraHigh()

	/**
	 * Converts the map to an image resource.
	 *
	 * @since 1.2
	 */
	abstract toImageResource(def map)

	/**
	 * Creates a stub image resource.
	 *
	 * @param image
	 * 			the parameter of the image resource as a map.
	 *
	 * @return the image resource.
	 *
	 * @since 1.2
	 */
	def createImage(def image) {
		toImageResource([
			getName: { image.name },
			getLocale: { image.locale },
			getURL: { image.url },
			getResolution: { image.resolution },
			getImage: { image.image },
			getWidthPx: { image.width },
			getHeightPx: { image.height },
			getSizePx: { new Dimension(image.width, image.height) },
			toString: { "${image.name}: ${image.image}".toString() },
		])
	}
}
