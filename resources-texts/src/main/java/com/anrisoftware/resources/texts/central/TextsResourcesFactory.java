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
