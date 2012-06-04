package com.anrisoftware.resources.images.api;

import com.anrisoftware.resources.api.ImageResolution;
import com.anrisoftware.resources.api.ImageResource;

/**
 * Puts {@link ImageResource}s and retrieve them. The images are identified by
 * their name, resolution and size. Image resources with the name, resolution
 * and size that are already in the map are not added.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public interface ImagesMap {

	/**
	 * <p>
	 * Add new image to the map.
	 * </p>
	 * <p>
	 * It will be only one image with the same name, size and resolution in the
	 * map. That means that if an image resource with the same name, size and
	 * resolution is already in the map, the image resource will not be added.
	 * </p>
	 * 
	 * @param image
	 *            the {@link ImageResource} that should be added.
	 */
	void putImage(ImageResource image);

	/**
	 * Returns the image from the map. The image with the best resolution and
	 * nearest size will be returned.
	 * 
	 * @param name
	 *            the name of the image.
	 * 
	 * @param width
	 *            the width of the image.
	 * 
	 * @param height
	 *            the height of the image.
	 * 
	 * @return the {@link ImageResource}, the image with the best resolution and
	 *         the nearest size or <code>null</code> if no such image was found
	 *         in the map.
	 */
	ImageResource getImage(String name, int width, int height);

	/**
	 * Returns the image from the map.
	 * 
	 * @param name
	 *            the name of the image.
	 * 
	 * @param width
	 *            the width of the image.
	 * 
	 * @param height
	 *            the height of the image.
	 * 
	 * @param resolution
	 *            the {@link ImageResolution} of the image.
	 * 
	 * @return the {@link ImageResource}, the nearest image to the size give or
	 *         <code>null</code> if no such image was found in the map.
	 */
	ImageResource getImage(String name, int width, int height,
			ImageResolution resolution);

	/**
	 * Check if the image with the given name and locale is in the map.
	 * 
	 * @param name
	 *            the name of the image.
	 * 
	 * @return <code>true</code>if the image is in the map.
	 */
	boolean haveImage(String name);

}
