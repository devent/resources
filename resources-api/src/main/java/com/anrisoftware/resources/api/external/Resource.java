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
package com.anrisoftware.resources.api.external;


import java.net.URL;
import java.util.Locale;

public interface Resource {

	/**
	 * Returns the name of this resource.
	 * 
	 * @return the {@link String} name.
	 */
	String getName();

	/**
	 * Returns the locale of the resource.
	 * 
	 * @return the {@link Locale} of the resource.
	 */
	Locale getLocale();

	/**
	 * Returns the URL of the resource.
	 * 
	 * @return the {@link URL} of the resource.
	 */
	URL getURL();

}
