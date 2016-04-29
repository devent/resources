/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.texts.internal.texts

import org.junit.Test

import com.anrisoftware.resources.texts.AbstractTextResourceTest
import com.anrisoftware.resources.texts.external.TextsFactory
import com.anrisoftware.resources.texts.internal.maps.TextsDefaultMapsModule
import com.google.inject.Injector

/**
 * Creates the text resources and runs functionality tests.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TextResourceTest extends AbstractTextResourceTest {

    @Test
    void "load plain text with defined locale"() {
        super."load plain text with defined locale"()
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
}
