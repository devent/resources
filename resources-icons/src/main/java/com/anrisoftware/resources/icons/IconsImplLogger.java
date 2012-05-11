package com.anrisoftware.resources.icons;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.IconSize;
import com.anrisoftware.resources.api.ImageResource;
import com.anrisoftware.resources.api.ResourcesException;

class IconsImplLogger extends AbstractLogger {

	IconsImplLogger() {
		super(IconsImpl.class);
	}

	ResourcesException errorGetResource(ResourcesException e, Object handler) {
		log.warn("Error get the resource in the handler {}: {}.", handler,
				e.getMessage());
		return e;
	}

	ResourcesException errorCreateURL(MalformedURLException e) {
		ResourcesException ex = new ResourcesException(e, "Error create URL");
		log.error(ex.getMessage());
		return ex;
	}

	URL checkResourceURL(URL url, String value) {
		if (url == null) {
			log.warn("The resource URL ``{}'' could not be found.", value);
		}
		return url;
	}

	void addedImageResource(String name, IconSize size, ImageResource image) {
		log.debug("Add new image resouce {} named {} with size {}.",
				new Object[] { image, name, size });
	}

	void checkImageLoaded(Map<IconSize, ImageResource> resources, String name)
			throws ResourcesException {
		if (resources == null) {
			ResourcesException ex = new ResourcesException(
					"No image resource loaded for ``%s''", name);
			log.error(ex.getMessage());
			throw ex;
		}
	}

	void resizedImageTo(int size, String name) {
		log.debug("Resize image resource ``{}'' to size {}.", name, size);
	}

	ResourcesException errorResizeImage(Exception e) {
		ResourcesException ex = new ResourcesException(e, "Error resize image");
		log.error(ex.getMessage());
		return ex;
	}
}