/**
 * Copyright © 2012 Erwin Müller (erwin.mueller@anrisoftware.com)
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

package com.anrisoftware.resources.binary.internal.binaries;


import java.util.Locale;

import com.anrisoftware.resources.binary.external.BinariesBundlesMap;
import com.anrisoftware.resources.getbundle.external.GetBundle;

interface BinariesWorkerFactory {

	/**
	 * Creates a new {@link BinariesWorker}.
	 * 
	 * @param name
	 *            the {@link String} name of the resource we want to get.
	 * 
	 * @param locale
	 *            the {@link Locale} of the resource we want to get.
	 * 
	 * @param getBundle
	 *            the {@link GetBundle} that returns the resource bundle for the
	 *            locale.
	 * 
	 * @param bundles
	 *            the map of bundles with their binary resources maps.
	 */
	BinariesWorker create(String name, Locale locale, GetBundle getBundle,
			BinariesBundlesMap bundles);
}
