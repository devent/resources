package com.anrisoftware.resources;

import static com.anrisoftware.resources.api.ImageResolution.LOW;
import static java.lang.String.format;

import java.awt.Dimension;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import javax.inject.Named;

import com.anrisoftware.resources.api.IconSize;
import com.anrisoftware.resources.api.Icons;
import com.anrisoftware.resources.api.ImageResource;
import com.anrisoftware.resources.api.ImageResourceFactory;
import com.anrisoftware.resources.api.ImageScalingWorkerFactory;
import com.anrisoftware.resources.api.ResourcesException;
import com.google.common.collect.Maps;
import com.google.inject.Inject;

class IconsImpl implements Icons {

	private final IconsImplLogger log;

	private final Map<String, Map<IconSize, ImageResource>> images;

	private final ImageResourceFactory imageResourceFactory;

	private final Properties imagesProperties;

	private final ImageScalingWorkerFactory imageScalingWorkerFactory;

	@Inject
	IconsImpl(@Named("icons-properties") Properties imagesProperties,
			IconsImplLogger logger, ImageResourceFactory imageResourceFactory,
			ImageScalingWorkerFactory imageScalingWorkerFactory) {
		this.log = logger;
		this.imagesProperties = imagesProperties;
		this.images = Maps.newHashMap();
		this.imageResourceFactory = imageResourceFactory;
		this.imageScalingWorkerFactory = imageScalingWorkerFactory;
	}

	@Override
	public Icons loadResources() throws ResourcesException {
		loadImageResources();
		return this;
	}

	private void loadImageResources() throws ResourcesException {
		for (Map.Entry<Object, Object> entry : imagesProperties.entrySet()) {
			loadImageResourceForDefaultSizes((String) entry.getKey(),
					(String) entry.getValue());
		}
	}

	private void loadImageResourceForDefaultSizes(String name, String urlPattern)
			throws ResourcesException {
		for (IconSize size : IconSize.values()) {
			String urlString = format(urlPattern, size.getSize());
			URL url = createURL(urlString);
			if (url == null) {
				continue;
			}
			ImageResource image = imageResourceFactory.create(url, LOW);
			addImageResource(name, size, image);
		}
		log.checkImageLoaded(images.get(name), name);
	}

	private void addImageResource(String name, IconSize size,
			ImageResource image) {
		Map<IconSize, ImageResource> resources = images.get(name);
		if (resources == null) {
			images.put(name, new TreeMap<IconSize, ImageResource>());
			resources = images.get(name);
		}
		resources.put(size, image);
		log.addedImageResource(name, size, image);
	}

	private URL createURL(String value) {
		try {
			return new URL(value);
		} catch (MalformedURLException e) {
			URL url = IconsImpl.class.getClassLoader().getResource(value);
			return log.checkResourceURL(url, value);
		}
	}

	@Override
	public ImageResource iconResource(String name, IconSize size)
			throws ResourcesException {
		return resizedImage(name, size);
	}

	private ImageResource resizedImage(String name, IconSize size)
			throws ResourcesException {
		ImageResource image = images.get(name).get(size);
		if (image != null) {
			return image;
		}
		ImageResource nearest = nearestAvailableImage(name, size.getSize());
		Image resizedImage = resizeImage(nearest.getImage(), size.getSize());
		ImageResource resized = imageResourceFactory.create(resizedImage, LOW);
		addImageResource(name, size, resized);
		log.resizedImageTo(size.getSize(), name);
		return resized;
	}

	private Image resizeImage(Image image, int size) throws ResourcesException {
		int width = size;
		int height = size;
		try {
			return imageScalingWorkerFactory.create(image,
					new Dimension(width, height)).call();
		} catch (Exception e) {
			throw log.errorResizeImage(e);
		}
	}

	private ImageResource nearestAvailableImage(String name, int size) {
		IconSize foundSize = nearestAvailableSize(name, size);
		return images.get(name).get(foundSize);
	}

	private IconSize nearestAvailableSize(String name, int size) {
		int difference = Integer.MAX_VALUE;
		IconSize foundSize = null;
		for (IconSize s : images.get(name).keySet()) {
			int newdiff = s.getSize() - size;
			if (newdiff < 0 && Math.abs(newdiff) < difference) {
				foundSize = s;
			}
			if (newdiff > 0 && newdiff < difference) {
				foundSize = s;
				difference = newdiff;
			}
		}
		return foundSize;
	}

}
