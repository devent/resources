/*
 * Copyright 2017 Erwin Müller <erwin.mueller@deventm.org>
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

import java.io.InputStream;

import com.anrisoftware.resources.api.external.Resource;
import com.anrisoftware.resources.api.external.ResourcesException;

/**
 * Binary data resource.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public interface BinaryResource extends Resource {

    /**
     * Returns the binary data of the resource.
     * <p>
     * The binary data is read in to memory upon the first request. It is stored
     * then for quicker access.
     *
     * @return a byte array of the data.
     *
     * @throws ResourcesException
     *             if there was an error loading the binary data.
     */
    byte[] getBinary() throws ResourcesException;

    /**
     * Opens an input stream that reads the binary data.
     * <p>
     * The binary data can't be stored for quick access because it is red
     * sequential from the stream.
     *
     * @return an {@link InputStream} that reads the binary data.
     *
     * @throws ResourcesException
     *             if there was an error opening the binary data.
     */
    InputStream getStream() throws ResourcesException;

    /**
     * Discards the binary data of this resource.
     * <p>
     * Use this method if the binary data is no longer needed.
     *
     * @throws ResourcesException
     *             if there was an error discarding the data.
     */
    void discardBinary() throws ResourcesException;
}
