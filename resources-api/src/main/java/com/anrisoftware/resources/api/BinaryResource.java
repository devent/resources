package com.anrisoftware.resources.api;

import java.io.InputStream;
import java.net.URL;
import java.util.Locale;

/**
 * Resource for binary data.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.2
 */
public interface BinaryResource {

	/**
	 * Returns the name of this resource.
	 * 
	 * @return the {@link String} name.
	 */
	String getName();

	/**
	 * Returns the locale of the resource.
	 * 
	 * @return the {@link Locale} of the resource.
	 */
	Locale getLocale();

	/**
	 * Returns the URL of the resource.
	 * 
	 * @return the {@link URL} of the resource.
	 */
	URL getURL();

	/**
	 * <p>
	 * Returns the binary data of the resource.
	 * </p>
	 * <p>
	 * The binary data is read in to memory upon the first request. It is stored
	 * then for quicker access.
	 * </p>
	 * 
	 * @return a byte array of the data.
	 * 
	 * @throws ResourcesException
	 *             if there was an error loading the binary data.
	 */
	byte[] getBinary() throws ResourcesException;

	/**
	 * <p>
	 * Opens an input stream that reads the binary data.
	 * </p>
	 * <p>
	 * The binary data can't be stored for quick access because it is red
	 * sequential from the stream.
	 * </p>
	 * 
	 * @return an {@link InputStream} that reads the binary data.
	 * 
	 * @throws ResourcesException
	 *             if there was an error opening the binary data.
	 */
	InputStream getStream() throws ResourcesException;

	/**
	 * <p>
	 * Discards the binary data of this resource.
	 * </p>
	 * <p>
	 * Use this method if the binary data is no longer needed.
	 * </p>
	 * 
	 * @throws ResourcesException
	 *             if there was an error discarding the data.
	 */
	void discardBinary() throws ResourcesException;
}
