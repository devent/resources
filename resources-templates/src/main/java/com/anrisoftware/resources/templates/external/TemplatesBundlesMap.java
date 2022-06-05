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


import java.util.ResourceBundle;

public interface TemplatesBundlesMap {

	/**
	 * Returns the template resources for the specified resource bundle.
	 * <p>
	 * If no template resources are found for the specified resource bundle a
	 * new templates map is created.
	 * 
	 * @param bundle
	 *            the {@link ResourceBundle}.
	 * 
	 * @return the {@link TemplatesMap} for the resource bundle.
	 */
	TemplatesMap getTemplates(ResourceBundle bundle);

}
