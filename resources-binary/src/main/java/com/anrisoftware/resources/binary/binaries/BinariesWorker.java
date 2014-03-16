/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-binary.
 *
 * resources-binary is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-binary is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-binary. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.binary.binaries;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.anrisoftware.resources.GetBundle;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.binary.api.BinariesMap;
import com.anrisoftware.resources.binary.api.BinaryResource;
import com.anrisoftware.resources.binary.api.BinaryResourceFactory;
import com.anrisoftware.resources.binary.api.BundlesMap;
import com.google.inject.assistedinject.Assisted;

/**
 * Returns the binary resource with the desired name and locale.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class BinariesWorker {

	private final BinariesWorkerLogger log;

	private final String name;

	private final Locale locale;

	private final GetBundle getBundle;

	private final BundlesMap bundles;

	private final BinaryResourceFactory resourceFactory;

	/**
	 * Injects the dependencies.
	 * 
	 * @param logger
	 *            the {@link BinariesWorkerLogger} where the logging messages
	 *            are logged.
	 * 
	 * @param resourceFactory
	 *            the {@link BinaryResourceFactory} to create a new image
	 *            resource.
	 * 
	 * @param name
	 *            the {@link String} name of the image resource we want to get.
	 * 
	 * @param locale
	 *            the {@link Locale} of the image resource we want to get.
	 * 
	 * @param getBundle
	 *            the {@link GetBundle} that returns the resource bundle for the
	 *            locale.
	 * 
	 * @param bundles
	 *            the map of bundles with their binary resources maps.
	 */
	@Inject
	BinariesWorker(BinariesWorkerLogger logger,
			BinaryResourceFactory resourceFactory, @Assisted String name,
			@Assisted Locale locale, @Assisted GetBundle getBundle,
			@Assisted BundlesMap bundles) {
		this.log = logger;
		this.resourceFactory = resourceFactory;
		this.name = name;
		this.locale = locale;
		this.getBundle = getBundle;
		this.bundles = bundles;
	}

	/**
	 * Returns the binary resource with the desired name and locale.
	 * 
	 * @return a {@link BinaryResource}.
	 */
	public BinaryResource binaryResource() {
		ResourceBundle bundle = getBundle.bundleFor(locale);
		BinariesMap map = bundles.getBinaries(getBundle.getBaseName(), bundle);
		log.loadedResourceBundle(name, bundle);
		lazyLoadBinary(map, bundle);
		log.checkBinaryLoaded(map.haveBinary(name), name, locale, bundle);
		BinaryResource image = map.getBinary(name);
		return image;
	}

	private void lazyLoadBinary(BinariesMap map, ResourceBundle bundle)
			throws ResourcesException {
		if (map.haveBinary(name)) {
			return;
		}
		String location = bundle.getString(name);
		URL url = createURL(location);
		BinaryResource resource;
		if (url != null) {
			resource = resourceFactory.create(name, locale, url);
			addResource(map, resource);
		}
	}

	private URL createURL(String value) {
		try {
			return new URL(value);
		} catch (MalformedURLException e) {
			URL url = BinariesImpl.class.getClassLoader().getResource(value);
			return log.checkResourceURL(url, value);
		}
	}

	private void addResource(BinariesMap map, BinaryResource resource) {
		map.putBinary(resource);
		log.addedBinaryResource(resource);
	}

}
