package com.anrisoftware.resources.images;

import static java.lang.String.format;

import java.awt.Dimension;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.anrisoftware.resources.GetBundle;
import com.anrisoftware.resources.api.ImageResolution;
import com.anrisoftware.resources.api.ImageResource;
import com.anrisoftware.resources.api.ImageResourceFactory;
import com.anrisoftware.resources.api.ImageScalingWorkerFactory;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.images.api.BundlesMap;
import com.anrisoftware.resources.images.api.ImagesMap;
import com.google.inject.assistedinject.Assisted;

/**
 * Returns the image resource with the desired name, locale, resolution and
 * size. It will scale the image if necessary and add the scaled image resource
 * the the images map.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
class ImagesWorker {

	private final ImagesWorkerLogger log;

	private final ImageResourceFactory imageResourceFactory;

	private final ImageScalingWorkerFactory scalingWorkerFactory;

	private final String name;

	private final Locale locale;

	private final Dimension size;

	private final GetBundle getBundle;

	private final BundlesMap bundles;

	/**
	 * Injects the dependencies.
	 * 
	 * @param logger
	 *            the {@link ImagesWorkerLogger} where the logging messages are
	 *            logged.
	 * 
	 * @param imageResourceFactory
	 *            the {@link ImageResourceFactory} to create a new image
	 *            resource.
	 * 
	 * @param scalingWorkerFactory
	 *            the {@link ImageScalingWorkerFactory} to create a new worker
	 *            that will scale the image.
	 * 
	 * @param name
	 *            the {@link String} name of the image resource we want to get.
	 * 
	 * @param locale
	 *            the {@link Locale} of the image resource we want to get.
	 * 
	 * @param size
	 *            the {@link Dimension} width and height of the image resource
	 *            we want to get.
	 * 
	 * @param getBundle
	 *            the {@link GetBundle} that returns the resource bundle for the
	 *            locale.
	 * 
	 * @param bundles
	 *            the map of bundles with their images maps.
	 */
	@Inject
	ImagesWorker(ImagesWorkerLogger logger,
			ImageResourceFactory imageResourceFactory,
			ImageScalingWorkerFactory scalingWorkerFactory,
			@Assisted String name, @Assisted Locale locale,
			@Assisted Dimension size, @Assisted GetBundle getBundle,
			@Assisted BundlesMap bundles) {
		this.log = logger;
		this.imageResourceFactory = imageResourceFactory;
		this.scalingWorkerFactory = scalingWorkerFactory;
		this.name = name;
		this.locale = locale;
		this.size = size;
		this.getBundle = getBundle;
		this.bundles = bundles;
	}

	/**
	 * Returns the image resource with the desired name, locale and size. It
	 * will choose the best available resolution for scaling.
	 * 
	 * @return a {@link ImageResource}.
	 */
	public ImageResource imageResource() {
		ResourceBundle bundle = getBundle.bundleFor(locale);
		ImagesMap map = bundles.getImages(bundle);
		log.loadedResourceBundle(name, bundle);
		lazyLoadImagesForAvailableResolutions(map, bundle);
		log.checkImageLoaded(map.haveImage(name), name);
		ImageResource image = map.getImage(name, size);
		image = resizeIfNeeded(map, image.getResolution(), image);
		return image;
	}

	private void lazyLoadImagesForAvailableResolutions(ImagesMap map,
			ResourceBundle bundle) throws ResourcesException {
		for (ImageResolution resolution : ImageResolution.values()) {
			lazyLoadImagesForResolution(map, bundle, resolution);
		}
	}

	/**
	 * Returns the image resource with the desired name, locale and size. It
	 * will use the specified resolution for scaling.
	 * 
	 * @param resolution
	 *            the specified {@link ImageResolution}.
	 * 
	 * @return a {@link ImageResource}.
	 */
	public ImageResource imageResource(ImageResolution resolution) {
		ResourceBundle bundle = getBundle.bundleFor(locale);
		ImagesMap map = bundles.getImages(bundle);
		log.loadedResourceBundle(name, bundle);
		lazyLoadImagesForResolution(map, bundle, resolution);
		log.checkImageLoaded(map.haveImage(name), name);
		ImageResource image = map.getImage(name, size, resolution);
		image = resizeIfNeeded(map, resolution, image);
		return image;
	}

	private void lazyLoadImagesForResolution(ImagesMap map,
			ResourceBundle bundle, ImageResolution resolution)
			throws ResourcesException {
		if (map.haveImage(name, resolution)) {
			return;
		}
		String location = bundle.getString(name);
		URL url = createURL(location, resolution);
		ImageResource image;
		if (url != null) {
			image = imageResourceFactory.create(name, locale, resolution, url);
			addImageResource(map, image);
		}
	}

	private URL createURL(String urlPattern, ImageResolution resolution) {
		String urlString = format(urlPattern, resolution.getName());
		return createURL(urlString);
	}

	private URL createURL(String value) {
		try {
			return new URL(value);
		} catch (MalformedURLException e) {
			URL url = ImagesImpl.class.getClassLoader().getResource(value);
			return log.checkResourceURL(url, value);
		}
	}

	private void addImageResource(ImagesMap map, ImageResource image) {
		map.putImage(image);
		log.addedImageResource(image);
	}

	private ImageResource resizeIfNeeded(ImagesMap map,
			ImageResolution resolution, ImageResource res) {
		if (res.getSize().equals(size)) {
			return res;
		}
		Image image = resizeImage(name, res.getImage());
		res = imageResourceFactory.create(name, locale, resolution, image);
		map.putImage(res);
		log.resizedImage(res);
		return res;
	}

	private Image resizeImage(String name, Image image)
			throws ResourcesException {
		try {
			return scalingWorkerFactory.create(image, size).call();
		} catch (Exception e) {
			throw log.errorResizeImage(e, name);
		}
	}

}
