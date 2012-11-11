package com.anrisoftware.resources.images.api;

import java.awt.Dimension;

import com.anrisoftware.resources.api.ResourcesException;

/**
 * Puts image resources and retrieve them. The images are identified by their
 * name, resolution and size. Image resources with the name, resolution and size
 * that are already in the map are not added.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public interface ImagesMap {

	/**
	 * Add new image to the map.
	 * <p>
	 * It will be only one image with the same name, size and resolution in the
	 * map. That means that if an image resource with the same name, size and
	 * resolution is already in the map, the image resource will not be added.
	 * 
	 * @param image
	 *            the {@link ImageResource} that should be added.
	 */
	void putImage(ImageResource image) throws ResourcesException;

	/**
	 * Returns the image from the map. The image with the best resolution and
	 * nearest size will be returned.
	 * 
	 * @param name
	 *            the name of the image.
	 * 
	 * @param sizePx
	 *            the {@link Dimension} with the width and height of the image
	 *            in pixels.
	 * 
	 * @return the {@link ImageResource}, the image with the best resolution and
	 *         the nearest size or <code>null</code> if no such image was found
	 *         in the map.
	 */
	ImageResource getImage(String name, Dimension sizePx);

	/**
	 * Returns the image from the map.
	 * 
	 * @param name
	 *            the name of the image.
	 * 
	 * @param sizePx
	 *            the {@link Dimension} with the width and height of the image
	 *            in pixels.
	 * 
	 * @param resolution
	 *            the {@link ImageResolution} of the image.
	 * 
	 * @return the {@link ImageResource}, the nearest image to the size give or
	 *         <code>null</code> if no such image was found in the map.
	 */
	ImageResource getImage(String name, Dimension sizePx,
			ImageResolution resolution);

	/**
	 * Check if the image with the given name and resolution is in the map.
	 * 
	 * @param name
	 *            the name of the image.
	 * 
	 * @param resolution
	 *            the {@link ImageResolution} of the image.
	 * 
	 * @return <code>true</code> if the image is in the map.
	 */
	boolean haveImage(String name, ImageResolution resolution);

	/**
	 * Check if the image with the given name is in the map.
	 * 
	 * @param name
	 *            the name of the image.
	 * 
	 * @return <code>true</code> if the image is in the map.
	 */
	boolean haveImage(String name);

}
