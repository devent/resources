package com.anrisoftware.resources;

import static java.lang.String.format;

import java.awt.Dimension;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import javax.inject.Named;

import com.anrisoftware.resources.api.ImageResolution;
import com.anrisoftware.resources.api.ImageResource;
import com.anrisoftware.resources.api.ImageResourceFactory;
import com.anrisoftware.resources.api.ImageScalingWorkerFactory;
import com.anrisoftware.resources.api.Images;
import com.anrisoftware.resources.api.ResourcesException;
import com.google.inject.Inject;

class ImagesImpl implements Images {

	private final ImagesImplLogger log;

	private final ImagesMap images;

	private final ImageResourceFactory imageResourceFactory;

	private final Properties imagesProperties;

	private final ImageScalingWorkerFactory imageScalingWorkerFactory;

	@Inject
	ImagesImpl(@Named("images-properties") Properties imagesProperties,
			ImagesImplLogger logger, ImagesMap images,
			ImageResourceFactory imageResourceFactory,
			ImageScalingWorkerFactory imageScalingWorkerFactory) {
		this.log = logger;
		this.imagesProperties = imagesProperties;
		this.images = images;
		this.imageResourceFactory = imageResourceFactory;
		this.imageScalingWorkerFactory = imageScalingWorkerFactory;
	}

	@Override
	public Images loadResources() throws ResourcesException {
		loadImageResources();
		return this;
	}

	private void loadImageResources() throws ResourcesException {
		for (Map.Entry<Object, Object> entry : imagesProperties.entrySet()) {
			loadImageResourceForResolutions((String) entry.getKey(),
					(String) entry.getValue());
		}
	}

	private void loadImageResourceForResolutions(String name, String urlPattern)
			throws ResourcesException {
		for (ImageResolution resolution : ImageResolution.values()) {
			String urlString = format(urlPattern, resolution.getName());
			URL url = createURL(urlString);
			if (url == null) {
				continue;
			}
			ImageResource image = imageResourceFactory.create(url, resolution);
			addImageResource(name, resolution, image);
		}
		log.checkImageLoaded(images.haveImage(name), name);
	}

	private URL createURL(String value) {
		try {
			return new URL(value);
		} catch (MalformedURLException e) {
			URL url = ImagesImpl.class.getClassLoader().getResource(value);
			return log.checkResourceURL(url, value);
		}
	}

	private void addImageResource(String name, ImageResolution resolution,
			ImageResource image) throws ResourcesException {
		int width = image.getWidth();
		int height = image.getHeight();
		images.putImage(image, name, width, height, resolution);
		log.addedImageResource(name, resolution, image);
	}

	@Override
	public ImageResource imageResource(String name, int width, int height)
			throws ResourcesException {
		ImageResource res = images.getImage(name, width, height);
		res = resizeIfNeeded(name, width, height, res.getResolution(), res);
		return res;
	}

	@Override
	public ImageResource imageResource(String name, int width, int height,
			ImageResolution resolution) throws ResourcesException {
		ImageResource res = images.getImage(name, width, height, resolution);
		res = resizeIfNeeded(name, width, height, resolution, res);
		return res;
	}

	private ImageResource resizeIfNeeded(String name, int width, int height,
			ImageResolution resolution, ImageResource res)
			throws ResourcesException {
		if (res.getWidth() != width || res.getHeight() != height) {
			Image image = resizeImage(res.getImage(), width, height);
			res = imageResourceFactory.create(image, resolution);
			images.putImage(res, name, width, height, resolution);
			log.addResizedImage(res, name, resolution);
		}
		return res;
	}

	private Image resizeImage(Image image, int width, int height)
			throws ResourcesException {
		try {
			return imageScalingWorkerFactory.create(image,
					new Dimension(width, height)).call();
		} catch (Exception e) {
			throw log.errorResizeImage(e);
		}
	}

}
