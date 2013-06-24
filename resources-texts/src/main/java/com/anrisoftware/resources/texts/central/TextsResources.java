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

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;

import javax.swing.KeyStroke;

import org.apache.commons.lang3.event.EventListenerSupport;

import com.anrisoftware.globalpom.mnemonic.Accelerator;
import com.anrisoftware.globalpom.mnemonic.AcceleratorFactory;
import com.anrisoftware.globalpom.mnemonic.Mnemonic;
import com.anrisoftware.globalpom.mnemonic.MnemonicFactory;
import com.anrisoftware.resources.api.LocaleListener;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Centralized access to all text resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.4
 */
public class TextsResources {

	/**
	 * Action accelerator keys resource bundle.
	 */
	public static final String ACTION_ACCELERATORS_PROPERTY = "action_accelerators";

	/**
	 * Action mnemonics resource bundle.
	 */
	public static final String ACTION_MNEMONICS_PROPERTY = "action_mnemonics";

	/**
	 * Action names resource bundle.
	 */
	public static final String ACTIONS_PROPERTY = "actions";

	/**
	 * Texts resource bundle.
	 */
	public static final String TEXTS_PROPERTY = "texts";

	private final EventListenerSupport<LocaleListener> localeListeners;

	private final MnemonicFactory mnemonicFactory;

	private final AcceleratorFactory acceleratorFactory;

	private Texts texts;

	private Texts actions;

	private Texts mnemonics;

	private Texts accelerators;

	private Locale locale;

	/**
	 * Sets default properties.
	 * 
	 * @since 1.6
	 */
	@AssistedInject
	TextsResources(TextsFactory textsFactory, MnemonicFactory mnemonicFactory,
			AcceleratorFactory acceleratorFactory) {
		this(textsFactory, mnemonicFactory, acceleratorFactory,
				createDefaultProperties());
	}

	private static Properties createDefaultProperties() {
		Properties p = new Properties();
		p.setProperty(TEXTS_PROPERTY, "Texts");
		p.setProperty(ACTIONS_PROPERTY, "Actions");
		p.setProperty(ACTION_MNEMONICS_PROPERTY, "ActionMnemonics");
		p.setProperty(ACTION_ACCELERATORS_PROPERTY, "ActionAccelerators");
		return p;
	}

	/**
	 * @see TextsResourcesFactory#create(Properties)
	 * 
	 * @since 1.6
	 */
	@AssistedInject
	TextsResources(TextsFactory textsFactory, MnemonicFactory mnemonicFactory,
			AcceleratorFactory acceleratorFactory,
			@Assisted Properties properties) {
		if (properties.containsKey(TEXTS_PROPERTY)) {
			this.texts = textsFactory.create(properties
					.getProperty(TEXTS_PROPERTY));
		}
		if (properties.containsKey(ACTIONS_PROPERTY)) {
			this.actions = textsFactory.create(properties
					.getProperty(ACTIONS_PROPERTY));
		}
		if (properties.containsKey(ACTION_MNEMONICS_PROPERTY)) {
			this.mnemonics = textsFactory.create(properties
					.getProperty(ACTION_MNEMONICS_PROPERTY));
		}
		if (properties.containsKey(ACTION_ACCELERATORS_PROPERTY)) {
			this.accelerators = textsFactory.create(properties
					.getProperty(ACTION_ACCELERATORS_PROPERTY));
		}
		this.localeListeners = new EventListenerSupport<LocaleListener>(
				LocaleListener.class);
		this.locale = Locale.getDefault();
		this.mnemonicFactory = mnemonicFactory;
		this.acceleratorFactory = acceleratorFactory;
	}

	public void setTexts(Texts texts) {
		Texts oldValue = this.texts;
		this.texts = texts;
		if (oldValue != texts) {
			fireUpdateLocale();
		}
	}

	public Texts getTexts() {
		return texts;
	}

	public void setActions(Texts actions) {
		Texts oldValue = this.actions;
		this.actions = actions;
		if (oldValue != actions) {
			fireUpdateLocale();
		}
	}

	public Texts getActions() {
		return actions;
	}

	public void setMnemonics(Texts mnemonics) {
		Texts oldValue = this.mnemonics;
		this.mnemonics = mnemonics;
		if (oldValue != mnemonics) {
			fireUpdateLocale();
		}
	}

	public Texts getMnemonics() {
		return mnemonics;
	}

	public void setAccelerators(Texts accelerators) {
		Texts oldValue = this.accelerators;
		this.accelerators = accelerators;
		if (oldValue != accelerators) {
			fireUpdateLocale();
		}
	}

	public Texts getAccelerators() {
		return accelerators;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
		fireUpdateLocale();
	}

	public Locale getLocale() {
		return locale;
	}

	private void fireUpdateLocale() {
		localeListeners.fire().updateLocale(locale);
	}

	public String getText(String name) throws ResourcesException {
		return texts.getResource(name, locale).getText();
	}

	public String getTextFormat(String name, Object... args)
			throws ResourcesException {
		return texts.getResource(name, locale).getFormattedText(args);
	}

	public String getAction(String name) throws ResourcesException {
		return actions.getResource(name, locale).getText();
	}

	/**
	 * Loads a mnemonic text resource with the specified name and returns the
	 * mnemonic key code.
	 * 
	 * @see Mnemonic#getMnemonic()
	 * 
	 * @param name
	 *            the mnemonic text resource name.
	 * 
	 * @throws ResourcesException
	 *             if there was an error loading the text resource.
	 */
	public int getMnemonic(String name) throws ResourcesException {
		String keyname = mnemonics.getResource(name, locale).getText();
		return mnemonicFactory.create(keyname).getMnemonic();
	}

	/**
	 * Loads a mnemonic text resource with the specified name and returns the
	 * displayed mnemonic index if such index was set in the resource.
	 * 
	 * @see Mnemonic#getMnemonicIndex()
	 * 
	 * @param name
	 *            the mnemonic text resource name.
	 * 
	 * @throws ResourcesException
	 *             if there was an error loading the text resource.
	 */
	public int getMnemonicIndex(String name) throws ResourcesException {
		String keyname = mnemonics.getResource(name, locale).getText();
		return mnemonicFactory.create(keyname).getMnemonicIndex();
	}

	/**
	 * Loads a accelerator text resource with the specified name and returns the
	 * accelerator key for an action.
	 * 
	 * @see Accelerator#getAccelerator()
	 * 
	 * @param name
	 *            the accelerator text resource name.
	 * 
	 * @throws ResourcesException
	 *             if there was an error loading the text resource.
	 */
	public KeyStroke getAccelerator(String name) {
		try {
			String keyname = accelerators.getResource(name, locale).getText();
			return acceleratorFactory.create(keyname).getAccelerator();
		} catch (MissingResourceException e) {
			return null;
		}
	}

	public void addTextsLocaleListeners(LocaleListener l) {
		localeListeners.addListener(l);
	}

	public void removeTextsLocaleListeners(LocaleListener l) {
		localeListeners.removeListener(l);
	}

}
