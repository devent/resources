/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.templates.internal.worker;

import com.anrisoftware.resources.templates.external.TemplatesPropertiesFactory;
import com.google.inject.AbstractModule;

/**
 * Binds default ST4 template properties.
 *
 * @author Erwin Müller, erwin.mueller@deventm.org
 * @since 2.1
 */
public class STDefaultPropertiesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TemplatesPropertiesFactory.class).to(
                STDefaultPropertiesFactory.class);
    }
}
