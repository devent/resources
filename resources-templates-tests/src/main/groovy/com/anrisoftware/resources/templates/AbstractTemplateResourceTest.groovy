/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-templates-tests.
 *
 * resources-templates-tests is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-templates-tests is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-templates-tests. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.templates

import org.perf4j.LoggingStopWatch
import org.perf4j.StopWatch

/**
 * Test for functionality of the template resources.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.3
 */
abstract class AbstractTemplateResourceTest extends AbstractTemplateResourceTestUtils {

	void "load template"() {
		String[] args = ["arg1", "one", "arg2", "two"]
		def baseName = "Templates"
		def templates = factory.create baseName
		def template = templates.getResource "test"

		assertStringContent template.getText(args), "${args[1]}:${args[3]}"
		assert template.locale.toString() == ""
		assert template.URL.toString() =~ ".+"
	}

	void "load template with set class loader"() {
		String[] args = ["arg1", "one", "arg2", "two"]
		def baseName = "Templates"
		def classLoader = getClass().classLoader
		def templates = factory.create baseName, classLoader
		def template = templates.getResource "test"

		assertStringContent template.getText(args), "${args[1]}:${args[3]}"
		assert template.locale.toString() == ""
		assert template.URL.toString() =~ ".+"
	}

	void "serialize resource"() {
		String[] args = ["arg1", "one", "arg2", "two"]
		def name = "test"
		def locale = Locale.ENGLISH
		def url = templateUrl

		def resource = resourceFactory.create name, locale, url, templateProperties
		def resourceB = reserialize resource

		assertStringContent resourceB.getText(args), "${args[1]}:${args[3]}"
		assert resourceB.name == name
		assert resourceB.locale == locale
		assert resourceB.URL == url
		assert resourceB.properties == templateProperties
	}

	void "load template with same data"() {
		String[] args = ["arg1", "one", "arg2", "two"]
		def baseName = "Templates"
		def templates = factory.create baseName
		def template = templates.getResource "test"

		StopWatch stopWatch = new LoggingStopWatch("get text from template")
		stopWatch.lap("Load template")
		assertStringContent template.getText(args), "${args[1]}:${args[3]}"
		stopWatch.lap("First access")
		assertStringContent template.getText(args), "${args[1]}:${args[3]}"
		assertStringContent template.getText(args), "${args[1]}:${args[3]}"
		assertStringContent template.getText(args), "${args[1]}:${args[3]}"
		stopWatch.stop("Additional access with same data")
	}
}
