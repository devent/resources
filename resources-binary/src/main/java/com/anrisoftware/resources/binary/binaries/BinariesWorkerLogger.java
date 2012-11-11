package com.anrisoftware.resources.binary.binaries;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.binary.api.BinaryResource;

/**
 * Logging messages for {@link BinariesImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class BinariesWorkerLogger extends AbstractLogger {

	BinariesWorkerLogger() {
		super(BinariesImpl.class);
	}

	void loadedResourceBundle(String name, ResourceBundle bundle) {
		if (log.isDebugEnabled()) {
			log.debug(
					"Loaded the resource bundle {} for the binary resource ``{}''.",
					bundleToString(bundle), name);
		}
	}

	private String bundleToString(ResourceBundle bundle) {
		return new ToStringBuilder(bundle).append("locale", bundle.getLocale())
				.toString();
	}

	void addedBinaryResource(BinaryResource resource) {
		log.debug("Add new binary resouce {}.", resource);
	}

	void checkBinaryLoaded(boolean haveResource, String name, Locale locale)
			throws ResourcesException {
		if (!haveResource) {
			ResourcesException ex = new ResourcesException("", name,
					"No binary resource loaded for ``%s'' for locale %s", name,
					locale);
			log.error(ex.getMessage());
			throw ex;
		}
	}

	URL checkResourceURL(URL url, String value) {
		if (url == null) {
			log.warn("The resource URL ``{}'' could not be found.", value);
		}
		return url;
	}

}
