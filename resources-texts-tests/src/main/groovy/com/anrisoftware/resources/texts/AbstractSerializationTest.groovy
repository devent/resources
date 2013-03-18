/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
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

import org.apache.commons.io.Charsets
import org.junit.Before

import com.google.inject.Injector

/**
 * Test for serialization of the texts resources.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractSerializationTest extends AbstractTextResourceTestUtils {

	def textFactory

	void "serialize text resource"() {
		def name = "Text Resource A"
		def locale = Locale.ENGLISH
		def url = helloText
		def charset = Charsets.UTF_8

		def resource = textFactory.create name, locale, url, charset
		def resourceB = reserialize resource

		assert resourceB.name == name
		assert resourceB.locale == locale
		assert resourceB.URL == url
		assert resourceB.charset == charset
	}

	@Before
	void createFactories() {
		super.createFactories()
		textFactory = createTextFactory()
	}

	/**
	 * Create text resource factory.
	 *
	 * @since 1.2
	 */
	abstract createTextFactory()

	static final URL helloText = AbstractSerializationTest.class.getResource("/com/anrisoftware/resources/texts/texts/hello.txt")
}
