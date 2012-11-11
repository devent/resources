package com.anrisoftware.resources.images.resource;

import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 * Waits until the given image flags is ready from the image.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ImageResourceObserver implements ImageObserver {

	private final int flags;

	private int x;

	private int y;

	private int width;

	private int height;

	private boolean done;

	/**
	 * Sets the flags we observe for.
	 * 
	 * @param flags
	 *            one or a combination of the flags of the image observer.
	 * 
	 * @see ImageObserver
	 */
	public ImageResourceObserver(int flags) {
		this.flags = flags;
		this.done = false;
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y,
			int width, int height) {
		if ((infoflags & flags) == flags) {
			this.done = true;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
		return false;
	}

	/**
	 * Returns <code>true</code> if the given flags is available from the image.
	 */
	public boolean isDone() {
		return done;
	}

	/**
	 * Returns the height of the image, if the {@link ImageObserver#HEIGHT} flag
	 * becomes available.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the width of the image, if the {@link ImageObserver#WIDTH} flag
	 * becomes available.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the X-axis of the pixels, if the {@link ImageObserver#SOMEBITS}
	 * flag becomes available.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the Y-axis of the pixels, if the {@link ImageObserver#SOMEBITS}
	 * flag becomes available.
	 */
	public int getY() {
		return y;
	}
}
