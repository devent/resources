/*
 * Copyright 2012-2025 Erwin Müller <erwin.mueller@anrisoftware.com>
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

public class GetBundleWithClassLoader extends GetBundle {

	private final ClassLoader classLoader;

	/**
	 * Sets the resource bundle the base name and class loader.
	 * 
	 * @param baseName
	 *            the bundle base name {@link String}.
	 * 
	 * @param classLoader
	 *            the bundle {@link ClassLoader}.
	 */
	public GetBundleWithClassLoader(String baseName, ClassLoader classLoader) {
		super(baseName);
		this.classLoader = classLoader;
	}

	@Override
	public ResourceBundle bundleFor(Locale locale) {
		return getBundle(getBaseName(), locale, classLoader);
	}

	@Override
	public ClassLoader getClassLoader() {
		return classLoader;
	}
}
