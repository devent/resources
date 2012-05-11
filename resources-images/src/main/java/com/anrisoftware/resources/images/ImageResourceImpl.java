package com.anrisoftware.resources.images;

import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;

import java.awt.Image;
import java.awt.MediaTracker;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.lang.builder.ToStringBuilder;

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

	private final ImageResolution resolution;

	private URL url;

	private Image image;

	private ImageIcon imageLoader;

	private Integer height;

	private Integer width;

	@AssistedInject
	ImageResourceImpl(@Assisted URL url, @Assisted ImageResolution resolution,
			ImageResourceImplLogger logger) {
		this.log = logger;
		this.url = url;
		this.resolution = resolution;
		this.height = HEIGHT_WIDTH_NOT_SET;
		this.width = HEIGHT_WIDTH_NOT_SET;
	}

	@AssistedInject
	ImageResourceImpl(@Assisted Image image,
			@Assisted ImageResolution resolution, ImageResourceImplLogger logger) {
		this.log = logger;
		this.image = image;
		this.resolution = resolution;
		this.height = HEIGHT_WIDTH_NOT_SET;
		this.width = HEIGHT_WIDTH_NOT_SET;
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
		synchronized (height) {
			if (height == HEIGHT_WIDTH_NOT_SET) {
				height = determineHeight();
			}
			return height;
		}
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
		synchronized (width) {
			if (width == HEIGHT_WIDTH_NOT_SET) {
				width = determineWidth();
			}
			return width;
		}
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
	public URL getURL() {
		return url;
	}

	@Override
	public ImageResolution getResolution() {
		return resolution;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(url).append(image).toString();
	}

}
