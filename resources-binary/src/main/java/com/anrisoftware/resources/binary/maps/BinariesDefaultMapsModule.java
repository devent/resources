/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.binary.maps;

import com.anrisoftware.resources.binary.api.BinariesMap;
import com.anrisoftware.resources.binary.api.BinariesMapFactory;
import com.anrisoftware.resources.binary.api.BundlesMap;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the Java hash map as the binary resources map.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class BinariesDefaultMapsModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(BundlesMap.class).to(DefaultBundlesMap.class);
		install(new FactoryModuleBuilder().implement(BinariesMap.class,
				DefaultBinariesMap.class).build(BinariesMapFactory.class));
	}

}
