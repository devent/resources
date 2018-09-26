
package com.anrisoftware.resources.templates.external;

/*-
 * #%L
 * Resources :: Templates
 * %%
 * Copyright (C) 2012 - 2018 Advanced Natural Research Institute
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.Serializable;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

/**
 * Factory to create a new template worker.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface TemplateWorkerFactory {

	/**
	 * Creates a new template worker.
	 * 
	 * @param templateUrl
	 *            the {@link URL} of the template file.
	 * 
	 * @param properties
	 *            the {@link Properties} for the template.
	 * 
	 * @param attributes
	 *            the attributes {@link Map} for the template.
	 * 
	 * @return the {@link TemplateWorker}.
	 * 
	 * @since 1.4
	 */
	TemplateWorker create(URL templateUrl, Properties properties,
			Map<Serializable, Serializable> attributes);
}
