/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-api.
 *
 * resources-api is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-api is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-api. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.images.api;

import java.awt.Dimension;
import java.util.Locale;
import java.util.ResourceBundle;

import com.anrisoftware.resources.api.ResourcesException;

/**
 * Gives images resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 * @see ImageResource
 */
public interface Images {

	/**
	 * Returns the used resource bundle base name.
	 * 
	 * @return the used base name {@link String}.
	 * 
	 * @since 1.1
	 */
	String getBaseName();

	/**
	 * Returns the used resource bundle class loader.
	 * 
	 * @return the used {@link ClassLoader}.
	 * 
	 * @since 1.1
	 */
	ClassLoader getClassLoader();

	/**
	 * Returns the used resource bundle control.
	 * 
	 * @return the used {@link ResourceBundle.Control}.
	 * 
	 * @since 1.1
	 */
	ResourceBundle.Control getControl();

	/**
	 * Returns the image with the specified name, locale size.
	 * <p>
	 * If the needed size is not the size of the image, a scaled instance will
	 * be returned. The most nearest available image resolution will be used for
	 * scaling.
	 * 
	 * @param name
	 *            the name {@link String} of the resource.
	 * 
	 * @param locale
	 *            the {@link Locale} of the resource.
	 * 
	 * @param sizePx
	 *            the needed {@link Dimension} width and height of the image in
	 *            pixels.
	 * 
	 * @throws ResourcesException
	 *             if the resource is not available.
	 * 
	 * @since 1.2
	 */
	ImageResource getResource(String name, Locale locale, Dimension sizePx)
			throws ResourcesException;

	/**
	 * Returns the image with the specified name, locale size.
	 * <p>
	 * If the needed size is not the size of the image, a scaled instance will
	 * be returned. The most nearest available image resolution will be used for
	 * scaling.
	 * 
	 * @param name
	 *            the name {@link String} of the resource.
	 * 
	 * @param locale
	 *            the {@link Locale} of the resource.
	 * 
	 * @param size
	 *            the needed {@link IconSize} of the image.
	 * 
	 * @throws ResourcesException
	 *             if the resource is not available.
	 * 
	 * @since 1.2
	 */
	ImageResource getResource(String name, Locale locale, IconSize size)
			throws ResourcesException;

	/**
	 * Returns the image with the specified name, locale size.
	 * <p>
	 * If the needed size is not the size of the image, a scaled instance will
	 * be returned. The most nearest available image resolution will be used for
	 * scaling.
	 * 
	 * @param name
	 *            the name {@link String} of the resource.
	 * 
	 * @param locale
	 *            the {@link Locale} of the resource.
	 * 
	 * @param widthPx
	 *            the needed width of the image in pixels.
	 * 
	 * @param heightPx
	 *            the needed height of the image in pixels.
	 * 
	 * @param resolution
	 *            the {@link ImageResolution} to use.
	 * 
	 * @throws ResourcesException
	 *             if the resource is not available.
	 * 
	 * @since 1.2
	 */
	ImageResource getResource(String name, Locale locale, int widthPx,
			int heightPx) throws ResourcesException;

	/**
	 * Returns the image with the given name, locale, size and resolution.
	 * <p>
	 * If the needed size is not the size of the image, a scaled instance will
	 * be returned, from the specified resolution.
	 * 
	 * @param name
	 *            the name {@link String} of the resource.
	 * 
	 * @param locale
	 *            the {@link Locale} of the resource.
	 * 
	 * @param sizePx
	 *            the needed {@link Dimension} width and height of the image in
	 *            pixels.
	 * 
	 * @param resolution
	 *            the {@link ImageResolution} to use.
	 * 
	 * @throws ResourcesException
	 *             if the resource is not available.
	 * 
	 * @since 1.2
	 */
	ImageResource getResource(String name, Locale locale, Dimension sizePx,
			ImageResolution resolution) throws ResourcesException;

	/**
	 * Returns the image with the given name, locale, size and resolution.
	 * <p>
	 * If the needed size is not the size of the image, a scaled instance will
	 * be returned, from the specified resolution.
	 * 
	 * @param name
	 *            the name {@link String} of the resource.
	 * 
	 * @param locale
	 *            the {@link Locale} of the resource.
	 * 
	 * @param widthPx
	 *            the needed width of the image in pixels.
	 * 
	 * @param heightPx
	 *            the needed height of the image in pixels.
	 * 
	 * @param resolution
	 *            the {@link ImageResolution} to use.
	 * 
	 * @throws ResourcesException
	 *             if the resource is not available.
	 * 
	 * @since 1.2
	 */
	ImageResource getResource(String name, Locale locale, int widthPx,
			int heightPx, ImageResolution resolution) throws ResourcesException;

}
