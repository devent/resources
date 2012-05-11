package com.anrisoftware.resources.images;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Maps.newTreeMap;
import static java.lang.Math.abs;

import java.awt.Dimension;
import java.util.Comparator;
import java.util.Map;

import javax.inject.Inject;

import com.anrisoftware.resources.api.ImageResolution;
import com.anrisoftware.resources.api.ImageResource;

/**
 * Puts {@link ImageResource}s and retrieve them. The images are identified by
 * their name, resolution and size. No duplicates are allowed in the map.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ImagesMap {

	private final ImagesMapLogger log;

	private final Map<String, Map<ImageResolution, Map<Dimension, ImageResource>>> images;

	/**
	 * Creates the images map.
	 */
	@Inject
	ImagesMap(ImagesMapLogger logger) {
		this.log = logger;
		this.images = newHashMap();
	}

	/**
	 * Add new image to the map. It will be only one image with the same name,
	 * the same size and the same resolution in the map.
	 * 
	 * @param image
	 *            the {@link ImageResource}.
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
	 */
	public void putImage(ImageResource image, String name, int width,
			int height, ImageResolution resolution) {
		Map<ImageResolution, Map<Dimension, ImageResource>> resolutions = resolutionsMap(name);
		Map<Dimension, ImageResource> resources = resourcesMap(resolutions,
				resolution);
		Dimension dimension = new Dimension(width, height);
		if (!resources.containsKey(dimension)) {
			resources.put(dimension, image);
		} else {
			log.imageAlreadyInMap(this, image);
		}
	}

	private Map<Dimension, ImageResource> resourcesMap(
			Map<ImageResolution, Map<Dimension, ImageResource>> resolutions,
			ImageResolution resolution) {
		Map<Dimension, ImageResource> resources = resolutions.get(resolution);
		if (resources == null) {
			resources = newTreeMap(new Comparator<Dimension>() {

				@Override
				public int compare(Dimension o1, Dimension o2) {
					return compeateByArea(o1, o2);
				}

				private int compeateByArea(Dimension o1, Dimension o2) {
					int area1 = o1.width * o1.height;
					int area2 = o2.width * o2.height;
					return area1 < area2 ? -1 : area1 > area2 ? 1 : 0;
				}
			});
		}
		resolutions.put(resolution, resources);
		return resources;
	}

	private Map<ImageResolution, Map<Dimension, ImageResource>> resolutionsMap(
			String name) {
		Map<ImageResolution, Map<Dimension, ImageResource>> resolutions = images
				.get(name);
		if (resolutions == null) {
			resolutions = newHashMap();
			images.put(name, resolutions);
		}
		return resolutions;
	}

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
	public ImageResource getImage(String name, int width, int height) {
		Map<ImageResolution, Map<Dimension, ImageResource>> resolutions = resolutionsMap(name);
		Dimension dimension = new Dimension(width, height);
		FindNearest findNearest = new FindNearest(dimension);

		int diff = Integer.MAX_VALUE;
		ImageResource foundImage = null;
		for (Map<Dimension, ImageResource> resolution : resolutions.values()) {
			ImageResource image = resolution.get(dimension);
			if (image != null) {
				return image;
			}
			findNearest.findNearest(resolution);
			int newdiff = findNearest.getDifference();
			if (newdiff < 0 && abs(newdiff) < abs(diff)) {
				foundImage = findNearest.getImage();
				diff = newdiff;
			}
			if (newdiff > 0 && newdiff < diff) {
				foundImage = findNearest.getImage();
				diff = newdiff;
			}
		}
		return foundImage;
	}

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
	public ImageResource getImage(String name, int width, int height,
			ImageResolution resolution) {
		Map<ImageResolution, Map<Dimension, ImageResource>> resolutions = resolutionsMap(name);
		Map<Dimension, ImageResource> resources = resourcesMap(resolutions,
				resolution);
		Dimension dimension = new Dimension(width, height);
		ImageResource image = resources.get(dimension);
		if (image == null) {
			log.noImageReturningNearest(this, name);
			return new FindNearest(dimension).findNearest(resources);
		}
		return image;
	}

	/**
	 * Find the nearest {@link ImageResource}.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 1.0
	 */
	private static class FindNearest {

		private final int searchArea;

		private int difference;

		private ImageResource image;

		/**
		 * Set the size what we search for.
		 * 
		 * @param searchSize
		 *            the {@link Dimension} that is the size.
		 */
		public FindNearest(Dimension searchSize) {
			this.searchArea = searchSize.width * searchSize.height;
			this.difference = Integer.MAX_VALUE;
		}

		/**
		 * Find the next nearest image to the give size.
		 * 
		 * @param resources
		 *            the {@link Map} of the {@link ImageResource}s with the
		 *            {@link Dimension} size that are in the map.
		 * 
		 * @return the nearest {@link ImageResource} that is found. If the size
		 *         is smaller then the smallest image in the map, we return the
		 *         smallest image. If the size is larger then the biggest image
		 *         in the map, we return the biggest image.
		 */
		public ImageResource findNearest(Map<Dimension, ImageResource> resources) {
			for (Map.Entry<Dimension, ImageResource> entry : resources
					.entrySet()) {
				int newarea = entry.getKey().width * entry.getKey().height;
				int newdiff = searchArea - newarea;
				if (newdiff < 0 && abs(newdiff) < abs(difference)) {
					image = entry.getValue();
					difference = newdiff;
				}
				if (newdiff > 0 && newdiff < difference) {
					image = entry.getValue();
					difference = newdiff;
				}
			}
			return image;
		}

		/**
		 * Return the found nearest {@link ImageResource} or <code>null</code>
		 * if no image was found.
		 */
		public ImageResource getImage() {
			return image;
		}

		/**
		 * Returns the difference of the area of the found image and the
		 * searched size.
		 */
		public int getDifference() {
			return difference;
		}

	}

	/**
	 * Check if the image with the given name is in the map.
	 * 
	 * @param name
	 *            the name of the image.
	 * 
	 * @return <code>true</code>if the image is in the map.
	 */
	public boolean haveImage(String name) {
		return images.containsKey(name);
	}

	@Override
	public String toString() {
		return images.toString();
	}

}
