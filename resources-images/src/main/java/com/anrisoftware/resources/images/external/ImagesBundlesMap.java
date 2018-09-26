
package com.anrisoftware.resources.images.external;

/*-
 * #%L
 * Resources :: Image
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

import java.util.ResourceBundle;

/**
 * Associating a resource bundle with an images map.
 * <p>
 * Lazy create a new images map for a new resource bundle.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
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
