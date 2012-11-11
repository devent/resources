package com.anrisoftware.resources.binary.api;

import java.io.InputStream;

import com.anrisoftware.resources.api.Resource;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Resource for binary data.
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
