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
package com.anrisoftware.resources.texts.defaults;

import com.anrisoftware.resources.binary.binaries.BinariesResourcesModule;
import com.anrisoftware.resources.binary.maps.BinariesDefaultMapsModule;
import com.anrisoftware.resources.texts.maps.TextsDefaultMapsModule;
import com.anrisoftware.resources.texts.texts.TextsResourcesCharsetModule;
import com.anrisoftware.resources.texts.texts.TextsResourcesModule;
import com.google.inject.AbstractModule;

/**
 * Installs default modules for the texts resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.5
 */
public class TextsResourcesDefaultModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new TextsResourcesModule());
		install(new TextsResourcesCharsetModule());
		install(new TextsDefaultMapsModule());
		install(new BinariesResourcesModule());
		install(new BinariesDefaultMapsModule());
	}

}
