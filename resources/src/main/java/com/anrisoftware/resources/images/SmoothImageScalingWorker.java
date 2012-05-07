package com.anrisoftware.resources.images;

import static java.awt.Image.SCALE_SMOOTH;

import java.awt.Dimension;
import java.awt.Image;

import javax.inject.Inject;

import com.anrisoftware.resources.api.ImageScalingWorker;
import com.google.inject.assistedinject.Assisted;

/**
 * Scales an image to a new size using the {@link Image#SCALE_SMOOTH} method.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class SmoothImageScalingWorker implements ImageScalingWorker {

	private final Dimension size;

	private final Image image;

	@Inject
	SmoothImageScalingWorker(@Assisted Image image, @Assisted Dimension size) {
		this.image = image;
		this.size = size;
	}

	@Override
	public Image call() throws Exception {
		int width = size.width;
		int height = size.height;
		return image.getScaledInstance(width, height, SCALE_SMOOTH);
	}
}
