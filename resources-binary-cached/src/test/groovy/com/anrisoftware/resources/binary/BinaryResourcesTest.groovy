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
package com.anrisoftware.resources.binary

import javax.cache.Caching

import org.junit.Test

import com.anrisoftware.resources.binary.api.BinariesFactory
import com.anrisoftware.resources.binary.binaries.BinariesResourcesModule
import com.google.inject.Guice
import com.google.inject.Injector


class BinaryResourcesTest extends AbstractBinaryResourcesTest {

	@Test
	void "load lorem zipfile with different locale"() {
		super."load lorem zipfile with different locale"()
	}

	@Override
	Injector createInjector() {
		if (injector != null) {
			injector
		}
		Guice.createInjector([resourcesModule, mapModule].flatten())
	}

	@Override
	def createFactory() {
		injector.getInstance(BinariesFactory)
	}

	@Override
	def getResourcesModule() {
		new BinariesResourcesModule()
	}

	@Override
	def getMapModule() {
		new CachingUtil(Caching.getCacheManager()).mapModule
	}
}
