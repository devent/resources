
package com.anrisoftware.resources.st.external;

/*-
 * #%L
 * Resources :: Templates :: ST
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

import java.io.Serializable;

/**
 * Adds the attribute type.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.6
 */
public interface AttributeRenderer extends
        org.stringtemplate.v4.AttributeRenderer, Serializable {

    /**
     * Returns the attribute type of the renderer.
     *
     * @return the type {@link Class}.
     */
    Class<?> getAttributeType();
}