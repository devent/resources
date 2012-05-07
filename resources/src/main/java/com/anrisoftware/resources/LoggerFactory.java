package com.anrisoftware.resources;

import static org.fest.util.Arrays.array;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.anrisoftware.gsmultifractions.gui.log.GuiAbstractLogger;
import com.anrisoftware.resources.api.IconSize;
import com.anrisoftware.resources.api.ImageResource;
import com.anrisoftware.resources.api.ResourcesException;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

interface LoggerFactory {

	Logger create(@Assisted Class<?> clazz);

	class Logger extends GuiAbstractLogger {

		@Inject
		Logger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

		ResourcesException errorGetResource(ResourcesException e, Object handler) {
			log.warn("Error get the resource in the handler {}: {}.", handler,
					e.getMessage());
			return e;
		}

		ResourcesException errorCreateURL(MalformedURLException e) {
			ResourcesException ex = new ResourcesException(e,
					"Error create URL");
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
					array(image, name, size));
		}

		void checkImageLoaded(Map<IconSize, ImageResource> resources,
				String name) throws ResourcesException {
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
			ResourcesException ex = new ResourcesException(e,
					"Error resize image");
			log.error(ex.getMessage());
			return ex;
		}
	}
}
