/**
 * Copyright © 2012 Erwin Müller (erwin.mueller@anrisoftware.com)
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
