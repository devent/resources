package com.anrisoftware.resources.binary.maps;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;

import javax.inject.Inject;

import com.anrisoftware.resources.api.BinaryResource;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.binary.api.BinariesMap;

/**
 * Stores the binary resources in the Java hash map.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class DefaultBinariesMap implements BinariesMap {

	private final BinariesMapLogger log;

	private final Map<String, BinaryResource> map;

	/**
	 * Creates the images map.
	 */
	@Inject
	DefaultBinariesMap(BinariesMapLogger logger) {
		this.log = logger;
		this.map = newHashMap();
	}

	@Override
	public void putBinary(BinaryResource resource) throws ResourcesException {
		String name = resource.getName();
		if (!map.containsKey(name)) {
			map.put(name, resource);
		} else {
			log.imageAlreadyInMap(resource);
		}
	}

	@Override
	public BinaryResource getBinary(String name) {
		return map.get(name);
	}

	@Override
	public boolean haveBinary(String name) {
		return map.containsKey(name);
	}

	@Override
	public String toString() {
		return map.toString();
	}

}
