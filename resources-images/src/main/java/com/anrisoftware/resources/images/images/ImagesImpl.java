/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-images.
 *
 * resources-images is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-images is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-images. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.images.images;

import java.awt.Dimension;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import com.anrisoftware.resources.GetBundle;
import com.anrisoftware.resources.GetBundleWithClassLoader;
import com.anrisoftware.resources.GetBundleWithClassLoaderAndControl;
import com.anrisoftware.resources.GetBundleWithControl;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.images.api.BundlesMap;
import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.ImageResolution;
import com.anrisoftware.resources.images.api.ImageResource;
import com.anrisoftware.resources.images.api.Images;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Manages the bundles of the image resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ImagesImpl implements Images {

	private final GetBundle getBundle;

	private final ImagesWorker worker;

	@AssistedInject
	ImagesImpl(ImagesWorkerFactory workerFactory, BundlesMap bundles,
			@Assisted String baseName) {
		this(workerFactory, bundles, new GetBundle(baseName));
	}

	@AssistedInject
	ImagesImpl(ImagesWorkerFactory workerFactory, BundlesMap bundles,
			@Assisted String baseName, @Assisted ClassLoader classLoader) {
		this(workerFactory, bundles, new GetBundleWithClassLoader(baseName,
				classLoader));
	}

	@AssistedInject
	ImagesImpl(ImagesWorkerFactory workerFactory, BundlesMap bundles,
			@Assisted String baseName, @Assisted ResourceBundle.Control control) {
		this(workerFactory, bundles,
				new GetBundleWithControl(baseName, control));
	}

	@AssistedInject
	ImagesImpl(ImagesWorkerFactory workerFactory, BundlesMap bundles,
			@Assisted String baseName, @Assisted ClassLoader classLoader,
			@Assisted ResourceBundle.Control control) {
		this(workerFactory, bundles, new GetBundleWithClassLoaderAndControl(
				baseName, classLoader, control));
	}

	private ImagesImpl(ImagesWorkerFactory workerFactory, BundlesMap bundles,
			GetBundle getBundle) {
		this.worker = workerFactory.create(getBundle, bundles);
		this.getBundle = getBundle;
	}

	@Override
	public ClassLoader getClassLoader() {
		return getBundle.getClassLoader();
	}

	@Override
	public String getBaseName() {
		return getBundle.getBaseName();
	}

	@Override
	public Control getControl() {
		return getBundle.getControl();
	}

	@Override
	public ImageResource getResource(String name, Locale locale, int width,
			int height) throws ResourcesException {
		return getResource(name, locale, new Dimension(width, height));
	}

	@Override
	public ImageResource getResource(String name, Locale locale, IconSize size)
			throws ResourcesException {
		return getResource(name, locale,
				new Dimension(size.getSizePx(), size.getSizePx()));
	}

	@Override
	public ImageResource getResource(String name, Locale locale, Dimension size)
			throws ResourcesException {
		return worker.imageResource(name, locale, size);
	}

	@Override
	public ImageResource getResource(String name, Locale locale, int width,
			int height, ImageResolution resolution) throws ResourcesException {
		return getResource(name, locale, new Dimension(width, height),
				resolution);
	}

	@Override
	public ImageResource getResource(String name, Locale locale,
			Dimension size, ImageResolution resolution)
			throws ResourcesException {
		return worker.imageResource(name, locale, size, resolution);
	}
}
