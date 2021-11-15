/*
 * Copyright 2012-2021 Erwin Müller <erwin.mueller@anrisoftware.com>
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
package com.anrisoftware.resources.texts.internal

import static com.anrisoftware.globalpom.utils.TestUtils.*

import org.apache.commons.io.Charsets
import org.junit.jupiter.api.BeforeEach

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

	@BeforeEach
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
