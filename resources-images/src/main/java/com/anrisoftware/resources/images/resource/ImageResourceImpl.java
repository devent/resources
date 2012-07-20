package com.anrisoftware.resources.images.resource;

import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.MediaTracker;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.resources.api.ImageResolution;
import com.anrisoftware.resources.api.ImageResource;
import com.anrisoftware.resources.api.ResourcesException;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Image resource with lazy loading. Two image resources are equals if the
 * resource URL is the same.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ImageResourceImpl implements ImageResource {

	private static final int HEIGHT_WIDTH_NOT_SET = -1;

	private final ImageResourceImplLogger log;

	private final String name;

	private final Locale locale;

	private final Dimension size;

	private final ImageResolution resolution;

	private final URL url;

	private Image image;

	private ImageIcon imageLoader;

	@AssistedInject
	ImageResourceImpl(ImageResourceImplLogger logger, @Assisted String name,
			@Assisted Locale locale, @Assisted ImageResolution resolution,
			@Assisted URL url) {
		this(logger, name, locale, resolution, url, null);
	}

	@AssistedInject
	ImageResourceImpl(ImageResourceImplLogger logger, @Assisted String name,
			@Assisted Locale locale, @Assisted ImageResolution resolution,
			@Assisted Image image) {
		this(logger, name, locale, resolution, null, image);
	}

	private ImageResourceImpl(ImageResourceImplLogger logger, String name,
			Locale locale, ImageResolution resolution, URL url, Image image) {
		this.log = logger;
		this.name = name;
		this.locale = locale;
		this.resolution = resolution;
		this.url = url;
		this.image = image;
		this.size = new Dimension(HEIGHT_WIDTH_NOT_SET, HEIGHT_WIDTH_NOT_SET);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

	@Override
	public URL getURL() {
		return url;
	}

	@Override
	public ImageResolution getResolution() {
		return resolution;
	}

	@Override
	public Image getImage() throws ResourcesException {
		if (image == null) {
			image = loadImage();
		}
		return getLoadedImage(image);
	}

	private Image loadImage() throws ResourcesException {
		try {
			return ImageIO.read(url);
		} catch (IOException e) {
			throw log.errorLoadingImage(this, e);
		}
	}

	private Image getLoadedImage(Image image) {
		if (imageLoader == null || !isImageLoaded()) {
			imageLoader = waitUntilImageIsLoaded(image);
		}
		return imageLoader.getImage();
	}

	private ImageIcon waitUntilImageIsLoaded(Image image) {
		if (imageLoader == null) {
			imageLoader = new ImageIcon(image);
		}
		log.imageIsLoaded(this, isImageLoaded());
		while (!isImageLoaded()) {
			Thread.yield();
		}
		return imageLoader;
	}

	private boolean isImageLoaded() {
		return imageLoader.getImageLoadStatus() != MediaTracker.LOADING
				|| imageLoader.getImageLoadStatus() != MediaTracker.COMPLETE;
	}

	@Override
	public int getHeight() throws ResourcesException {
		if (size.height == HEIGHT_WIDTH_NOT_SET) {
			synchronized (getImage()) {
				size.height = determineHeight();
			}
		}
		return size.height;
	}

	private Integer determineHeight() throws ResourcesException {
		ImageResourceObserver observer = new ImageResourceObserver(HEIGHT);
		int height = getImage().getHeight(observer);
		height = waitForHeight(observer, height);
		return height;
	}

	private int waitForHeight(ImageResourceObserver observer, int height) {
		if (height < 0) {
			log.waitForHeight(this);
			while (!observer.isDone()) {
				Thread.yield();
			}
			height = observer.getHeight();
		}
		return height;
	}

	@Override
	public int getWidth() throws ResourcesException {
		if (size.width == HEIGHT_WIDTH_NOT_SET) {
			synchronized (getImage()) {
				size.width = determineWidth();
			}
		}
		return size.width;
	}

	private int determineWidth() throws ResourcesException {
		ImageResourceObserver observer = new ImageResourceObserver(WIDTH);
		int width = getImage().getWidth(observer);
		width = waitForWidth(observer, width);
		return width;
	}

	private int waitForWidth(ImageResourceObserver observer, int width) {
		if (width < 0) {
			log.waitForWidth(this);
			while (!observer.isDone()) {
				Thread.yield();
			}
			width = observer.getWidth();
		}
		return width;
	}

	@Override
	public Dimension getSize() {
		if (size.height == HEIGHT_WIDTH_NOT_SET) {
			getHeight();
		}
		if (size.width == HEIGHT_WIDTH_NOT_SET) {
			getWidth();
		}
		return size;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(name).append(locale)
				.append(resolution).toString();
	}

}
