package com.anrisoftware.resources.st;

import java.io.Serializable;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;

import javax.inject.Inject;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang3.ArrayUtils;

import com.anrisoftware.resources.api.BinaryResourceFactory;
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

	private int dataHashCode;

	/**
	 * For serialization.
	 */
	@Deprecated
	public StResourceImpl() {
		dataHashCode = 0;
	}

	@Inject
	StResourceImpl(StResourceImplLogger logger,
			BinaryResourceFactory factory,
			STTemplateWorkerFactory workerFactory, @Assisted String name,
			@Assisted Locale locale, @Assisted URL url,
			@Assisted Properties properties) {
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
	public Properties getProperties() {
		return worker.getProperties();
	}

	@Override
	public String getText(Object... data) throws ResourcesException {
		if (text == null || !isSameData(data)) {
			text = worker.process(name, data);
		}
		return text;
	}

	private boolean isSameData(Object[] data) {
		int hashCode = ArrayUtils.hashCode(data);
		boolean same = dataHashCode == hashCode;
		dataHashCode = hashCode;
		log.dataIsSame(this, same);
		System.out.println(hashCode);
		return same;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("locale", getLocale())
				.append("name", getName()).append("url", getURL()).toString();
	}

}
