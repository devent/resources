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
package com.anrisoftware.resources.images.internal.images;

import com.anrisoftware.resources.external.GetBundle;
import com.anrisoftware.resources.images.external.ImagesBundlesMap;

/**
 * Factory to create a new images worker.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
interface ImagesWorkerFactory {

	/**
	 * Creates a new {@link ImagesWorker}.
	 * 
	 * @param getBundle
	 *            the {@link GetBundle} that returns the resource bundle for the
	 *            locale.
	 * 
	 * @param bundles
	 *            the map of bundles with their images maps.
	 */
	ImagesWorker create(GetBundle getBundle, ImagesBundlesMap bundles);
}
