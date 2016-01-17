/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-texts-tests.
 *
 * resources-texts-tests is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-texts-tests is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-texts-tests. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.texts

import static com.anrisoftware.globalpom.utils.TestUtils.*

import org.junit.Before
import org.junit.BeforeClass

import com.anrisoftware.resources.binary.binaries.BinariesResourcesModule
import com.anrisoftware.resources.binary.maps.BinariesDefaultMapsModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Creates the environment to unit test the texts resources. Adds binary
 * resources modules.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractTextResourceTestUtils {

	Injector injector

	def factory

	@BeforeClass
	static void setupToStringStyle() {
		toStringStyle
	}

	@Before
	void createFactories() {
		injector = createInjector()
		factory = createFactory()
	}

	/**
	 * Creates the needed modules if they are not already have been created.
	 *
	 * @since 1.2
	 */
	Injector createInjector() {
		Guice.createInjector(textsModule,
						textsMapModule,
						characterSetModule,
						binariesModule,
						binariesMapModule)
	}

	/**
	 * Create the texts resources factory.
	 *
	 * @since 1.2
	 */
	abstract createFactory()

	/**
	 * Returns the module that binds the texts resources.
	 */
	abstract getTextsModule()

	/**
	 * Returns the module that binds the texts map.
	 */
	abstract getTextsMapModule()

	/**
	 * Returns the module that binds the default character set for
	 * text resources.
	 *
	 * @since 1.2
	 */
	abstract getCharacterSetModule()

	/**
	 * Returns the module that binds the binaries resources.
	 */
	def getBinariesModule() {
		new BinariesResourcesModule()
	}

	/**
	 * Returns the module that binds the binaries map.
	 */
	def getBinariesMapModule() {
		new BinariesDefaultMapsModule()
	}
}
