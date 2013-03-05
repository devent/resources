/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-binary.
 *
 * resources-binary is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-binary is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-binary. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.binary

import org.junit.Test

import com.anrisoftware.resources.binary.api.BinariesFactory
import com.anrisoftware.resources.binary.binaries.BinariesResourcesModule
import com.anrisoftware.resources.binary.maps.BinariesDefaultMapsModule

/**
 * Test the binary resources.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class BinaryResourcesTest extends AbstractBinaryResourcesTest {

	@Test
	void "load lorem zipfile with different locale"() {
		super."load lorem zipfile with different locale"()
	}

	@Test
	void "test serialize binary resource"() {
		super."test serialize binary resource"()
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
		new BinariesDefaultMapsModule()
	}
}
