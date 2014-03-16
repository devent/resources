/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-texts.
 *
 * resources-texts is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-texts is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-texts. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.texts

import org.junit.Test

import com.anrisoftware.resources.texts.api.TextResourceFactory
import com.anrisoftware.resources.texts.api.TextsFactory
import com.anrisoftware.resources.texts.maps.TextsDefaultMapsModule
import com.anrisoftware.resources.texts.texts.TextsResourcesCharsetModule
import com.anrisoftware.resources.texts.texts.TextsResourcesModule

/**
 * Test the serializable text resource.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class SerializeTextResourceTest extends AbstractSerializationTest {

	@Test
	void "serialize text resource"() {
		super."serialize text resource"()
	}

	@Override
	def getTextsModule() {
		new TextsResourcesModule()
	}

	@Override
	def getTextsMapModule() {
		new TextsDefaultMapsModule()
	}

	@Override
	def createFactory() {
		injector.getInstance(TextsFactory)
	}

	@Override
	def getCharacterSetModule() {
		new TextsResourcesCharsetModule()
	}

	@Override
	def createTextFactory() {
		injector.getInstance(TextResourceFactory)
	}
}
