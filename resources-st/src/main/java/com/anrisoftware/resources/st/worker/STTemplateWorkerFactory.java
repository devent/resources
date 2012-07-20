package com.anrisoftware.resources.st.worker;

import java.net.URL;
import java.util.Properties;

/**
 * Factory to create a new template worker that is using a <a
 * href=http://www.antlr.org/wiki/display/ST4/StringTemplate+4+Wiki+Home>String
 * Template</a> template engine.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface STTemplateWorkerFactory {

	static final String DELIMITER_STOP_CHAR_PROPERTY = "template_delimiter_stop_character";

	static final String DELIMITER_START_CHAR_PROPERTY = "template_delimiter_start_character";

	static final String ENCODING_PROPERTY = "template_encoding";

	/**
	 * Creates a new template worker that is using a <a
	 * href=http://www.antlr.org
	 * /wiki/display/ST4/StringTemplate+4+Wiki+Home>String Template</a> template
	 * engine.
	 * 
	 * @param templateUrl
	 *            the {@link URL} of the template group file.
	 * 
	 * @param properties
	 *            the {@link Properties} for the template group file. Have the
	 *            properties:
	 *            <ul>
	 *            <li>{@value #ENCODING_PROPERTY}</li>
	 *            <li>{@value #DELIMITER_START_CHAR_PROPERTY}</li>
	 *            <li>{@value #DELIMITER_STOP_CHAR_PROPERTY}</li>
	 *            </ul>
	 * 
	 * @return the {@link TemplateWorker}.
	 */
	TemplateWorker create(URL templateUrl, Properties properties);
}
