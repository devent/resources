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
package com.anrisoftware.resources.api;

import java.awt.Dimension;
import java.util.Locale;
import java.util.ResourceBundle;

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
	 * <p>
	 * Returns the image with the specified name, locale size.
	 * </p>
	 * <p>
	 * If the needed size is not the size of the image, a scaled instance will
	 * be returned. The most nearest available image resolution will be used for
	 * scaling.
	 * </p>
	 * 
	 * @param name
	 *            the name {@link String} of the resource.
	 * 
	 * @param locale
	 *            the {@link Locale} of the resource.
	 * 
	 * @param size
	 *            the needed {@link Dimension} width and height of the image.
	 * 
	 * @throws ResourcesException
	 *             if the resource is not available.
	 * 
	 * @since 1.1
	 */
	ImageResource imageResource(String name, Locale locale, Dimension size)
			throws ResourcesException;

	/**
	 * <p>
	 * Returns the image with the specified name, locale size.
	 * </p>
	 * <p>
	 * If the needed size is not the size of the image, a scaled instance will
	 * be returned. The most nearest available image resolution will be used for
	 * scaling.
	 * </p>
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
	 * @since 1.1
	 */
	ImageResource imageResource(String name, Locale locale, IconSize size)
			throws ResourcesException;

	/**
	 * <p>
	 * Returns the image with the specified name, locale size.
	 * </p>
	 * <p>
	 * If the needed size is not the size of the image, a scaled instance will
	 * be returned. The most nearest available image resolution will be used for
	 * scaling.
	 * </p>
	 * 
	 * @param name
	 *            the name {@link String} of the resource.
	 * 
	 * @param locale
	 *            the {@link Locale} of the resource.
	 * 
	 * @param width
	 *            the needed width of the image.
	 * 
	 * @param height
	 *            the needed height of the image.
	 * 
	 * @param resolution
	 *            the {@link ImageResolution} to use.
	 * 
	 * @throws ResourcesException
	 *             if the resource is not available.
	 * 
	 * @since 1.1
	 */
	ImageResource imageResource(String name, Locale locale, int width,
			int height) throws ResourcesException;

	/**
	 * <p>
	 * Returns the image with the given name, locale, size and resolution.
	 * </p>
	 * <p>
	 * If the needed size is not the size of the image, a scaled instance will
	 * be returned, from the specified resolution.
	 * </p>
	 * 
	 * @param name
	 *            the name {@link String} of the resource.
	 * 
	 * @param locale
	 *            the {@link Locale} of the resource.
	 * 
	 * @param size
	 *            the needed {@link Dimension} width and height of the image.
	 * 
	 * @param resolution
	 *            the {@link ImageResolution} to use.
	 * 
	 * @throws ResourcesException
	 *             if the resource is not available.
	 * 
	 * @since 1.1
	 */
	ImageResource imageResource(String name, Locale locale, Dimension size,
			ImageResolution resolution) throws ResourcesException;

	/**
	 * <p>
	 * Returns the image with the given name, locale, size and resolution.
	 * </p>
	 * <p>
	 * If the needed size is not the size of the image, a scaled instance will
	 * be returned, from the specified resolution.
	 * </p>
	 * 
	 * @param name
	 *            the name {@link String} of the resource.
	 * 
	 * @param locale
	 *            the {@link Locale} of the resource.
	 * 
	 * @param width
	 *            the needed width of the image.
	 * 
	 * @param height
	 *            the needed height of the image.
	 * 
	 * @param resolution
	 *            the {@link ImageResolution} to use.
	 * 
	 * @throws ResourcesException
	 *             if the resource is not available.
	 * 
	 * @since 1.1
	 */
	ImageResource imageResource(String name, Locale locale, int width,
			int height, ImageResolution resolution) throws ResourcesException;

}
