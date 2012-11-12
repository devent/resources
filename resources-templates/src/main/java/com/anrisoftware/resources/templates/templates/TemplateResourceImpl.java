package com.anrisoftware.resources.templates.templates;

import java.io.Serializable;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;

import javax.inject.Inject;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.templates.api.TemplateResource;
import com.anrisoftware.resources.templates.api.TemplateWorker;
import com.anrisoftware.resources.templates.api.TemplateWorkerFactory;
import com.google.inject.assistedinject.Assisted;

/**
 * Serializable template resource. Uses a template worker to process the
 * templates.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @see TemplateWorker
 * @since 1.0
 */
class TemplateResourceImpl implements TemplateResource, Serializable {

	/**
	 * @since 1.0
	 */
	private static final long serialVersionUID = -8844751640256590067L;

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

	@Inject
	TemplateResourceImpl(TemplateResourceImplLogger logger,
			TemplateWorkerFactory workerFactory, @Assisted String name,
			@Assisted Locale locale, @Assisted URL url,
			@Assisted Properties properties) {
		this.name = name;
		this.locale = locale;
		this.worker = workerFactory.create(url, properties);
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
	public String toString() {
		return new ToStringBuilder(this).append("locale", getLocale())
				.append("name", getName()).append("url", getURL()).toString();
	}

}