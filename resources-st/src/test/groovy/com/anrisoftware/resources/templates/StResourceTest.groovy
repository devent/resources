/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-st.
 *
 * resources-st is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-st is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-st. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.templates

import org.junit.Test

import com.anrisoftware.resources.api.ResourcesException
import com.anrisoftware.resources.templates.api.TemplateResourceFactory
import com.anrisoftware.resources.templates.api.Templates
import com.anrisoftware.resources.templates.api.TemplatesFactory
import com.anrisoftware.resources.templates.maps.TemplatesDefaultMapsModule
import com.anrisoftware.resources.templates.templates.TemplatesResourcesModule
import com.anrisoftware.resources.templates.worker.STDefaultPropertiesModule
import com.anrisoftware.resources.templates.worker.STWorkerModule

/**
 * Creates the ST template resources and runs functionality tests.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class StResourceTest extends AbstractTemplateResourceTest {

	@Test
	void "load ST template"() {
		super."load template"()
	}

	@Test
	void "load template with set class loader"() {
		super."load template with set class loader"()
	}

	@Test
	void "serialize resource"() {
		super."serialize resource"()
	}

	@Test
	void "load ST template with same data"() {
		super."load template with same data"()
	}

	@Test
	void "load ST template in a template group"() {
		String[] args = [
			"st",
			"arg1",
			"one",
			"arg2",
			"two"
		]
		def baseName = "Templates"
		def templates = factory.create baseName
		def template = templates.getResource "stttemplate"

		assertStringContent template.getText(args), "${args[2]}:${args[4]}"
		assert template.locale.toString() == ""
		assert template.URL.toString() =~ ".+"
	}

	@Test
	void "load multiple ST template in same template group"() {
		String[] args = [
			"foo",
			"arg1",
			"foo1",
			"arg2",
			"foo2"
		]
		def baseName = "Templates"
		Templates templates = factory.create baseName
		def template = templates.getResource "multipletemplates"

		assertStringContent template.getText(args), "${args[2]}:${args[4]}"

		args = [
			"bar",
			"arg1",
			"bar1",
			"arg2",
			"bar2"
		]
		template.invalidate()
		assertStringContent template.getText(args), "${args[2]}:${args[4]}"
	}

	@Test(expected = ResourcesException)
	void "load missng template"() {
		String[] args = [
			"arg1",
			"one",
			"arg2",
			"two"
		]
		def baseName = "Templates"
		def templates = factory.create baseName
		def template = templates.getResource "errorstemplate"
		template.getText(args)
	}

	@Test(expected = ResourcesException)
	void "load template with errors"() {
		String[] args = [
			"test",
			"arg1",
			"one",
			"arg2",
			"two"
		]
		def baseName = "Templates"
		def templates = factory.create baseName
		def template = templates.getResource "errorstemplate"
		template.getText(args)
	}

	def getTemplatesModule() {
		new TemplatesResourcesModule()
	}

	def getTemplateWorkerModule() {
		new STWorkerModule()
	}

	def getTemplatesMapModule() {
		new TemplatesDefaultMapsModule()
	}

	@Override
	def getPropertiesModule() {
		new STDefaultPropertiesModule()
	}

	@Override
	def createFactory() {
		injector.getInstance TemplatesFactory
	}

	@Override
	def createResourceFactory() {
		injector.getInstance TemplateResourceFactory
	}

	@Override
	def getTemplateUrl() {
		StResourceTest.class.getResource("testtemplate.stg")
	}

	@Override
	def getTemplateProperties() {
		propertiesModule.getSTDefaultProperties()
	}
}
