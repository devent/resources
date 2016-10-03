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
package com.anrisoftware.resources.binary.external;

import java.util.ResourceBundle;

/**
 * Associating a resource bundle with an binaries map.
 * <p>
 * Lazy create a new binaries map for a new resource bundle.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public interface BinariesBundlesMap {

	/**
	 * Returns the binaries for the specified resource bundle.
	 * <p>
	 * If no binaries are found for the specified resource bundle a new binaries
	 * map is created.
	 * 
	 * @param baseName
	 *            the base name {@link String} of the resource bundle.
	 * 
	 * @param bundle
	 *            the {@link ResourceBundle}.
	 * 
	 * @return the {@link BinariesMap} for the resource bundle.
	 */
	BinariesMap getBinaries(String baseName, ResourceBundle bundle);

}
