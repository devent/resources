package com.anrisoftware.resources.templates.worker;

import static com.anrisoftware.resources.templates.worker.STTemplateWorkerFactory.DELIMITER_START_CHAR_PROPERTY;
import static com.anrisoftware.resources.templates.worker.STTemplateWorkerFactory.DELIMITER_STOP_CHAR_PROPERTY;
import static com.anrisoftware.resources.templates.worker.STTemplateWorkerFactory.ENCODING_PROPERTY;

import java.io.ObjectStreamException;
import java.net.URL;
import java.util.Properties;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STErrorListener;
import org.stringtemplate.v4.STGroupFile;
import org.stringtemplate.v4.compiler.STException;
import org.stringtemplate.v4.misc.STMessage;

import com.anrisoftware.propertiesutils.ContextProperties;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.templates.api.TemplateWorker;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Template worker that is using a <a
 * href=http://www.antlr.org/wiki/display/ST4/StringTemplate+4+Wiki+Home>String
 * Template</a> template engine.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class STTemplateWorker implements TemplateWorker {

	private static final long serialVersionUID = -7497653988923573560L;

	private final STTemplateWorkerLogger log;

	private final ContextProperties properties;

	private final URL templateUrl;

	private transient STGroupFile groupFile;

	private transient ResourcesException error;

	@Inject
	STTemplateWorker(STTemplateWorkerLogger logger, @Assisted URL templateUrl,
			@Assisted Properties properties) throws ResourcesException {
		this.log = logger;
		this.templateUrl = templateUrl;
		this.properties = new ContextProperties(this, properties);
		createGroupFile();
	}

	private Object readResolve() throws ObjectStreamException {
		createGroupFile();
		return this;
	}

	private void createGroupFile() {
		this.groupFile = openGroupFile(templateUrl);
		groupFile.setListener(new STErrorListener() {

			@Override
			public void runTimeError(STMessage msg) {
				error = log.errorProcessTemplate(msg);
			}

			@Override
			public void internalError(STMessage msg) {
				error = log.errorProcessTemplate(msg);
			}

			@Override
			public void compileTimeError(STMessage msg) {
				error = log.errorProcessTemplate(msg);
			}

			@Override
			public void IOError(STMessage msg) {
				error = log.errorProcessTemplate(msg);
			}
		});
	}

	private STGroupFile openGroupFile(URL templateUrl)
			throws ResourcesException {
		String encoding;
		char startChar;
		char stopChar;
		encoding = properties.getProperty(ENCODING_PROPERTY);
		startChar = properties.getCharProperty(DELIMITER_START_CHAR_PROPERTY);
		stopChar = properties.getCharProperty(DELIMITER_STOP_CHAR_PROPERTY);
		try {
			return new STGroupFile(templateUrl, encoding, startChar, stopChar);
		} catch (STException e) {
			throw log.errorOpenGroupFile(e);
		}
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	@Override
	public URL getURL() {
		return templateUrl;
	}

	@Override
	public String process(String name, Object... data)
			throws ResourcesException {
		ST template = createTemplate(name, data);
		String rendered = renderTemplate(template);
		log.templateProcessed(name);
		return rendered;
	}

	private ST createTemplate(String templateName, Object... data)
			throws ResourcesException {
		ST template = groupFile.getInstanceOf(templateName);
		log.checkTemplateCreated(template, templateName);
		throwErrors();
		for (int i = 0; i < data.length; i++) {
			template.add(data[i].toString(), data[++i]);
		}
		return template;
	}

	private String renderTemplate(ST template) throws ResourcesException {
		String rendered = template.render();
		throwErrors();
		return rendered;
	}

	private void throwErrors() throws ResourcesException {
		if (error != null) {
			throw error;
		}
	}
}