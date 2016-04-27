/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-templates.
 *
 * resources-templates is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-templates is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-templates. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.templates.templates;

import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.resources.external.ResourcesException;
import com.anrisoftware.resources.templates.api.TemplateResource;
import com.anrisoftware.resources.templates.api.TemplateResourceFactory;
import com.anrisoftware.resources.templates.api.TemplateWorker;
import com.anrisoftware.resources.templates.api.TemplateWorkerFactory;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Serializable template resource. Uses a template worker to process the
 * templates.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @see TemplateWorker
 * @since 1.0
 */
@SuppressWarnings("serial")
class TemplateResourceImpl implements TemplateResource, Serializable {

	private static final Map<Serializable, Serializable> EMPTY_ATTRIBUTES = new HashMap<Serializable, Serializable>();

	private TemplateResourceImplLogger log;

	private String text;

	private TemplateWorker worker;

	private String name;

	private Locale locale;

	/**
	 * For serialization.
	 */
	@Deprecated
	public TemplateResourceImpl() {
	}

	/**
	 * @see TemplateResourceFactory#create(String, Locale, URL, Properties)
	 */
	@AssistedInject
	TemplateResourceImpl(TemplateResourceImplLogger logger,
			TemplateWorkerFactory workerFactory, @Assisted String name,
			@Assisted Locale locale, @Assisted URL url,
			@Assisted Properties properties) {
		this(logger, workerFactory, name, locale, url, properties,
				EMPTY_ATTRIBUTES, true);
	}

	/**
	 * @see TemplateResourceFactory#create(String, Locale, URL, Properties, Map)
	 */
	@AssistedInject
	TemplateResourceImpl(TemplateResourceImplLogger logger,
			TemplateWorkerFactory workerFactory, @Assisted String name,
			@Assisted Locale locale, @Assisted URL url,
			@Assisted Properties properties,
			@Assisted Map<Serializable, Serializable> attributes) {
		this(logger, workerFactory, name, locale, url, properties, attributes,
				true);
	}

	private TemplateResourceImpl(TemplateResourceImplLogger logger,
			TemplateWorkerFactory workerFactory, String name, Locale locale,
			URL url, Properties properties,
			Map<Serializable, Serializable> attributes, boolean differentCtor) {
		this.name = name;
		this.locale = locale;
		this.worker = workerFactory.create(url, properties, attributes);
		this.log = logger;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

	@Override
	public URL getURL() {
		return worker.getURL();
	}

	@Override
	public String getText(boolean invalidate, Object... data)
			throws ResourcesException {
		if (invalidate) {
			invalidate();
		}
		return getText(data);
	}

	@Override
	public String getText(Object... data) throws ResourcesException {
		if (text == null) {
			log.processTemplate(this);
			text = worker.process(name, data);
		}
		return text;
	}

	@Override
	public void invalidate() {
		text = null;
		log.resourceInvalidated(this);
	}

	@Override
	public Properties getProperties() {
		return worker.getProperties();
	}

	@Override
	public Map<Serializable, Serializable> getAttributes() {
		return worker.getAttributes();
	}

	@Override
    public <T> T getTemplate() {
        return worker.getTemplate();
    }

    @Override
	public String toString() {
		return new ToStringBuilder(this).append("locale", getLocale())
				.append("name", getName()).append("url", getURL()).toString();
	}

}
