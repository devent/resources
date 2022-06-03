/*
 * Copyright 2012-2022 Erwin Müller <erwin.mueller@anrisoftware.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.anrisoftware.resources.templates.external;


import java.io.Serializable;
import java.net.URL;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

public interface TemplateResourceFactory {

	/**
	 * Creates a new template resource with the specified name, locale, URL and
	 * template properties.
	 * 
	 * @param name
	 *            the name {@link String} of the template.
	 * 
	 * @param locale
	 *            the {@link Locale} the template.
	 * 
	 * @param url
	 *            the {@link URL} of the resource.
	 * 
	 * @param properties
	 *            the template {@link Properties} which specify additional
	 *            parameter for the template.
	 * 
	 * @return the new {@link TemplateResource}.
	 */
	TemplateResource create(String name, Locale locale, URL url,
			Properties properties);

	/**
	 * Creates a new template resource with the specified name, locale, URL and
	 * template properties.
	 * 
	 * @param name
	 *            the name {@link String} of the template.
	 * 
	 * @param locale
	 *            the {@link Locale} the template.
	 * 
	 * @param url
	 *            the {@link URL} of the resource.
	 * 
	 * @param properties
	 *            the template {@link Properties} which specify additional
	 *            parameter for the template.
	 * 
	 * @param attributes
	 *            the attributes {@link Map} for the template.
	 * 
	 * @return the new {@link TemplateResource}.
	 * 
	 * @since 1.4
	 */
	TemplateResource create(String name, Locale locale, URL url,
			Properties properties, Map<Serializable, Serializable> attributes);
}
