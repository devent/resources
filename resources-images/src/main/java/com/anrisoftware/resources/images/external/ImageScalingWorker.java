
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

import java.awt.Image;
import java.util.concurrent.Callable;

/**
 * Scales an image to a new size.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface ImageScalingWorker extends Callable<Image> {

	/**
	 * Scale the image to the set width and height.
	 * 
	 * @return the scaled {@link Image}.
	 */
	@Override
	Image call() throws Exception;

}
