/*
 * Copyright 2012-2023 Erwin Müller <erwin.mueller@anrisoftware.com>
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
package com.anrisoftware.resources.images.external;


import java.util.ResourceBundle;

public interface ImagesBundlesMap {

	/**
	 * Returns the images for the specified resource bundle.
	 * <p>
	 * If no images are found for the specified resource bundle a new images map
	 * is created.
	 * 
	 * @param bundle
	 *            the {@link ResourceBundle}.
	 * 
	 * @return the {@link ImagesMap} for the resource bundle.
	 */
	ImagesMap getImages(ResourceBundle bundle);

}
