/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.texts.central;

import java.util.Properties;

/**
 * Factory for centralized access to all text resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.6
 */
public interface TextsResourcesFactory {

	/**
	 * Sets the properties for the texts resources.
	 * 
	 * @param properties
	 *            the {@link Properties}.
	 * 
	 * @return the {@link TextsResources}.
	 * 
	 * @see TextsResources#ACTION_ACCELERATORS_PROPERTY
	 * @see TextsResources#ACTION_MNEMONICS_PROPERTY
	 * @see TextsResources#ACTIONS_PROPERTY
	 * @see TextsResources#TEXTS_PROPERTY
	 */
	TextsResources create(Properties properties);
}
