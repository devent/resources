/*
 * Copyright 2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.getbundle.external;

import static java.util.ResourceBundle.getBundle;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Returns the resource bundle with the specified base name and control.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class GetBundleWithControl extends GetBundle {

	private final ResourceBundle.Control control;

	/**
	 * Sets the resource bundle the base name and class loader.
	 * 
	 * @param baseName
	 *            the bundle base name {@link String}.
	 * 
	 * @param control
	 *            the bundle {@link ResourceBundle.Control}.
	 */
	public GetBundleWithControl(String baseName, ResourceBundle.Control control) {
		super(baseName);
		this.control = control;
	}

	@Override
	public ResourceBundle bundleFor(Locale locale) {
		return getBundle(getBaseName(), locale, control);
	}

}
