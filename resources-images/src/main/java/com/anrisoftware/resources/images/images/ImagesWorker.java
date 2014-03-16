/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-images.
 *
 * resources-images is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-images is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-images. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.images.images;

import static java.lang.String.format;

import java.awt.Dimension;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.anrisoftware.resources.GetBundle;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.images.api.BundlesMap;
import com.anrisoftware.resources.images.api.ImageResolution;
import com.anrisoftware.resources.images.api.ImageResource;
import com.anrisoftware.resources.images.api.ImageResourceFactory;
import com.anrisoftware.resources.images.api.ImageScalingWorkerFactory;
import com.anrisoftware.resources.images.api.ImagesMap;
import com.google.inject.assistedinject.Assisted;

/**
 * Returns the image resource with the desired name, locale, resolution and
 * size. It will scale the image if necessary and add the scaled image resource
 * to the images map.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
class ImagesWorker {

	private final ImagesWorkerLogger log;

	private final ImageResourceFactory imageResourceFactory;

	private final ImageScalingWorkerFactory scalingWorkerFactory;

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
			@Assisted GetBundle getBundle, @Assisted BundlesMap bundles) {
		this.log = logger;
		this.imageResourceFactory = imageResourceFactory;
		this.scalingWorkerFactory = scalingWorkerFactory;
		this.getBundle = getBundle;
		this.bundles = bundles;
	}

	/**
	 * Returns the image resource with the desired name, locale and size. It
	 * will choose the best available resolution for scaling.
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
	 * @return a {@link ImageResource}.
	 */
	public ImageResource imageResource(String name, Locale locale,
			Dimension size) {
		ResourceBundle bundle = getBundle.bundleFor(locale);
		ImagesMap map = bundles.getImages(bundle);
		log.loadedResourceBundle(name, bundle);
		lazyLoadImagesForAvailableResolutions(name, locale, map, bundle);
		log.checkImageLoaded(map.haveImage(name), name, locale, bundle);
		ImageResource image = map.getImage(name, size);
		ImageResolution resolution = image.getResolution();
		image = resizeIfNeeded(name, locale, size, map, resolution, image,
				bundle);
		return image;
	}

	private void lazyLoadImagesForAvailableResolutions(String name,
			Locale locale, ImagesMap map, ResourceBundle bundle)
			throws ResourcesException {
		for (ImageResolution resolution : ImageResolution.values()) {
			lazyLoadImagesForResolution(name, locale, map, bundle, resolution);
		}
	}

	/**
	 * Returns the image resource with the desired name, locale and size. It
	 * will use the specified resolution for scaling.
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
	 * @param resolution
	 *            the specified {@link ImageResolution}.
	 * 
	 * @return a {@link ImageResource}.
	 */
	public ImageResource imageResource(String name, Locale locale,
			Dimension size, ImageResolution resolution) {
		ResourceBundle bundle = getBundle.bundleFor(locale);
		ImagesMap map = bundles.getImages(bundle);
		log.loadedResourceBundle(name, bundle);
		lazyLoadImagesForResolution(name, locale, map, bundle, resolution);
		log.checkImageLoaded(map.haveImage(name), name, locale, bundle);
		ImageResource image = map.getImage(name, size, resolution);
		image = resizeIfNeeded(name, locale, size, map, resolution, image,
				bundle);
		return image;
	}

	private void lazyLoadImagesForResolution(String name, Locale locale,
			ImagesMap map, ResourceBundle bundle, ImageResolution resolution)
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

	private ImageResource resizeIfNeeded(String name, Locale locale,
			Dimension size, ImagesMap map, ImageResolution resolution,
			ImageResource res, ResourceBundle bundle) {
		if (res.getSizePx().equals(size)) {
			return res;
		}
		Image image = resizeImage(name, size, res.getImage(), locale, bundle);
		res = imageResourceFactory.create(name, locale, resolution, image);
		map.putImage(res);
		log.resizedImage(res);
		return res;
	}

	private Image resizeImage(String name, Dimension size, Image image,
			Locale locale, ResourceBundle bundle) throws ResourcesException {
		try {
			return scalingWorkerFactory.create(image, size).call();
		} catch (Exception e) {
			throw log.errorResizeImage(e, name, locale, bundle);
		}
	}

}
