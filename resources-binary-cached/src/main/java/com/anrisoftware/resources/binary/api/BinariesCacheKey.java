package com.anrisoftware.resources.binary.api;

import java.io.Serializable;

public interface BinariesCacheKey extends Serializable {

	@Override
	int hashCode();

	@Override
	boolean equals(Object obj);
}
