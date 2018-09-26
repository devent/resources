
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

/**
 * The default resolutions for an image resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public enum ImageResolution {

	/**
	 * Low resolution.
	 */
	LOW("ldpi"),

	/**
	 * Medium resolution.
	 */
	MEDIUM("mdpi"),

	/**
	 * High resolution.
	 */
	HIGH("hdpi"),

	/**
	 * Very high resolution.
	 */
	EXTRA_HIGH("xhdpi");

	private final String name;

	private ImageResolution(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of this resolution.
	 * 
	 * @return the name.
	 */
	public String getName() {
		return name;
	}
}
