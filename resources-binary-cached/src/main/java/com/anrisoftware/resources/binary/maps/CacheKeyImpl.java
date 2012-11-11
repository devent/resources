package com.anrisoftware.resources.binary.maps;

import java.util.Locale;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.resources.binary.api.BinariesCacheKey;

@SuppressWarnings("serial")
class CacheKeyImpl implements BinariesCacheKey {

	private int hashCode;

	/**
	 * For serialization.
	 */
	@Deprecated
	public CacheKeyImpl() {
	}

	public CacheKeyImpl(String name, String baseName, Locale locale) {
		this.hashCode = new HashCodeBuilder().append(name).append(baseName)
				.append(locale).hashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(hashCode).toString();
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		CacheKeyImpl that = (CacheKeyImpl) obj;
		return hashCode == that.hashCode();
	}

}
