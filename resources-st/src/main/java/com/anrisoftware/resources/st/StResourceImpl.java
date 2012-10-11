package com.anrisoftware.resources.st;

import java.io.Serializable;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;

import javax.inject.Inject;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.api.TemplateResource;
import com.anrisoftware.resources.st.worker.STTemplateWorkerFactory;
import com.anrisoftware.resources.st.worker.TemplateWorker;
import com.google.inject.assistedinject.Assisted;

@SuppressWarnings("serial")
class StResourceImpl implements TemplateResource, Serializable {

	private StResourceImplLogger log;

	private String text;

	private TemplateWorker worker;

	private String name;

	private Locale locale;

	/**
	 * For serialization.
	 */
	@Deprecated
	public StResourceImpl() {
	}

	@Inject
	StResourceImpl(StResourceImplLogger logger,
			STTemplateWorkerFactory workerFactory, @Assisted String name,
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
